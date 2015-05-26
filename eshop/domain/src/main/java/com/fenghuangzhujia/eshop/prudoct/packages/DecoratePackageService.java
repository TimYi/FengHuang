package com.fenghuangzhujia.eshop.prudoct.packages;

import java.io.IOException;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.prudoct.packages.dto.DecoratePackageDto;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Service
@Transactional
public class DecoratePackageService extends DtoPagingService<DecoratePackage, DecoratePackageDto, String> {
	
	@Autowired
	private MediaService mediaService;
	@Autowired
	private DecoratePackageRepository repository;
	
	/**
	 * 删除组图中某个图片
	 * @param id 套餐id
	 * @param picid 图片id
	 */
	public void deletePic(String id,String picid) {
		DecoratePackage p=repository.findOne(id);
		if(p==null)return;
		MediaContent pic=mediaService.getMedia(picid);
		if(pic==null)return;
		p.getPics().remove(pic);
		repository.save(p);
		try {
			mediaService.delete(pic);
		} catch (IOException e) {
			throw new ErrorCodeException(SystemErrorCodes.FILE_ERROR, e.getMessage());
		}		
	}
	
	/**
	 * 向组图中添加一张图片
	 * @param id
	 * @param picFile
	 */
	public void addPic(String id, MultipartFile picFile) {
		DecoratePackage p=repository.findOne(id);
		if(p==null)return;
		try {
			MediaContent pic=mediaService.save(picFile);
			if(p.getPics()==null)p.setPics(new HashSet<>());
			p.getPics().add(pic);
			repository.save(p);
		} catch (IOException e) {
			throw new ErrorCodeException(SystemErrorCodes.FILE_ERROR, e.getMessage());
		}
	}
}
