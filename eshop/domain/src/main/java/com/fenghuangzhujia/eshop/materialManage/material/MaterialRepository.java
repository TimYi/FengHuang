package com.fenghuangzhujia.eshop.materialManage.material;

import java.util.List;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface MaterialRepository extends SpecificationRepository<Material, String> {

	List<Material> findByPackagesId(String id);
}
