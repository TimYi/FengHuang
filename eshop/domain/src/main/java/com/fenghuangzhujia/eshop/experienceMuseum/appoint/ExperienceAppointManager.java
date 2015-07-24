package com.fenghuangzhujia.eshop.experienceMuseum.appoint;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.fenghuangzhujia.eshop.commerce.order.GoodOrder;
import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.rlmessage.MessageSender;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.utils.CodeGenerater;
import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.eshop.experienceMuseum.ExperienceMuseum;
import com.fenghuangzhujia.eshop.experienceMuseum.ExperienceMuseumRepository;
import com.fenghuangzhujia.eshop.prudoct.scramble.PackageGood;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.utils.Java8TimeUtils;

@Service
@Transactional
public class ExperienceAppointManager {
	
	@Autowired
	private ExperienceAppointRepository repository;
	@Autowired
	private ExperienceMuseumRepository museumRepository;
	@Autowired
	private MessageSender messageSender;
	
	/**
	 * 用户预约体验馆
	 * @param user
	 * @param museum
	 * @param realName
	 * @param mobile
	 * @return
	 */
	public ExperienceAppoint appointByUser(User user, ExperienceMuseum museum, String realName, String mobile) {
		if(user==null || museum==null) {
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "出现非法空参数");
		}
		if(!user.getInfoComplete())
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "用户信息不完整，不能预约");
		couldAppoint(user, museum);		
		ExperienceAppoint appoint=saveAppoint(user, museum, realName, mobile);		
		
		//发送预约成功提醒短信
		sendAppointSuccessMessage(appoint);
		return appoint;
	}
	
	/**
	 * 订单支付成功之后，自动预约体验馆
	 * @param order
	 * @return
	 */
	public ExperienceAppoint autoAppointAfterPay(GoodOrder order) {
		//找到同城第一家体验馆
		PackageGood good=(PackageGood)order.getGood();
		Area city=good.getCity();
		List<ExperienceMuseum> museums=museumRepository.findByCity(city);
		if(museums==null || museums.isEmpty()) return null;
		ExperienceMuseum museum=museums.get(0);		
		ExperienceAppoint appoint=saveAppoint(order.getUser(), museum, order.getRealName(), order.getMobile());
		
		//发送预约成功提醒短信
		sendAppointSuccessMessage(appoint);
		return appoint;		
	}
	
	/**
	 * 检测用户是否能预约体验馆，如果不能预约，抛出异常
	 * 同一家体验馆一个月内同一用户只能预约一次
	 * @param user
	 * @param museum
	 * @throws 如果不满足预约条件，抛出异常
	 */
	public void couldAppoint(User user, ExperienceMuseum museum) throws ErrorCodeException {
		Specification<ExperienceAppoint> spec=withinOneMonthSpecification(user, museum);
		List<ExperienceAppoint> appointsBefore=repository.findAll(spec);
		if(appointsBefore!=null && !appointsBefore.isEmpty()) {
			throw new ErrorCodeException(SystemErrorCodes.APPOINT_CONSTRAINED, "一个月内已经预约此种类型套餐，没人每月限约一次");
		}
	}
	
	private ExperienceAppoint saveAppoint(User user, ExperienceMuseum museum, String realName, String mobile) {
		ExperienceAppoint appoint=new ExperienceAppoint();
		appoint.setUser(user);
		appoint.setMuseum(museum);
		appoint.setRealName(realName);
		appoint.setMobile(mobile);
		String areaCode=museum.getCity().getCode();
		String code=CodeGenerater.generateCode(ExperienceAppoint.TYPE_CODE, areaCode);
		appoint.setCode(code);
		appoint=repository.save(appoint);
		return appoint;
	}
	
	private Specification<ExperienceAppoint> withinOneMonthSpecification(User user,ExperienceMuseum museum) {
		return new Specification<ExperienceAppoint>() {			
			@Override
			public Predicate toPredicate(Root<ExperienceAppoint> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				Path<User> sameUser=root.get("user");
				predicates.add(cb.equal(sameUser, user));
				
				Path<ExperienceMuseum> sameMuseum=root.get("museum");
				predicates.add(cb.equal(sameMuseum, museum));
				
				Path<Date> createAt=root.get("createTime");
				LocalDate oneMonthBefore=LocalDate.now().minusMonths(1L);
				Date date=Java8TimeUtils.fromLocalDate(oneMonthBefore);
				predicates.add(cb.greaterThan(createAt, date));
				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
	
	private void sendAppointSuccessMessage(ExperienceAppoint appoint) {
		try {
			//发送预约成功提示短信
			messageSender.appointSuccess(appoint.getMobile(), appoint.getCreateTime(), 
					appoint.getMuseum().getCity().getName(),appoint.getCode(), appoint.getRealName(), appoint.getMobile());
		} catch (Exception e) {
			LogUtils.errorLog(e);
		}
	}
}
