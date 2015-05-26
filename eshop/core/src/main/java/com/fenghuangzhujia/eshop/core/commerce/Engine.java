package com.fenghuangzhujia.eshop.core.commerce;

import static com.fenghuangzhujia.eshop.core.base.SystemErrorCodes.*;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.NotImplementedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.commerce.cargo.CargoOrder;
import com.fenghuangzhujia.eshop.core.commerce.cargo.CargoOrderRepository;
import com.fenghuangzhujia.eshop.core.commerce.eshop.Shop;
import com.fenghuangzhujia.eshop.core.commerce.goods.Good;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder;
import com.fenghuangzhujia.eshop.core.commerce.order.Order;
import com.fenghuangzhujia.eshop.core.commerce.order.OrderRepository;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

/**
 * 商业引擎
 * 处理用户下单等内容
 * @author pc
 *
 */
@Transactional
@Component
public class Engine {
	
	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private CargoOrderRepository cargoOrderRepository;
	@Autowired
	private Processor processor;
	
	/**
	 * 用户从购物车下单，购买商品
	 * @param user
	 * @param shop
	 * @param cargoOrders
	 * @return
	 */
	public Order order(User user, Shop shop, Set<CargoOrder> cargoOrders) {
		if(user==null || shop==null || cargoOrders==null)throw new ErrorCodeException(ILLEGAL_ARGUMENT,"请检查是否有非法空参数");
		for (CargoOrder cargoOrder : cargoOrders) {
			if(!cargoOrder.getShop().getId().equals(shop.getId()))throw new ErrorCodeException(ILLEGAL_ARGUMENT, "无法为不同店铺商品合并下单！");
			if(!cargoOrder.getUser().getId().equals(user.getId()))throw new ErrorCodeException(ILLEGAL_ARGUMENT, "只能为用户下单本人购物车内商品！");
		}
		Order order=new Order();
		order.setBuyer(user);
		order.setSolder(shop);
		Set<GoodOrder> goodOrders=new HashSet<>();
		for (CargoOrder cargoOrder : cargoOrders) {
			Good good=cargoOrder.getGood();
			Integer count=cargoOrder.getCount();
			GoodOrder goodOrder=new GoodOrder();
			goodOrder.setGood(good);
			goodOrder.setPrice(good.getRealPrice());
			goodOrder.setCount(count);
			goodOrder.setOrder(order);//添加订单
			goodOrders.add(goodOrder);
			//对每个商品订单进行检查，比如库存，如果不能购买此商品，抛出异常
			processor.process(goodOrder);
		}
		order.setGoodOrders(goodOrders);
		order=orderRepository.save(order);
		//删除购物车中的已购买商品
		cargoOrderRepository.delete(cargoOrders);
		return order;
	}
	
	/**
	 * 用户直接下单购买商品
	 * @param user
	 * @param shop
	 * @param good
	 * @param count
	 * @return
	 */
	public Order order(User user, Shop shop, Good good, int count) {
		if(user==null || shop==null || good==null || count<1)throw new ErrorCodeException(ILLEGAL_ARGUMENT);
		Order order=new Order();
		order.setBuyer(user);
		order.setSolder(shop);
		GoodOrder goodOrder=new GoodOrder();
		goodOrder.setOrder(order);
		goodOrder.setGood(good);
		goodOrder.setPrice(good.getRealPrice());
		goodOrder.setCount(count);
		Set<GoodOrder> goodOrders=new HashSet<>();
		goodOrders.add(goodOrder);
		//对每个商品订单进行检查，比如库存，如果不能购买此商品，抛出异常
		processor.process(goodOrder);
		order.setGoodOrders(goodOrders);
		order=orderRepository.save(order);
		return order;
	}
	
	/**
	 * 将商品加入购物车
	 * @param user
	 * @param good
	 * @param count
	 * @return
	 */
	public CargoOrder addToCargo(User user, Good good, int count) {
		CargoOrder cargoOrder=new CargoOrder();
		cargoOrder.setUser(user);
		cargoOrder.setGood(good);
		cargoOrder.setCount(count);
		cargoOrder=cargoOrderRepository.save(cargoOrder);
		return cargoOrder;
	}
	
	/**
	 * 将商品移除出购物车
	 * @param cargoOrder
	 */
	public void removeFromCargo(CargoOrder cargoOrder) {
		cargoOrderRepository.delete(cargoOrder);
	}
	
	/**
	 * 客户申请取消订单
	 * 具体的取消过程在此方法中封装
	 * @param order
	 * @return
	 */
	public Order cancel(Order order) {
		throw new NotImplementedException();
	}
}
