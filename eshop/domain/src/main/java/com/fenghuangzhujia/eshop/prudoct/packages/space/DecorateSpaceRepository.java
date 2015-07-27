package com.fenghuangzhujia.eshop.prudoct.packages.space;

import java.util.List;

import org.springframework.data.domain.Sort;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface DecorateSpaceRepository extends SpecificationRepository<DecorateSpace, String> {

	List<DecorateSpace> findByDecoratePackageId(String id, Sort sort);
}
