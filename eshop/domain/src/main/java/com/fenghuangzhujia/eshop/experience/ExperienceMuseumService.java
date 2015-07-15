package com.fenghuangzhujia.eshop.experience;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.experience.dto.ExperienceMuseumDto;
import com.fenghuangzhujia.eshop.experience.dto.ExperienceMuseumInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class ExperienceMuseumService extends 
	DtoSpecificationService<ExperienceMuseum, ExperienceMuseumDto, ExperienceMuseumInputArgs, String> {

}
