package com.fenghuangzhujia.eshop.experienceMuseum;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.experienceMuseum.dto.ExperienceMuseumDto;
import com.fenghuangzhujia.eshop.experienceMuseum.dto.ExperienceMuseumInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class ExperienceMuseumService extends 
	DtoSpecificationService<ExperienceMuseum, ExperienceMuseumDto, ExperienceMuseumInputArgs, String> {

}
