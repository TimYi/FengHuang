package com.fenghuangzhujia.eshop.prudoct.packages.dto;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.brand.Brand;
import com.fenghuangzhujia.eshop.core.brand.BrandRepository;
import com.fenghuangzhujia.eshop.core.commerce.eshop.ShopRepository;
import com.fenghuangzhujia.eshop.core.menu.Menu;
import com.fenghuangzhujia.eshop.core.menu.MenuRepository;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class DecoratePackageDtoAdapter extends AbstractDtoAdapter<DecoratePackage, DecoratePackageDto, DecoratePackageInputArgs> {

	@Autowired
	private MenuRepository columnRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private ShopRepository shopRepository;
	@Autowired
	private MediaService mediaService;
	@Autowired
	private CategoryItemRepository categoryItemRepository;
	
	@Override
	public DecoratePackageDto postConvert(DecoratePackage d,
			DecoratePackageDto t) {
		return t;
	}

	@Override
	public DecoratePackage postConvertToDo(DecoratePackageInputArgs t,
			DecoratePackage d) {
		d=postUpdate(t, d);
		try {
			MultipartFile[] picFiles=t.getPicFiles();
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
			throw new ErrorCodeException(SystemErrorCodes.FILE_ERROR, e.getMessage());
		}
	}

	@Override
	public DecoratePackage postUpdate(DecoratePackageInputArgs t, DecoratePackage d) {
		try {
			String menuid=t.getMenuid();
			if(StringUtils.isNotBlank(menuid)) {
				Menu menu=columnRepository.findOne(menuid);
				d.setMenu(menu);
			}
			String brandid=t.getBrandid();
			if(StringUtils.isNotBlank(brandid)) {
				Brand brand=brandRepository.findOne(brandid);
				d.setBrand(brand);
			}
			MultipartFile mainPicFile=t.getMainPicFile();
			if(mainPicFile!=null) {
				MediaContent mainPic=d.getMainPic();
				mainPic=mediaService.update(mainPic, mainPicFile);
				d.setMainPic(mainPic);
			}
			MultipartFile thumbnailsFile=t.getThumbnailsFile();
			if(thumbnailsFile!=null) {
				MediaContent thumbnails=d.getThumbnails();
				thumbnails=mediaService.update(thumbnails, thumbnailsFile);
				d.setThumbnails(thumbnails);
			}
			String houseTypeId=t.getHouseTypeId();
			if(StringUtils.isNotBlank(houseTypeId)) {
				CategoryItem houseType=categoryItemRepository.findOne(houseTypeId);
				d.setHouseType(houseType);
			}
			String decorateTypeId=t.getDecorateTypeId();
			if(StringUtils.isNotBlank(decorateTypeId)) {
				CategoryItem decorateType=categoryItemRepository.findOne(decorateTypeId);
				d.setDecorateType(decorateType);
			}
			String responseTypeId=t.getResponseTypeId();
			if(StringUtils.isNotBlank(responseTypeId)) {
				CategoryItem responseType=categoryItemRepository.findOne(responseTypeId);
				d.setResponseType(responseType);
			}		
			d.setDetails(t.getDetails());
			return d;
		} catch (IOException e) {
			throw new ErrorCodeException(SystemErrorCodes.FILE_ERROR, e.getMessage());
		}
	}

}
