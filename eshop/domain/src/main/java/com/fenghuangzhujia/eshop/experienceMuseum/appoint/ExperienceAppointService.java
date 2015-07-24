package com.fenghuangzhujia.eshop.experienceMuseum.appoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.experienceMuseum.ExperienceMuseum;
import com.fenghuangzhujia.eshop.experienceMuseum.ExperienceMuseumRepository;
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
	
	public PagedList<ExperienceAppointDto> findUserAppoints(String userId, int page, int size) {
		Pageable pageable=PageableBuilder.build(page, size);
		Page<ExperienceAppoint> appoints=repository.findByUserId(userId, pageable);
		Page<ExperienceAppointDto> result=appoints.map(getConverter());
		return new PagedList<>(result);
	}
	
	public ExperienceAppointDto getUserAppoint(String id, String userId) {
		ExperienceAppoint appoint=repository.findOne(id);
		if(appoint==null || !appoint.getUser().getId().equals(userId))return null;
		return getConverter().convert(appoint);
	}
	
	public ExperienceAppointDto appoint(String userId, String museumId, String realName, String mobile) {
		User user=userRepository.findOne(userId);
		if(user==null)
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "无效的用户");
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
