package com.fenghuangzhujia.eshop.meteria;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javassist.bytecode.LineNumberAttribute.Pc;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.meteriaManage.brand.BrandService;
import com.fenghuangzhujia.eshop.meteriaManage.brand.dto.BrandDto;
import com.fenghuangzhujia.eshop.meteriaManage.brand.dto.BrandInputArgs;
import com.fenghuangzhujia.eshop.meteriaManage.meteria.MeteriaService;
import com.fenghuangzhujia.eshop.meteriaManage.meteria.dto.MeteriaInputArgs;
import com.fenghuangzhujia.eshop.meteriaManage.product.ProductService;
import com.fenghuangzhujia.eshop.meteriaManage.product.dto.ProductDto;
import com.fenghuangzhujia.eshop.meteriaManage.product.dto.ProductInputArgs;
import com.fenghuangzhujia.foundation.core.test.JpegMultipartFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MeteriaInit {

	@Autowired
	private BrandService brandService;	
	@Autowired
	private ProductService productService;
	@Autowired
	private MeteriaService meteriaService;
	
	@Transactional
	public void initMeterias() {
		
	}
	
	private void addBrand(BrandInputArgs brand, Map<ProductInputArgs, List<MeteriaInputArgs>> products) {
		BrandDto brandDto=brandService.add(brand);
		for (Entry<ProductInputArgs, List<MeteriaInputArgs>> productSet : products.entrySet()) {
			ProductInputArgs product=productSet.getKey();
			List<MeteriaInputArgs> meterias=productSet.getValue();
			product.setBrandId(brandDto.getId());
			ProductDto productDto=productService.add(product);
			for (MeteriaInputArgs meteria : meterias) {
				meteria.setProductId(productDto.getId());
				meteriaService.add(meteria);
			}
		}
	}
	
	private BrandInputArgs getBrand(String name, int ordernum, String filePath) {
		File pic=new File("filePath");
		JpegMultipartFile picFile=new JpegMultipartFile(pic);
		BrandInputArgs brand=new BrandInputArgs();
		brand.setLogoFile(picFile);
		brand.setName(name);
		brand.setOrdernum(ordernum);
		return brand;
	}
	
	private ProductInputArgs getProduct(String name, int ordernum) {
		ProductInputArgs product=new ProductInputArgs();
		product.setName(name);
		product.setOrdernum(ordernum);
		return product;
	}
	
	private MeteriaInputArgs createMeteria(int ordernum, String filePath) {
		File pic=new File("filePath");
		JpegMultipartFile picFile=new JpegMultipartFile(pic);
		MeteriaInputArgs meteria=new MeteriaInputArgs();
		meteria.setPicFile(picFile);
		meteria.setOrdernum(ordernum);
		return meteria;
	}
}
