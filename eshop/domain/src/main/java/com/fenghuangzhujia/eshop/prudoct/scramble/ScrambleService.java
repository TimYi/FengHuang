package com.fenghuangzhujia.eshop.prudoct.scramble;

import static com.fenghuangzhujia.eshop.core.base.SystemErrorCodes.*;

import java.text.MessageFormat;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.cases.DecorateCase;
import com.fenghuangzhujia.eshop.cases.DecorateCaseRepository;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrder;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrderService;
import com.fenghuangzhujia.eshop.commerce.order.dto.GoodOrderDto;
import com.fenghuangzhujia.eshop.commerce.order.dto.GoodOrderDtoConverter;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.couponsDef.CouponsAllocater;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.core.utils.CodeGenerater;
import com.fenghuangzhujia.eshop.message.Message;
import com.fenghuangzhujia.eshop.message.MessageRepository;
import com.fenghuangzhujia.eshop.prudoct.appoint.PackageAppoint;
import com.fenghuangzhujia.eshop.prudoct.appoint.PackageAppointRepository;
import com.fenghuangzhujia.eshop.prudoct.appoint.PackageAppointService;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackageRepository;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage.ScrambleStatus;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

/**
 * 抢购服务，
 * @author pc
 *
 */
@Service
@Transactional
public class ScrambleService {
	
	public static final String TYPE_CODE="DP";
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PackageAppointRepository appointRepository;
	@Autowired
	private DecoratePackageRepository decoratePackageRepository;
	@Autowired
	private DecorateCaseRepository decorateCaseRepository;
	@Autowired
	private PackageAppointService packageAppointService;
	@Autowired
	private PackageGoodRepository packageGoodRepository;
	@Autowired
	private GoodOrderService goodOrderService;
	@Autowired
	private GoodOrderDtoConverter converter;
	@Autowired
	private CouponsAllocater couponsAllocater;
	@Autowired
	private MessageRepository messageRepository;
	
	public GoodOrderDto scramble(String userId, String packageId, String caseId) {
		//验证是否有可用预约
		PackageAppoint appoint=packageAppointService.getAliveAppoint(userId, packageId);
		if(appoint==null || appoint.isUsed())
			throw new ErrorCodeException(NOT_APPOINTED, "您没有可用预约");
		
		//检验库存
		DecoratePackage decoratePackage=appoint.getDecoratePackage();
		if(decoratePackage.getInStock()<=decoratePackage.getSaleNumber()) {
			throw new ErrorCodeException(NO_GOOD, "已经没有库存！");
		}
		if(!decoratePackage.getStatus().equals(ScrambleStatus.SCRAMBLE)) {
			throw new ErrorCodeException(SystemErrorCodes.NOT_ON_SALE,"抢购尚未开始或已经结束");
		}
		
		//生成商品
		DecorateCase decorateCase;
		if(StringUtils.isBlank(caseId)) {
			decorateCase=null;
		} else {
			decorateCase=decorateCaseRepository.findOne(caseId);
		}
		PackageGood good=new PackageGood();
		good.setName(decoratePackage.getName());
		good.setPrice(decoratePackage.getDeposit());
		good.setRealPrice(decoratePackage.getDeposit());
		good.setDecorateCase(decorateCase);		
		good.setDecoratePackage(decoratePackage);
		good.setCity(appoint.getCity());
		good=packageGoodRepository.save(good);
		//消耗预约
		appoint.setUsed(true);		
		//消耗库存
		Long saleNumber=decoratePackage.getSaleNumber()+1;
		decoratePackage.setSaleNumber(saleNumber);
		//生成订单
		User user=appoint.getUser();
		double price=decoratePackage.getDeposit();
		int count=1;
		String mobile=appoint.getMobile();
		String realName=appoint.getRealName();
		//生成订单编号
		String code=CodeGenerater.generateCode(TYPE_CODE, appoint.getCity().getCode());
		GoodOrder order=goodOrderService.createOrderToPay(user, good, price, count, mobile, realName,code);
		
		//触发抢购套餐分发优惠券事件
		couponsAllocater.allocate(CouponsAllocater.SCRAMBLE, userId);
		
		//为用户留言
		Message message=new Message();
		message.setUser(user);
		message.setTitle("套餐预约成功");
		String pattern="尊进的客户您好，您已成功抢购{0}，请尽快完成支付。";
		String content=MessageFormat.format(pattern, decoratePackage.getName());
		message.setContent(content);
		message=messageRepository.save(message);
		
		GoodOrderDto result=converter.convert(order);
		return result;		
	}
}
