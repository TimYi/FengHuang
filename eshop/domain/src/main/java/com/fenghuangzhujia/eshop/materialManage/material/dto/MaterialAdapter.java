package com.fenghuangzhujia.eshop.materialManage.material.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.eshop.materialManage.material.Material;
import com.fenghuangzhujia.eshop.materialManage.product.Product;
import com.fenghuangzhujia.eshop.materialManage.product.ProductRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class MaterialAdapter extends AbstractDtoAdapter<Material, MaterialDto, MaterialInputArgs> {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private MediaService mediaService;

	@Override
	public MaterialDto postConvert(Material d, MaterialDto t) {
		return t;
	}

	@Override
	public Material postConvertToDo(MaterialInputArgs i, Material d) {
		return postUpdate(i, d);
	}

	@Override
	public Material postUpdate(MaterialInputArgs i, Material d) {
		MultipartFile picFile=i.getPicFile();
		if(picFile!=null) {
			try {
				MediaContent pic=d.getPic();
				pic=mediaService.update(pic, picFile);
				d.setPic(pic);
			} catch (Exception e) {
				LogUtils.errorLog(e);
			}
		}
		String productId=i.getProductId();
		if(StringUtils.isNotBlank(productId)) {
			Product product=productRepository.findOne(productId);
			d.setProduct(product);
		}		
		return d;
	}
}
