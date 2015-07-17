package com.fenghuangzhujia.eshop.view.carousel.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.eshop.templateEngine.fragment.TemplateFragment;
import com.fenghuangzhujia.eshop.templateEngine.fragment.TemplateFragmentRepository;
import com.fenghuangzhujia.eshop.templateEngine.fragment.TemplateFragmentService;
import com.fenghuangzhujia.eshop.templateEngine.fragment.dto.TemplateFragmentDto;
import com.fenghuangzhujia.eshop.templateEngine.fragment.dto.TemplateFragmentInputArgs;
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
	@Autowired
	private TemplateFragmentService templateFragmentService;
	@Autowired
	private TemplateFragmentRepository templateFragmentRepository;
	
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
		
		TemplateFragmentInputArgs fragmentInfo=i.getFragmentInfo();
		if(fragmentInfo!=null) {
			//记录老模板，更新出新模板之后，删除老模板
			TemplateFragment oldFragment=d.getFragment();
			TemplateFragmentDto dto=templateFragmentService.add(fragmentInfo);
			TemplateFragment fragment=templateFragmentRepository.findOne(dto.getId());
			d.setFragment(fragment);
			if(oldFragment!=null) {
				templateFragmentRepository.delete(oldFragment);
			}
		}

		return d;
	}

}
