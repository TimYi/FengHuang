package com.fenghuangzhujia.eshop.materialManage.material;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.materialManage.material.dto.MaterialDto;
import com.fenghuangzhujia.eshop.materialManage.material.dto.MaterialInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class MaterialService extends DtoSpecificationService<Material, MaterialDto, MaterialInputArgs, String> {

	@Autowired
	private MaterialTypeRepository materialTypeRepository;
	
	/**
	 * 为素材重新排序
	 * @param ids
	 */
	public void reOrder(Map<String, Integer> orders) {
		for (Entry<String, Integer> order : orders.entrySet()) {
			String id=order.getKey();
			Integer ordernum=order.getValue();
			Material material=getRepository().findOne(id);
			material.setOrdernum(ordernum);
		}
	}
	
	public List<MaterialDto> findByProduct(String productId) {
		List<Material> materials=getRepository().findByProductId(productId);
		return adapter.convertDoList(materials);
	}
	
	public List<MaterialType> getMaterialTypes() {
		Sort sort=new Sort(new Sort.Order(Direction.ASC, "ordernum"));
		Iterable<MaterialType> materials= materialTypeRepository.findAll(sort);
		if(materials==null)return null;
		List<MaterialType> materialTypes=new ArrayList<MaterialType>();
		for (MaterialType materialType : materials) {
			materialTypes.add(materialType);
		}
		return materialTypes;
	}
	
	public List<MaterialDto> findByPackage(String packageId) {
		List<Material> materials=getRepository().findByPackagesId(packageId);
		return adapter.convertDoList(materials);
	}
	
	public void reOrderMaterialTypes(String[] ids) {
		int i=0;
		for (String id : ids) {
			MaterialType type=materialTypeRepository.findOne(id);
			type.setOrdernum(i);
			i++;
		}
	}
	
	@Override
	public MaterialRepository getRepository() {
		return (MaterialRepository)super.getRepository();
	}
}
