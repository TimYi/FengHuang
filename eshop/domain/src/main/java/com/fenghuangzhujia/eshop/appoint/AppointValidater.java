package com.fenghuangzhujia.eshop.appoint;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.utils.Java8TimeUtils;

/**
 * 用于验证用户是否能够预约某种服务
 * 当前业务限制：注册用户，没人每月每种服务限约一套
 * @author pc
 *
 */
@Component
public class AppointValidater {
	
	@Autowired
	private AppointRepository appointRepository;

	/**如果不能预约，抛出异常*/
	public void couldAppoint(User user, CategoryItem appointType) throws ErrorCodeException {
		if(user==null || appointType==null)
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "出现空参数");
		//验证逻辑：从数据库中查询用户一个月之内，某种类型的预约，如果不为空，则抛出异常
		Specification<Appoint> spec=getValidateSpecification(user,appointType);
		Appoint appoint=appointRepository.findOne(spec);
		if(appoint!=null)
			throw new ErrorCodeException(SystemErrorCodes.APPOINT_CONSTRAINED, "一个月内已经预约此种类型套餐");		
	}
	
	/**获取一个月之内，某个用户，某个预约类型的查询条件*/
	private Specification<Appoint> getValidateSpecification(User user, CategoryItem appointType) {
		return new Specification<Appoint>() {			
			@Override
			public Predicate toPredicate(Root<Appoint> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				Path<User> sameUser=root.get("user");
				predicates.add(cb.equal(sameUser, user));
				
				Path<CategoryItem> sameType=root.get("type");
				predicates.add(cb.equal(sameType, appointType));
				
				Path<Date> createAt=root.get("createTime");
				LocalDate oneMonthBefore=LocalDate.now().minusMonths(1L);
				Date date=Java8TimeUtils.fromLocalDate(oneMonthBefore);
				predicates.add(cb.greaterThan(createAt, date));
				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}
