package com.fenghuangzhujia.eshop.core.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.brand.dto.BrandDto;
import com.fenghuangzhujia.eshop.core.brand.dto.BrandInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;
import com.fenghuangzhujia.foundation.core.model.PagedList;

@Service
@Transactional
public class BrandService extends DtoPagingService<Brand, BrandDto, BrandInputArgs, String> {
	
	public PagedList<BrandDto> findByType(String typeid, int page, int size) {
		PageRequest pageable=new PageRequest(page-1, size);
		Page<Brand> brands=getRepository().findByBrandTypeId(typeid, pageable);
		Page<BrandDto> result=brands.map(adapter);
		return new PagedList<>(result);
	}
	
	@Autowired
	public void setBrandRepository(BrandRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public BrandRepository getRepository() {
		return (BrandRepository)super.getRepository();
	}
}
