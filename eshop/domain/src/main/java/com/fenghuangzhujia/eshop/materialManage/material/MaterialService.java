package com.fenghuangzhujia.eshop.materialManage.material;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.materialManage.material.dto.MaterialDto;
import com.fenghuangzhujia.eshop.materialManage.material.dto.MaterialInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class MaterialService extends DtoSpecificationService<Material, MaterialDto, MaterialInputArgs, String> {

	/**
	 * 为素材重新排序
	 * @param meterias
	 */
	public void reOrder(String[] ids) {
		int i=0;
		for (String id : ids) {
			Material meteria=getRepository().findOne(id);
			meteria.setOrdernum(i);
			i++;
		}
	}
}
