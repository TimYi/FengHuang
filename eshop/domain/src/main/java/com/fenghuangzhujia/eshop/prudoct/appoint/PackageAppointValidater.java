package com.fenghuangzhujia.eshop.prudoct.appoint;

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
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.utils.Java8TimeUtils;

@Component
public class PackageAppointValidater {

	@Autowired
	private PackageAppointRepository appointRepository;

	/**如果不能预约，抛出异常*/
	public void couldAppoint(User user, DecoratePackage decoratePackage) throws ErrorCodeException {
		if(user==null || decoratePackage==null)
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "出现空参数");
		//验证逻辑：从数据库中查询用户一个月之内，某种类型的预约，如果不为空，则抛出异常
		Specification<PackageAppoint> spec=getValidateSpecification(user,decoratePackage);
		PackageAppoint appoint=appointRepository.findOne(spec);
		if(appoint!=null)
			throw new ErrorCodeException(SystemErrorCodes.APPOINT_CONSTRAINED, "一个月内已经预约此种类型套餐");		
	}
	
	/**
	 * 获取可用的预约
	 * “可用”这个概念，和业务规则相关，因此也封装到validater中
	 * 目前，“可用”的意思是一个月内的预约。
	 * 至于预约是否已经被使用，不在这里做判断。
	 * @param user
	 * @param decoratePackage
	 * @return
	 */
	@Transactional(readOnly=true)
	public PackageAppoint getAliveAppoint(User user, DecoratePackage decoratePackage) {
		if(user==null || decoratePackage==null)
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "出现空参数");
		//验证逻辑：从数据库中查询用户一个月之内，某种类型的预约，如果不为空，则抛出异常
		Specification<PackageAppoint> spec=getValidateSpecification(user,decoratePackage);
		PackageAppoint appoint=appointRepository.findOne(spec);
		return appoint;
	}
	
	/**获取一个月之内，某个用户，某个预约类型的查询条件*/
	private Specification<PackageAppoint> getValidateSpecification(User user, DecoratePackage decoratePackage) {
		return new Specification<PackageAppoint>() {			
			@Override
			public Predicate toPredicate(Root<PackageAppoint> root, CriteriaQuery<?> query,
					CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				
				Path<User> sameUser=root.get("user");
				predicates.add(cb.equal(sameUser, user));
				
				Path<DecoratePackage> sameType=root.get("decoratePackage");
				predicates.add(cb.equal(sameType, decoratePackage));
				
				Path<Date> createAt=root.get("createTime");
				LocalDate oneMonthBefore=LocalDate.now().minusMonths(1L);
				Date date=Java8TimeUtils.fromLocalDate(oneMonthBefore);
				predicates.add(cb.greaterThan(createAt, date));
				
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
	}
}
