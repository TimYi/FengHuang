package com.fenghuangzhujia.eshop.user.collect.dto;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.user.collect.Collect;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class CollectDtoAdapter extends AbstractDtoAdapter<Collect, CollectDto> {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MediaService mediaService;

	@Override
	public CollectDto postConvert(Collect d, CollectDto t) {
		t.setUserid(d.getUser().getId());
		return t;
	}

	@Override
	public Collect postConvertToDo(CollectDto t, Collect d) {
		return postUpdate(t, d);
	}

	@Override
	public Collect postUpdate(CollectDto t, Collect d) {
		String userid=t.getUserid();
		if(StringUtils.isNotBlank(userid)) {
			User user=userRepository.findOne(userid);
			d.setUser(user);
		}
		MultipartFile mainPicFile=t.getMainPicFile();
		if(mainPicFile!=null) {
			try {
				MediaContent media=d.getMainPic();
				media=mediaService.update(media, mainPicFile);
				d.setMainPic(media);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return d;
	}
}
