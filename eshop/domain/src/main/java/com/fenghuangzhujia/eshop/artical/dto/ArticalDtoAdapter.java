package com.fenghuangzhujia.eshop.artical.dto;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.artical.Artical;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.menu.Menu;
import com.fenghuangzhujia.eshop.core.menu.MenuRepository;
import com.fenghuangzhujia.foundation.core.dto.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class ArticalDtoAdapter extends AbstractDtoAdapter<Artical, ArticalDto> {

	@Autowired
	private MenuRepository menuRepository;
	@Autowired
	private MediaService mediaService;
	
	@Override
	public ArticalDto postConvert(Artical d, ArticalDto t) {
		return t;
	}

	@Override
	public Artical postConvertToDo(ArticalDto t, Artical d) {
		d=postUpdate(t, d);
		MultipartFile[] picFiles=t.getPicFiles();
		try {
			if(picFiles!=null) {
				Set<MediaContent> pics=new HashSet<>();
				for (MultipartFile picFile : picFiles) {
					MediaContent pic=mediaService.save(picFile);
					pics.add(pic);
				}
				d.setPics(pics);
			}
			return d;
		} catch (IOException e) {
			throw new ErrorCodeException(SystemErrorCodes.OTHER, e);
		}		
	}

	@Override
	public Artical postUpdate(ArticalDto t, Artical d) {
		String menuid=t.getMenuid();
		if(StringUtils.isNotBlank(menuid)) {
			Menu menu=menuRepository.findOne(menuid);
			d.setMenu(menu);
		}
		try {
			MultipartFile mainPicFile=t.getMainPicFile();
			if(mainPicFile!=null) {
				MediaContent media=d.getMainPic();
				media=mediaService.update(media, mainPicFile);
				d.setMainPic(media);
			}
			MultipartFile thumbnailsFile=t.getThumbnailsFile();
			if(thumbnailsFile!=null) {
				MediaContent media=d.getThumbnails();
				media=mediaService.update(media, thumbnailsFile);
				d.setThumbnails(media);
			}
			return d;
		} catch (Exception e) {
			throw new ErrorCodeException(SystemErrorCodes.OTHER,e);
		}		
	}
}
