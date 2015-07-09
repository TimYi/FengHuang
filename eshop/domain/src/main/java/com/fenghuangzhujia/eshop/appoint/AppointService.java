package com.fenghuangzhujia.eshop.appoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.appoint.dto.AppointDto;
import com.fenghuangzhujia.eshop.appoint.dto.AppointInputArgs;
import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.area.AreaRepository;
import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.eshop.core.base.Dics;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.commerce.couponsDef.CouponsAllocater;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrder;
import com.fenghuangzhujia.eshop.core.commerce.order.GoodOrderRepository;
import com.fenghuangzhujia.eshop.core.remind.impl.DtoUnreadRemindSpecificationService;
import com.fenghuangzhujia.eshop.core.rlmessage.MessageSender;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.core.utils.CodeGenerater;
import com.fenghuangzhujia.eshop.core.validate.message.MessageManager;
import com.fenghuangzhujia.eshop.prudoct.scramble.PackageGood;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.dics.Category;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;
import com.fenghuangzhujia.foundation.dics.CategoryRepository;

@Service
@Transactional
public class AppointService extends DtoUnreadRemindSpecificationService<Appoint, AppointDto, AppointInputArgs, String> {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CategoryItemRepository categoryItemRepository;
	@Autowired
	private MessageManager messageManager;
	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private AppointValidater appointValidater;
	@Autowired
	private CouponsAllocater couponsAllocater;
	@Autowired
	private GoodOrderRepository goodOrderRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private MessageSender messageSender;
	
	/**
	 * 为用户预约设置单独的方法，将业务逻辑封装在里面
	 * @param userid
	 * @param areaid
	 * @param mobile
	 * @return
	 */
	public AppointDto appointByUser(AppointInputArgs args) {
		Appoint appoint=new Appoint();
		User user=userRepository.findOne(args.getUserId());
		if(user==null)throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "用户id错误");
		//用户信息完整才能预约
		if(!user.getInfoComplete())
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "用户信息不完整，不能预约");
		appoint.setUser(user);
		
		//获取预约类型
		CategoryItem type=categoryItemRepository.findOne(args.getTypeId()); 
		if(type==null || !type.getType().equals(Dics.APPOINT_TYPE))
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "预约类型错误");
		appoint.setType(type);
		//检测用户是否满足预约条件
		appointValidater.couldAppoint(user, type);
		
		//暂时只允许用户用绑定手机号码预约，不能更换手机号。如果以后业务逻辑调整，这里要做相应调整。
		String mobile=user.getMobile();
		//检验短信验证码
		messageManager.validate(mobile, args.getValidater());
		appoint.setMobile(mobile);
		//使用用户绑定真实姓名，无视前端传来的参数
		String realName=user.getRealName();
		appoint.setRealName(realName);
		
		//获取用户选择的城市
		Area city=areaRepository.findOne(args.getCityId());
		if(city==null || !city.getLevel().equals(AreaLevel.CITY))
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "城市id错误");
		appoint.setCity(city);
		
		//为预约按照编码规则分配可记忆的唯一编码
		String areaCode=appoint.getCity().getCode();
		String code=CodeGenerater.generateCode(Appoint.TYPE_CODE, areaCode);
		appoint.setCode(code);
		
		appoint=getRepository().save(appoint);
		
		//触发预约服务成功分发优惠券
		couponsAllocater.allocate(CouponsAllocater.APPOINT_SERVICE, user.getId());
		
		//发送预约成功提示短信
		messageSender.appointSuccess(mobile);
		
		return adapter.convertToDetailedDto(appoint);
	}
	
	/**
	 * 支付订单成功之后，自动预约体验馆
	 * @param orderId
	 * @return
	 */
	public AppointDto appointAfterPay(String orderId) {
		GoodOrder order=goodOrderRepository.findOne(orderId);
		Appoint appoint=new Appoint();
		appoint.setUser(order.getUser());
		appoint.setMobile(order.getUser().getMobile());
		appoint.setRealName(order.getUser().getRealName());
		PackageGood good=(PackageGood)order.getGood();
		appoint.setCity(good.getCity());		
		
		//只添加体验馆一种数据。用这种方法进行预约（临时）
		Category type=categoryRepository.getByType(Dics.APPOINT_TYPE);
		CategoryItem tiyanguan=type.getItems().iterator().next();
		appoint.setType(tiyanguan);
		
		//为预约按照编码规则分配可记忆的唯一编码
		String areaCode=appoint.getCity().getCode();
		String code=CodeGenerater.generateCode(Appoint.TYPE_CODE, areaCode);
		appoint.setCode(code);
		
		appoint=getRepository().save(appoint);
		return adapter.convertToDetailedDto(appoint);
	}
	
	public PagedList<AppointDto> findByTypeId(String typeid, int page, int size) {
		PageRequest pageable=new PageRequest(page, size);
		Page<Appoint> list=getRepository().findByTypeId(typeid, pageable);
		Page<AppointDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public PagedList<AppointDto> findByUserId(String userid, int page, int size) {
		PageRequest pageable=new PageRequest(page-1, size);
		Page<Appoint> list=getRepository().findByUserId(userid, pageable);
		Page<AppointDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public AppointDto getUserAppoint(String userid, String id) {
		Appoint appoint=getRepository().findOne(id);
		if(!appoint.getUser().getId().equals(userid))return null;
		return adapter.convert(appoint);
	}
	
	@Autowired
	public void setAppointRepository(AppointRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public AppointRepository getRepository() {
		return (AppointRepository)super.getRepository();
	}
}
