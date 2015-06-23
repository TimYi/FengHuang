package com.fenghuangzhujia.eshop.live.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.live.Live;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class LiveAdapter extends AbstractDtoAdapter<Live, LiveDto, LiveInputArgs> {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MediaService mediaService;
	
	@Override
	public LiveDto postConvert(Live d, LiveDto t) {
		return t;
	}

	@Override
	public Live postConvertToDo(LiveInputArgs i, Live d) {
		return postUpdate(i, d);
	}

	@Override
	public Live postUpdate(LiveInputArgs i, Live d) {
		String userId=i.getUserId();
		if(StringUtils.isNotBlank(userId)) {
			User user=userRepository.findOne(userId);
			d.setUser(user);
		}		
		MultipartFile mainPicFile=i.getMainPicFile();
		try {
			if(mainPicFile!=null) {
				MediaContent mainPic=d.getMainPic();
				mainPic=mediaService.update(mainPic, mainPicFile);
				d.setMainPic(mainPic);
			}
		} catch (Exception e) {
			throw new ErrorCodeException(SystemErrorCodes.OTHER, e);
		}		
		return d;
	}

}
