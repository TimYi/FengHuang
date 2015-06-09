package com.fenghuangzhujia.eshop.collect.dto;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.collect.Collect;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class CollectDtoAdapter extends AbstractDtoAdapter<Collect, CollectDto, CollectInputArgs> {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MediaService mediaService;
	
	@Override
	public CollectDto postConvert(Collect d, CollectDto t) {
		return t;
	}

	@Override
	public Collect postConvertToDo(CollectInputArgs t, Collect d) {
		String userid=t.getUserid();
		User user=userRepository.findOne(userid);
		d.setUser(user);
		d=postUpdate(t, d);
		return d;
	}

	@Override
	public Collect postUpdate(CollectInputArgs t, Collect d) {
		MultipartFile file=t.getMainPicFile();
		if(file!=null) {
			try {
				MediaContent media=d.getMainPic();
				media=mediaService.update(media, file);
				d.setMainPic(media);
			} catch (IOException e) {
				throw new ErrorCodeException(SystemErrorCodes.OTHER);
			}			
		}
		return d;
	}
}
