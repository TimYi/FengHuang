package com.fenghuangzhujia.eshop.prudoct.cases.dto;

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
import com.fenghuangzhujia.eshop.prudoct.cases.DecorateCase;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class DecorateCaseDtoAdapter extends AbstractDtoAdapter<DecorateCase, DecorateCaseDto, DecorateCaseDto> {

	
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
	public DecorateCaseDto postConvert(DecorateCase d, DecorateCaseDto t) {
		if(d.getMenu()!=null) {
			t.setMenuid(d.getMenu().getId());
			t.setMenu(d.getMenu().getName());
		}
		if(d.getBrand()!=null) {
			t.setBrandid(d.getBrand().getId());
			t.setBrand(d.getBrand().getName());
		}
		return t;
	}

	@Override
	public DecorateCase postConvertToDo(DecorateCaseDto t, DecorateCase d) {
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
	public DecorateCase postUpdate(DecorateCaseDto t, DecorateCase d) {
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
			String categoryid=t.getCategoryid();
			if(StringUtils.isNotBlank(categoryid)) {
				CategoryItem category=categoryItemRepository.findOne(categoryid);
				d.setCategory(category);
			}
			String styleid=t.getStyleid();
			if(StringUtils.isNotBlank(styleid)) {
				CategoryItem style=categoryItemRepository.findOne(styleid);
				d.setStyle(style);
			}
			String apartmentTypeId=t.getApartmentTypeId();
			if(StringUtils.isNotBlank(apartmentTypeId)) {
				CategoryItem apartmentType=categoryItemRepository.findOne(apartmentTypeId);
				d.setApartmentType(apartmentType);
			}		
			d.setDetails(t.getDetails());
			return d;
		} catch (IOException e) {
			throw new ErrorCodeException(SystemErrorCodes.FILE_ERROR, e.getMessage());
		}
	}

}
