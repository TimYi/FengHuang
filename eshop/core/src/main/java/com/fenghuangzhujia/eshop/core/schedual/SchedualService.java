package com.fenghuangzhujia.eshop.core.schedual;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.schedual.activity.Activity;
import com.fenghuangzhujia.eshop.core.schedual.activity.ActivityRepository;
import com.fenghuangzhujia.foundation.validate.IValidater;

@Service
@Transactional
public class SchedualService {
	@Autowired
	private SchedualRepository repository;
	@Autowired
	private ActivityRepository activeRepository;
	@Autowired
	private IValidater<Schedual> validater;
	
	/**
	 * 新增时间表
	 * @param schedual
	 * @return
	 */
	public Schedual add(Schedual schedual) {
		repository.save(schedual);
		return schedual;
	}
	
	public void delete(String id) {
		repository.delete(id);
	}
	
	public void delete(Schedual schedual) {
		repository.delete(schedual);
	}
	
	public Schedual update(String id, Iterable<Operation> operations) {
		Schedual schedual=repository.findOne(id);
		return update(schedual, operations);
	}
	
	/**
	 * 通过一组对活动的操作集合更新schedual
	 * @param schedual
	 * @param operations
	 * @return
	 */
	public Schedual update(Schedual schedual, Iterable<Operation> operations) {
		if(schedual==null)return null;
		if(schedual.getActives()==null)schedual.setActives(new HashSet<>());
		for (Operation operation : operations) {
			if(operation.isDelete()) {
				deleteActive(schedual, operation.getId());
			}else {
				addActive(schedual, operation);
			}
		}
		validater.validate(schedual); //根据业务逻辑做一次检测，保证每个Activity符合指定规则，并且Activity之间没有时间冲突		
		repository.save(schedual);
		return schedual;
	}
	
	private void deleteActive(Schedual schedual, String id) {
		Activity a=schedual.getActive(id);
		if(a==null || a.isOccupied())return;//不能改动已经被占用的活动
		schedual.getActives().remove(a);
		activeRepository.delete(a);
	}
	
	private void addActive(Schedual schedual, Operation operation) {
		Activity a=new Activity();
		a.setDate(operation.getDate());
		a.setStartTime(operation.getStartTime());
		a.setEndTime(operation.getEndTime());
		a.setSchedual(schedual);
		schedual.getActives().add(a);
	}
	
	public Schedual get(String id) {
		Schedual schedual=repository.findOne(id);
		Hibernate.initialize(schedual.getActives());
		return schedual;
	}
	
	public List<Activity> check(String id, LocalDate startDate, LocalDate endDate) {
		Schedual schedual=repository.findOne(id);
		return check(schedual, startDate, endDate);
	}
	
	public List<Activity> check(Schedual schedual, LocalDate startDate, LocalDate endDate) {
		if(schedual==null || startDate==null || endDate==null)return null;
		return activeRepository.check(schedual, startDate, endDate);
	}
}
