package com.fenghuangzhujia.eshop.meteriaManage.brand.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.eshop.meteriaManage.brand.Brand;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class BrandAdapter extends AbstractDtoAdapter<Brand, BrandDto, BrandInputArgs> {
	
	@Autowired
	private MediaService mediaService;

	@Override
	public BrandDto postConvert(Brand d, BrandDto t) {
		return t;
	}

	@Override
	public Brand postConvertToDo(BrandInputArgs i, Brand d) {
		return postUpdate(i, d);
	}

	@Override
	public Brand postUpdate(BrandInputArgs i, Brand d) {
		MultipartFile logoFile=i.getLogoFile();
		if(logoFile!=null) {
			try {
				MediaContent logo=d.getLogo();
				logo=mediaService.update(logo, logoFile);
				d.setLogo(logo);
			} catch (Exception e) {
				LogUtils.errorLog(e);
			}
		}
		return d;
	}

}
