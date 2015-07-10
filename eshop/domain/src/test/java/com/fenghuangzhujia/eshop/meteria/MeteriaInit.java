package com.fenghuangzhujia.eshop.meteria;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;










import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.fenghuangzhujia.eshop.materialManage.brand.BrandService;
import com.fenghuangzhujia.eshop.materialManage.brand.dto.BrandDto;
import com.fenghuangzhujia.eshop.materialManage.brand.dto.BrandInputArgs;
import com.fenghuangzhujia.eshop.materialManage.material.MaterialService;
import com.fenghuangzhujia.eshop.materialManage.material.dto.MaterialInputArgs;
import com.fenghuangzhujia.eshop.materialManage.product.ProductService;
import com.fenghuangzhujia.eshop.materialManage.product.dto.ProductDto;
import com.fenghuangzhujia.eshop.materialManage.product.dto.ProductInputArgs;
import com.fenghuangzhujia.foundation.core.test.JpegMultipartFile;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext.xml" })
public class MeteriaInit {

	@Autowired
	private BrandService brandService;	
	@Autowired
	private ProductService productService;
	@Autowired
	private MaterialService meteriaService;
	
	@Test
	public void initMeterias() {
		File brandDir=new File("C:/Users/pc/Desktop/brand");
		int brandnum=0;
		for (File dir : brandDir.listFiles()) {
			BrandInputArgs brand=getBrand(dir.getName(), brandnum, FileUtils.getFile(dir, "logo.gif"));
		    brandnum++;
		    Map<ProductInputArgs, List<MaterialInputArgs>> products=new HashMap<>();
		    int productnum=0;
		    for (File pfile : dir.listFiles()) {		    	
				if(pfile.isFile())continue;
				ProductInputArgs product=getProduct(pfile.getName(), productnum);
				productnum++;
				List<MaterialInputArgs> meterias=new ArrayList<>();
				int meterianum=0;
				for (File mfile : pfile.listFiles()) {
					MaterialInputArgs meteria=createMeteria(meterianum, mfile);
					meterias.add(meteria);
				}
				products.put(product, meterias);
			}
		    addBrand(brand, products);
		}
	}
	
	private void addBrand(BrandInputArgs brand, Map<ProductInputArgs, List<MaterialInputArgs>> products) {
		BrandDto brandDto=brandService.add(brand);
		for (Entry<ProductInputArgs, List<MaterialInputArgs>> productSet : products.entrySet()) {
			ProductInputArgs product=productSet.getKey();
			List<MaterialInputArgs> meterias=productSet.getValue();
			product.setBrandId(brandDto.getId());
			ProductDto productDto=productService.add(product);
			for (MaterialInputArgs meteria : meterias) {
				meteria.setProductId(productDto.getId());
				meteriaService.add(meteria);
			}
		}
	}
	
	private BrandInputArgs getBrand(String name, int ordernum, File picFile) {
		JpegMultipartFile pic=new JpegMultipartFile(picFile);
		BrandInputArgs brand=new BrandInputArgs();
		brand.setLogoFile(pic);
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
	
	private MaterialInputArgs createMeteria(int ordernum, File pic) {
		JpegMultipartFile picFile=new JpegMultipartFile(pic);
		MaterialInputArgs meteria=new MaterialInputArgs();
		meteria.setPicFile(picFile);
		meteria.setOrdernum(ordernum);
		return meteria;
	}
}
