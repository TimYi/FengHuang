package com.fenghuangzhujia.eshop.user.collect.dto;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.user.collect.Collect;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class CollectDtoAdapter extends AbstractDtoAdapter<Collect, CollectDto> {
	
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private MediaService mediaService;

	@Override
	public Collect convertToDo(CollectDto t) {
		if(t==null)return null;
		Collect d=BeanMapper.map(t, Collect.class);
		if(t.getUserid()!=null) {
			User user=userRepository.findOne(t.getUserid());
			d.setUser(user);
		}
		if(t.getMainPicFile()!=null) {
			try {
				MediaContent mediaContent=mediaService.save(t.getMainPicFile());
				d.setMainPic(mediaContent);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return d;
	}

	@Override
	public Collect update(CollectDto t, Collect d) {
		if(t==null||d==null)return null;
		BeanMapper.copy(t, d);
		if(t.getUserid()!=null) {
			User user=userRepository.findOne(t.getUserid());
			d.setUser(user);
		}
		if(t.getMainPicFile()!=null) {
			try {
				MediaContent mediaContent=mediaService.save(t.getMainPicFile());
				d.setMainPic(mediaContent);
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
		return d;
	}

	@Override
	public CollectDto convert(Collect source) {
		if(source==null)return null;
		CollectDto t=BeanMapper.map(source, CollectDto.class);
		t.setUserid(source.getUser().getId());
		return t;
	}
}
