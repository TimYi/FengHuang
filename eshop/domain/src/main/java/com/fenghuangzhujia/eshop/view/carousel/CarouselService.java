package com.fenghuangzhujia.eshop.view.carousel;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.view.carousel.dto.CarouselDto;
import com.fenghuangzhujia.eshop.view.carousel.dto.CarouselInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class CarouselService extends DtoSpecificationService<Carousel, CarouselDto, CarouselInputArgs, String> {

	@Override
	public CarouselRepository getRepository() {
		return (CarouselRepository)super.getRepository();
	}
	
	public List<CarouselDto> findByPage(String pageId) {
		Sort sort=new Sort(new Sort.Order("ordernum"));
		List<Carousel> carousels=getRepository().findByPage(pageId,sort);
		return adapter.convertDoList(carousels);
	}
	
	public void reOrder(Map<String, Integer> orders) {
		for (Entry<String, Integer> order : orders.entrySet()) {
			String id=order.getKey();
			int ordernum=order.getValue();
			Carousel carousel=getRepository().findOne(id);
			carousel.setOrdernum(ordernum);
		}
	}
}
