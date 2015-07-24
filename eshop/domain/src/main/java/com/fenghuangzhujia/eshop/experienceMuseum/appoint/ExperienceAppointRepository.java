package com.fenghuangzhujia.eshop.experienceMuseum.appoint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface ExperienceAppointRepository extends SpecificationRepository<ExperienceAppoint, String> {

	Page<ExperienceAppoint> findByUserId(String userId, Pageable pageable);
}
