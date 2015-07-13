package com.fenghuangzhujia.eshop.view.carousel;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface CarouselRepository extends SpecificationRepository<Carousel, String> {

	@Query("select c from Carousel c join c.page p where p.id=?1")
	List<Carousel> findByPage(String pageId, Sort sort);
}
