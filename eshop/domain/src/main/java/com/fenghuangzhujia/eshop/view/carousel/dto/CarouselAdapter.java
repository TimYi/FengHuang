package com.fenghuangzhujia.eshop.view.carousel.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.eshop.view.carousel.Carousel;
import com.fenghuangzhujia.eshop.view.navigation.Navigation;
import com.fenghuangzhujia.eshop.view.navigation.NavigationRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class CarouselAdapter extends AbstractDtoAdapter<Carousel, CarouselDto, CarouselInputArgs> {

	@Autowired
	private NavigationRepository navigationRepository;
	@Autowired	
	private MediaService mediaService;
	
	@Override
	public CarouselDto postConvert(Carousel d, CarouselDto t) {
		return t;
	}

	@Override
	public Carousel postConvertToDo(CarouselInputArgs i, Carousel d) {
		return postUpdate(i, d);
	}

	@Override
	public Carousel postUpdate(CarouselInputArgs i, Carousel d) {
		String pageId=i.getPageId();
		if(StringUtils.isNotBlank(pageId)) {
			Navigation page=navigationRepository.findOne(pageId);
			d.setPage(page);
		}
		MultipartFile picFile=i.getPicFile();
		if(picFile!=null) {
			try {
				MediaContent pic=d.getPic();
				pic=mediaService.update(pic, picFile);
				d.setPic(pic);
			} catch (Exception e) {
				LogUtils.errorLog(e);
			}			
		}
		return d;
	}

}
