package com.fenghuangzhujia.eshop.view.decorateTechnology.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.eshop.view.decorateTechnology.DecorateTechnology;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class DecorateTechnologyAdapter extends 
	AbstractDtoAdapter<DecorateTechnology, DecorateTechnologyDto, DecorateTechnologyInputArgs> {

	@Autowired
	private MediaService mediaService;
	
	@Override
	public DecorateTechnologyDto postConvert(DecorateTechnology d,
			DecorateTechnologyDto t) {
		return t;
	}

	@Override
	public DecorateTechnology postConvertToDo(DecorateTechnologyInputArgs i,
			DecorateTechnology d) {
		return postUpdate(i, d);
	}

	@Override
	public DecorateTechnology postUpdate(DecorateTechnologyInputArgs i,
			DecorateTechnology d) {
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
