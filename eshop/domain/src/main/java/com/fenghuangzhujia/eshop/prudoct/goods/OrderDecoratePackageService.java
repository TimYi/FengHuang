package com.fenghuangzhujia.eshop.prudoct.goods;

import static com.fenghuangzhujia.eshop.core.base.SystemErrorCodes.*;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.area.AreaRepository;
import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.eshop.core.commerce.eshop.Shop;
import com.fenghuangzhujia.eshop.core.commerce.eshop.ShopRepository;
import com.fenghuangzhujia.eshop.core.commerce.goods.Good;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrderRepository;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.prudoct.cases.DecorateCase;
import com.fenghuangzhujia.eshop.prudoct.cases.DecorateCaseRepository;
import com.fenghuangzhujia.eshop.prudoct.goods.dto.PackageGoodDto;
import com.fenghuangzhujia.eshop.prudoct.goods.dto.PackageGoodDtoConverter;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackageRepository;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.utils.validater.PhoneNumberValidater;

@Service
@Transactional
public class OrderDecoratePackageService {
	
	@Autowired
	private UserRepository userRepository;	
	@Autowired
	private DecoratePackageRepository productRepository;	
	@Autowired
	private PackageGoodRepository packageGoodRepository;
	@Autowired
	private DecorateCaseRepository decorateCaseRepository;
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private GoodOrderRepository orderRepository;
	@Autowired
	private PackageGoodDtoConverter converter;
	@Autowired
	private AreaRepository areaRepository;
	
	/**
	 * 抢购套餐
	 * @param userid 用户
	 * @param id 套餐id
	 * @param caseid 案例id
	 * @return 生成订单的id
	 */
	public String scramble(String userid, String id, String caseid) {
		DecoratePackage product=productRepository.findOne(id);
		User user=userRepository.findOne(userid);
		Shop shop=shopRepository.findOne("0");
		if(product.getHousenum()==null || product.getHousenum()<=0) throw new ErrorCodeException(NO_GOOD);
		PackageGood good=new PackageGood();
		good.setShop(shop);
		good.setDecoratingPackage(product);
		if(StringUtils.isNotBlank(caseid)) {
			DecorateCase dcase=decorateCaseRepository.findOne(caseid);
			good.setDecorateCase(dcase);
		}
		packageGoodRepository.save(good);
		GoodOrder order=new GoodOrder();
		order.setUser(user);
		order.setGood(good);
		order.setCount(1);
		order.setPrice(good.getRealPrice());
		order.setStatus(OrderStatus.UNCONFIRM);
		order=orderRepository.save(order);		
		return order.getId();
	}
	
	/**
	 * 完善订单信息
	 * @param userid
	 * @param id
	 * @param caseid 案例
	 * @param areaid 区域
	 * @param address
	 * @param houseArea
	 * @param remark
	 */
	public void addDetail(String userid, String id, String caseid, String areaid, String address, Double houseArea, String remark) {
		GoodOrder order=orderRepository.findOne(id);
		if(order==null)throw new ErrorCodeException(ILLEGAL_ARGUMENT,"找不到指定订单");
		if(!order.getUser().getId().equals(userid))throw new ErrorCodeException(ILLEGAL_ARGUMENT,"这不是您的订单");
		Good good=order.getGood();
		if(!good.getClass().equals(PackageGood.class)) {
			throw new ErrorCodeException(GOOD_TYPE_DISMISS,"该订单商品类型错误");
		}
		PackageGood pg=(PackageGood)good;
		if(StringUtils.isNotBlank(caseid)) {
			DecorateCase dcase=decorateCaseRepository.findOne(caseid);
			pg.setDecorateCase(dcase);
		}
		if(StringUtils.isNotBlank(areaid)) {
			Area area=areaRepository.findOne(areaid);
			if(!area.getLevel().equals(AreaLevel.AREA))throw new ErrorCodeException(ILLEGAL_ARGUMENT,"必须选定区一级地址");
			pg.setArea(area);
		}
		pg.setAddress(address);
		pg.setRemark(remark);
		pg.setHouseArea(houseArea);
		if(houseArea!=null && houseArea>0) {
			Double price=pg.getRealPrice()*houseArea;
			order.setPrice(price);
			orderRepository.save(order);
		}
		packageGoodRepository.save(pg);
	}
	
	/**
	 * 确认订单
	 * @param userid
	 * @param id 订单id
	 * @param areaid 地区id
	 * @param address 详细住址
	 * @param houseArea 面积
	 * @param remark 留言
	 */
	public void comfirm(String userid, String id, String mobile) {
		GoodOrder order=orderRepository.findOne(id);
		if(order==null)throw new ErrorCodeException(ILLEGAL_ARGUMENT,"找不到指定订单");
		if(!order.getUser().getId().equals(userid))throw new ErrorCodeException(ILLEGAL_ARGUMENT,"这不是您的订单");
		Good good=order.getGood();
		if(!good.getClass().equals(PackageGood.class)) {
			throw new ErrorCodeException(GOOD_TYPE_DISMISS,"该订单商品类型错误");
		}
		PackageGood pg=(PackageGood)good;
		checkGoodCompletion(pg);
		if(!PhoneNumberValidater.isMobile(mobile))throw new ErrorCodeException(ILLEGAL_ARGUMENT,"不是标准11位手机号码");
		pg.setMobile(mobile);
		order.setStatus(OrderStatus.PROCESSING);
		orderRepository.save(order);
		packageGoodRepository.save(pg);
	}
	
	/**
	 * 根据订单id获取商品信息
	 * @param orderid
	 * @return
	 */
	public PackageGoodDto getGoodByOrder(String userid, String orderid) {
		GoodOrder order=orderRepository.findOne(orderid);
		if(order==null)throw new ErrorCodeException(ILLEGAL_ARGUMENT,"找不到指定订单");
		if(!order.getUser().getId().equals(userid))throw new ErrorCodeException(ILLEGAL_ARGUMENT,"这不是您的订单");
		Good good=order.getGood();
		if(good.getClass().equals(PackageGood.class)) {
			return (PackageGoodDto)converter.convert(good);
		} else {
			throw new ErrorCodeException(GOOD_TYPE_DISMISS);
		}
	}
	
	/**
	 * 检查商品是否完整
	 * @throws ErrorCodeException
	 */
	private void checkGoodCompletion(PackageGood good) throws ErrorCodeException {
		if(good.getArea()==null) {
			throw new ErrorCodeException(GOOD_INFO_MISSING, "必须选择好地区");
		}
	}
}
