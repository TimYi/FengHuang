package com.fenghuangzhujia.eshop.experienceMuseum.appoint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fenghuangzhujia.eshop.experienceMuseum.appoint.ExperienceAppoint.AppointStatus;
import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface ExperienceAppointRepository extends SpecificationRepository<ExperienceAppoint, String> {

	Page<ExperienceAppoint> findByUserId(String userId, Pageable pageable);
	
	Page<ExperienceAppoint> findByStatus(AppointStatus status, Pageable pageable);
	
	Page<ExperienceAppoint> findByUserIdAndStatus(String userId, AppointStatus status, Pageable pageable);
}
