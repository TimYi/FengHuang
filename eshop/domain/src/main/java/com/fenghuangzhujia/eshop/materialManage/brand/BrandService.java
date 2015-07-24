package com.fenghuangzhujia.eshop.materialManage.brand;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.materialManage.brand.dto.BrandDto;
import com.fenghuangzhujia.eshop.materialManage.brand.dto.BrandInputArgs;
import com.fenghuangzhujia.eshop.materialManage.brand.dto.BrandVo;
import com.fenghuangzhujia.eshop.materialManage.material.Material;
import com.fenghuangzhujia.eshop.materialManage.material.MaterialRepository;
import com.fenghuangzhujia.eshop.materialManage.product.Product;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.mapper.BeanMapper;

@Service
@Transactional
public class BrandService extends DtoSpecificationService<Brand, BrandDto, BrandInputArgs, String> {

	@Autowired
	private MaterialRepository materialRepository;
	
	public void reOrder(Map<String, Integer> orders) {
		for (Entry<String, Integer> order : orders.entrySet()) {
			String id=order.getKey();
			Integer ordernum=order.getValue();
			Brand brand=getRepository().findOne(id);
			brand.setOrdernum(ordernum);
		}
	}	
	
	@Override
	public PagedList<BrandDto> findAll(int page, int size,
			Map<String, Object> filters, Map<String, Object> orders) {
		orders.put("ordernum", "asc");
		return super.findAll(page, size, filters, orders);
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
	
	/**
	 * 获取和套餐相关的全部品牌和商品信息
	 * @param packageId
	 * @return
	 */
	public List<BrandVo> findByPackage(String packageId) {
		List<Material> materials=materialRepository.findByPackagesId(packageId);
		Map<String, Product> productMap=new HashMap<>();	
		for (Material material : materials) {
			Product originProduct=material.getProduct();
			String productId=originProduct.getId();
			Product product=productMap.get(productId);
			if(product==null) {
				product=new Product();				
				product.setBrand(originProduct.getBrand());
				product.setCreateTime(originProduct.getCreateTime());
				product.setName(originProduct.getName());
				product.setOrdernum(originProduct.getOrdernum());
				product.setMaterials(new TreeSet<>(new Comparator<Material>() {
					@Override
					public int compare(Material o1, Material o2) {
						return o1.getOrdernum()-o2.getOrdernum();
					}					
				}));
				productMap.put(productId, product);
			}
			product.getMaterials().add(material);
		}
		Map<String, Brand> brandMap=new HashMap<>();
		for (Product product : productMap.values()) {
			Brand originBrand=product.getBrand();
			String brandId=originBrand.getId();
			Brand brand=brandMap.get(brandId);
			if(brand==null) {
				brand=new Brand();
				brand.setLogo(originBrand.getLogo());
				brand.setName(originBrand.getName());
				brand.setOrdernum(originBrand.getOrdernum());
				brand.setProducts(new TreeSet<>(new Comparator<Product>() {
					@Override
					public int compare(Product o1, Product o2) {
						return o1.getOrdernum()-o2.getOrdernum();
					}
				}));
				brandMap.put(brandId, brand);
			}
			brand.getProducts().add(product);
		}
		TreeSet<Brand> brands=new TreeSet<>(new Comparator<Brand>() {
			@Override
			public int compare(Brand o1, Brand o2) {
				return o1.getOrdernum()-o2.getOrdernum();
			}
		});
		List<BrandVo> vos=new ArrayList<>();
		for (Brand brand : brands) {
			BrandVo vo=BeanMapper.map(brand, BrandVo.class);
			vos.add(vo);
		}
		return vos;
	}
}
