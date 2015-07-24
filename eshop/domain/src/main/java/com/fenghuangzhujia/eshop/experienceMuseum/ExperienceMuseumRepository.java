package com.fenghuangzhujia.eshop.experienceMuseum;

import java.util.List;

import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface ExperienceMuseumRepository extends SpecificationRepository<ExperienceMuseum, String> {

	public List<ExperienceMuseum> findByCity(Area city);
}
