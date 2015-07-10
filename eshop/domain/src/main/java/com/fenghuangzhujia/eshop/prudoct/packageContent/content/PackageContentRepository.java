package com.fenghuangzhujia.eshop.prudoct.packageContent.content;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface PackageContentRepository extends SpecificationRepository<PackageContent, String> {

	List<PackageContent> findByDecoratePackageId(String id);
	
	Page<PackageContent> findByDecoratePackageId(String id, Pageable pageable);
}
