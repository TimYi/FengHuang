package com.fenghuangzhujia.eshop.prudoct.packages.dto;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class DecoratePackageDtoAdapter extends AbstractDtoAdapter<DecoratePackage, DecoratePackageDto, DecoratePackageInputArgs> {

	@Autowired
	private MediaService mediaService;
	
	@Override
	public DecoratePackageDto postConvert(DecoratePackage d,
			DecoratePackageDto t) {
		return t;
	}

	@Override
	public DecoratePackage postConvertToDo(DecoratePackageInputArgs t,
			DecoratePackage d) {
		return postUpdate(t, d);
	}

	@Override
	public DecoratePackage postUpdate(DecoratePackageInputArgs t, DecoratePackage d) {
		try {
			MultipartFile mainPicFile=t.getMainPicFile();
			if(mainPicFile!=null) {
				MediaContent mainPic=d.getMainPic();
				mainPic=mediaService.update(mainPic, mainPicFile);
				d.setMainPic(mainPic);
			}
			return d;
		} catch (IOException e) {
			throw new ErrorCodeException(SystemErrorCodes.FILE_ERROR, e.getMessage());
		}
	}

}
