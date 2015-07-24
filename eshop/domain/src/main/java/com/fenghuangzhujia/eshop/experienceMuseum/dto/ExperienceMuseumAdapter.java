package com.fenghuangzhujia.eshop.experienceMuseum.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.area.Area;
import com.fenghuangzhujia.eshop.core.area.AreaRepository;
import com.fenghuangzhujia.eshop.core.area.Area.AreaLevel;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.eshop.experienceMuseum.ExperienceMuseum;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class ExperienceMuseumAdapter extends 
	AbstractDtoAdapter<ExperienceMuseum, ExperienceMuseumDto, ExperienceMuseumInputArgs> {

	@Autowired
	private AreaRepository areaRepository;
	@Autowired
	private MediaService mediaService;
	
	@Override
	public ExperienceMuseumDto postConvert(ExperienceMuseum d,
			ExperienceMuseumDto t) {
		return t;
	}

	@Override
	public ExperienceMuseum postConvertToDo(ExperienceMuseumInputArgs i,
			ExperienceMuseum d) {
		return postUpdate(i, d);
	}

	@Override
	public ExperienceMuseum postUpdate(ExperienceMuseumInputArgs i,
			ExperienceMuseum d) {
		String cityId=i.getCityId();
		if(StringUtils.isNotBlank(cityId)) {
			Area city=areaRepository.findOne(cityId);
			if(city.getLevel()!=AreaLevel.CITY) {
				throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "区域等级必须为城市");
			}
			d.setCity(city);
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
