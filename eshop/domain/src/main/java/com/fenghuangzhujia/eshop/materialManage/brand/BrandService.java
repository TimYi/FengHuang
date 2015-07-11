package com.fenghuangzhujia.eshop.materialManage.brand;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.materialManage.brand.dto.BrandDto;
import com.fenghuangzhujia.eshop.materialManage.brand.dto.BrandInputArgs;
import com.fenghuangzhujia.eshop.materialManage.brand.dto.BrandVo;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;

@Service
@Transactional
public class BrandService extends DtoSpecificationService<Brand, BrandDto, BrandInputArgs, String> {

	public void reOrder(String[] ids) {
		int i=0;
		for (String id : ids) {
			Brand brand=getRepository().findOne(id);
			brand.setOrdernum(i);
			i++;
		}
	}
	
	/**
	 * 返回品牌到下辖产品到素材全部信息
	 * @param id
	 * @return
	 */
	public BrandVo getDetailedBrand(String id) {
		Brand brand=getRepository().findOne(id);
		BrandVo vo=BeanMapper.map(brand, BrandVo.class);
		return vo; 
	}
	
	/**
	 * 返回全部品牌到下辖产品到素材全部信息
	 * @return
	 */
	public List<BrandVo> getDetailedBrands() {
		Order order=new Order("ordernum");
		Sort sort=new Sort(order);
		Iterable<Brand> brands=getRepository().findAll(sort);		
		List<BrandVo> vos=new ArrayList<>();
		for (Brand brand : brands) {
			BrandVo vo=BeanMapper.map(brand, BrandVo.class);
			vos.add(vo);
		}
		return vos;
	}
}
