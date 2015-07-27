package com.fenghuangzhujia.eshop.prudoct.packages.space.dto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackage;
import com.fenghuangzhujia.eshop.prudoct.packages.DecoratePackageRepository;
import com.fenghuangzhujia.eshop.prudoct.packages.space.DecorateItem;
import com.fenghuangzhujia.eshop.prudoct.packages.space.DecorateItemRepository;
import com.fenghuangzhujia.eshop.prudoct.packages.space.DecorateSpace;
import com.fenghuangzhujia.eshop.prudoct.packages.space.DecorateSpaceRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.SimpleDtoAdapter;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class DecorateSpaceAdapter extends 
	SimpleDtoAdapter<DecorateSpace, DecorateSpaceDto, DecorateSpaceInputArgs> {
	
	@Autowired
	private DecoratePackageRepository decoratePackageRepository;
	@Autowired
	private MediaService mediaService;
	@Autowired
	private DecorateSpaceRepository decorateSpaceRepository;
	@Autowired
	private DecorateItemRepository decorateItemRepository;

	@Override
	public DecorateSpace convertToDo(DecorateSpaceInputArgs t) {
		DecorateSpace d=new DecorateSpace();
		return update(t, d);
	}

	@Override
	public DecorateSpace update(DecorateSpaceInputArgs t, DecorateSpace d) {
		d.setName(t.getName());
		d.setOrdernum(t.getOrdernum());
		String decoratePackageId=t.getDecoratePackageId();
		if(StringUtils.isNotBlank(decoratePackageId)) {
			DecoratePackage decoratePackage=decoratePackageRepository.findOne(decoratePackageId);
			d.setDecoratePackage(decoratePackage);
		}
		try {
			MultipartFile picFile1=t.getPicFile1();
			if(picFile1!=null) {
				MediaContent pic1=d.getPic1();
				pic1=mediaService.update(pic1, picFile1);
				d.setPic1(pic1);
			}
			MultipartFile picFile2=t.getPicFile2();
			if(picFile2!=null) {
				MediaContent pic2=d.getPic2();
				pic2=mediaService.update(pic2, picFile1);
				d.setPic2(pic2);
			}
			MultipartFile picFile3=t.getPicFile3();
			if(picFile3!=null) {
				MediaContent pic3=d.getPic3();
				pic3=mediaService.update(pic3, picFile1);
				d.setPic3(pic3);
			}
		} catch (Exception e) {
			LogUtils.errorLog(e);
		}
		Set<DecorateItem> oldItems=d.getItems();
		List<DecorateItemDto> items=t.getItems();
		d=decorateSpaceRepository.save(d);
		if(items!=null) {
			Set<DecorateItem> decorateItems=new HashSet<>();
			for (DecorateItemDto item : items) {
				DecorateItem decorateItem=BeanMapper.map(item, DecorateItem.class);
				decorateItem.setSpace(d);
				decorateItem=decorateItemRepository.save(decorateItem);
				decorateItems.add(decorateItem);
			}
			d.setItems(decorateItems);
		}
		if(oldItems!=null) {
			decorateItemRepository.delete(oldItems);
		}
		return d;
	}

	@Override
	public DecorateSpaceDto convert(DecorateSpace source) {
		return BeanMapper.map(source, DecorateSpaceDto.class);
	}
}
