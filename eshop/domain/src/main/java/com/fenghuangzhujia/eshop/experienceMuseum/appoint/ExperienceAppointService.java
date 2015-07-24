package com.fenghuangzhujia.eshop.experienceMuseum.appoint;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.core.validate.message.MessageManager;
import com.fenghuangzhujia.eshop.experienceMuseum.ExperienceMuseum;
import com.fenghuangzhujia.eshop.experienceMuseum.ExperienceMuseumRepository;
import com.fenghuangzhujia.eshop.experienceMuseum.appoint.ExperienceAppoint.AppointStatus;
import com.fenghuangzhujia.eshop.experienceMuseum.appoint.dto.ExperienceAppointDto;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.persistance.PageableBuilder;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;

@Service
@Transactional
public class ExperienceAppointService {

	@Autowired
	private ExperienceAppointRepository repository;
	@Autowired
	private ExperienceAppointManager manager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private ExperienceMuseumRepository museumRepository;
	@Autowired
	private MessageManager messageManager;
	
	public PagedList<ExperienceAppointDto> findUserAppoints(String userId, int page, int size, AppointStatus status) {
		Pageable pageable=PageableBuilder.build(page, size);
		Page<ExperienceAppoint> appoints;
		if(status==null) {
			appoints=repository.findByUserId(userId, pageable);
		} else {
			appoints=repository.findByUserIdAndStatus(userId, status, pageable);
		}
		Page<ExperienceAppointDto> result=appoints.map(getConverter());
		return new PagedList<>(result);
	}
	
	public ExperienceAppointDto getUserAppoint(String id, String userId) {
		ExperienceAppoint appoint=repository.findOne(id);
		if(appoint==null || !appoint.getUser().getId().equals(userId))return null;
		return getConverter().convert(appoint);
	}
	
	public PagedList<ExperienceAppointDto> findAppoints(int page, int size, AppointStatus status) {
		Pageable pageable=PageableBuilder.build(page, size);
		Page<ExperienceAppoint> appoints;
		if(status==null) {
			appoints=repository.findAll(pageable);
		} else {
			appoints=repository.findByStatus(status, pageable);
		}
		Page<ExperienceAppointDto> result=appoints.map(getConverter());
		return new PagedList<>(result);
	}
	
	public ExperienceAppointDto findAppoint(String id) {
		ExperienceAppoint result=repository.findOne(id);
		return getConverter().convert(result);
	}
	
	public void calcelAppoint(String id) {
		ExperienceAppoint appoint=repository.findOne(id);
		manager.cancelAppoint(appoint);
	}
	
	public void cancelAppointByUser(String userId, String id) {
		User user=userRepository.findOne(userId);
		ExperienceAppoint appoint=repository.findOne(id);
		manager.cancelAppointByUser(user, appoint);
	}
	
	public void processAppoint(String id, String userId, String message, Date appointTime) {
		ExperienceAppoint appoint=repository.findOne(id);
		User waiter=userRepository.findOne(userId);
		manager.processAppoint(appoint, waiter, message, appointTime);
	}
	
	/**
	 * 预约
	 * @param userId
	 * @param museumId
	 * @param realName
	 * @param mobile 暂时无用，用用户注册的mobile代替
	 * @return
	 */
	public ExperienceAppointDto appoint(String userId, String museumId, String realName, String mobile, String validater) {		
		User user=userRepository.findOne(userId);
		if(user==null)
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "无效的用户");
		mobile=user.getMobile();
		messageManager.validate(mobile, validater);
		ExperienceMuseum museum=museumRepository.findOne(museumId);
		if(museum==null)
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "体验馆id不存在");
		ExperienceAppoint appoint=manager.appointByUser(user, museum, realName, mobile);
		return getConverter().convert(appoint);
	}
	
	private Converter<ExperienceAppoint, ExperienceAppointDto> getConverter() {
		return new Converter<ExperienceAppoint, ExperienceAppointDto>() {
			@Override
			public ExperienceAppointDto convert(ExperienceAppoint source) {
				return BeanMapper.map(source, ExperienceAppointDto.class);
			}
		};
	}
}
