package com.fenghuangzhujia.eshop.commerce.order;

import static com.fenghuangzhujia.eshop.core.base.SystemErrorCodes.ILLEGAL_ARGUMENT;

import java.util.Map;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fenghuangzhujia.eshop.commerce.goods.Good;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.commerce.order.dto.GoodOrderDto;
import com.fenghuangzhujia.eshop.commerce.order.dto.GoodOrderDtoConverter;
import com.fenghuangzhujia.eshop.commerce.pay.OrderPay;
import com.fenghuangzhujia.eshop.commerce.pay.OrderPayRepository;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.prudoct.appoint.PackageAppointService;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.eshop.prudoct.scramble.PackageGood;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.persistance.DynamicSpecifications;
import com.fenghuangzhujia.foundation.core.persistance.PageableBuilder;
import com.fenghuangzhujia.foundation.core.persistance.SearchFilter;
import com.fenghuangzhujia.foundation.core.persistance.SortFilter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Service
@Transactional
public class GoodOrderService {
	
	@Autowired
	private GoodOrderDtoConverter converter;
	@Autowired
	private GoodOrderRepository repository;
	@Autowired
	private OrderPayRepository orderPayRepository;
	@Autowired
	private PackageAppointService packageAppointService;
	
	/** 生成未支付订单 */
	public GoodOrder createOrderToPay(User user, Good good, double price, int count, String mobile, String realName, String code) {
		GoodOrder order=new GoodOrder();
		order.setUser(user);
		order.setGood(good);
		order.setPrice(price);
		order.setCount(count);
		order.setMobile(mobile);
		order.setRealName(realName);
		order.setStatus(OrderStatus.WAITING);
		order.setCode(code);
		order=repository.save(order);
		
		OrderPay pay=new OrderPay();
		//pay.setOrder(order);
		order.setPayment(pay);//注意关联的掌控端在order一侧
		pay.setTotalMoney(order.getPrice());
		pay.setShouldPayMoney(order.getPrice());
		orderPayRepository.save(pay);
		
		return order;
	}
	
	public PagedList<GoodOrderDto> findByUser(String userid, int page, int size, OrderStatus status) {
		PageRequest pageable=new PageRequest(page-1, size);
		Page<GoodOrder> goods;
		if(status==null) {
			goods=repository.findByUserId(userid, pageable);
		} else {
			goods=repository.findByUserIdAndStatus(userid, status, pageable);
		}
		Page<GoodOrderDto> result=goods.map(converter);
		return new PagedList<>(result);
	}
	
	public PagedList<GoodOrderDto> findByStatus(int page, int size, OrderStatus status) {
		Sort sort=new Sort(new Sort.Order(Direction.DESC, "updateTime"));
		Pageable pageable=PageableBuilder.build(sort, page, size);
		Page<GoodOrder> goods;
		if(status==null) {
			goods=repository.findAll(pageable);
		} else {
			goods=repository.findByStatus(status, pageable);
		}
		Page<GoodOrderDto> result=goods.map(converter);
		return new PagedList<>(result);
	}
	
	public GoodOrderDto findOneByUser(String userid, String id) {
		GoodOrder order=repository.findOne(id);
		if(!order.getUser().getId().equals(userid))throw new ErrorCodeException(ILLEGAL_ARGUMENT,"这不是您的订单");
		return converter.convert(order);
	}
	
	public Long countByStatus(OrderStatus status) {
		return repository.countByStatus(status);
	}
	
	public Long countByUserAndStatus(String userId, OrderStatus status) {
		return repository.countByUserIdAndStatus(userId, status);
	}
	
	
	//以下为后台管理用到接口
	
	public PagedList<GoodOrderDto> findAll(int page, int size,
			Map<String, Object> filters, Map<String, Object> orders) {
		Pageable pageable=SortFilter.toPageable(page, size, orders);
		Map<String, SearchFilter> filterMap=SearchFilter.parse(filters);
		Specification<GoodOrder> specification=DynamicSpecifications.bySearchFilter(filterMap.values(), null);
		Page<GoodOrder> dlist=repository.findAll(specification, pageable);
		Page<GoodOrderDto> list=dlist.map(converter);
		return new PagedList<>(list);
	}
	
	public GoodOrderDto findOne(String id) {
		GoodOrder order=repository.findOne(id);
		return converter.convert(order);
	}
	
	public void changeStatus(String id, OrderStatus status) {
		GoodOrder order=repository.findOne(id);
		order.setStatus(status);
	}
	
	public void calcelOrder(String id, String userId) {
		GoodOrder order=repository.findOne(id);
		if(!order.getUser().getId().equals(userId))
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "这不是您的订单");
		if(order.getStatus()!=OrderStatus.WAITING)
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "只能取消尚未支付的订单");
		changeStatus(id, OrderStatus.CANCEL);
		
		//重置预约
		DecoratePackage dPackage=((PackageGood)order.getGood()).getDecoratePackage();
		packageAppointService.resetAppoint(userId, dPackage.getId());
	}
}
