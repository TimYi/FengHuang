package com.fenghuangzhujia.eshop.meteriaManage.meteria.dto;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.eshop.meteriaManage.meteria.Meteria;
import com.fenghuangzhujia.eshop.meteriaManage.product.Product;
import com.fenghuangzhujia.eshop.meteriaManage.product.ProductRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class MeterialAdapter extends AbstractDtoAdapter<Meteria, MeteriaDto, MeteriaInputArgs> {
	
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private MediaService mediaService;

	@Override
	public MeteriaDto postConvert(Meteria d, MeteriaDto t) {
		return t;
	}

	@Override
	public Meteria postConvertToDo(MeteriaInputArgs i, Meteria d) {
		return postUpdate(i, d);
	}

	@Override
	public Meteria postUpdate(MeteriaInputArgs i, Meteria d) {
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
