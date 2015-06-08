package com.fenghuangzhujia.eshop.core.user.dto;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class UserDtoAdapter extends AbstractDtoAdapter<User, UserDto, UserInputArgs> {
	
	@Autowired
	private CategoryItemRepository categoryItemRepository;
	@Autowired
	private MediaService mediaService;

	@Override
	public UserDto postConvert(User d, UserDto t) {
		return t;
	}

	@Override
	public User postConvertToDo(UserInputArgs i, User d) {
		return postUpdate(i, d);
	}

	@Override
	public User postUpdate(UserInputArgs i, User d) {
		String sexId=i.getSexId();
		if(StringUtils.isNotBlank(sexId)) {
			CategoryItem sex=categoryItemRepository.findOne(sexId);
			d.setSex(sex);
		}
		String bloodTypeId=i.getBloodTypeId();
		if(StringUtils.isNotBlank(bloodTypeId)) {
			CategoryItem bloodType=categoryItemRepository.findOne(bloodTypeId);
			d.setBloodType(bloodType);
		} 
		String constellationId=i.getConstellationId();
		if(StringUtils.isNotBlank(constellationId)) {
			CategoryItem constellation=categoryItemRepository.findOne(constellationId);
			d.setConstellation(constellation);
		}
		MultipartFile avatarFile=i.getAvatarFile();
		try {
			if(avatarFile!=null) {
				MediaContent avatar=d.getAvatar();
				avatar=mediaService.update(avatar, avatarFile);
				d.setAvatar(avatar);
			}
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return d;
	}

}
