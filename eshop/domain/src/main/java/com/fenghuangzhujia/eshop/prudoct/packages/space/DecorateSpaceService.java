package com.fenghuangzhujia.eshop.prudoct.packages.space;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.prudoct.packages.space.dto.DecorateSpaceDto;
import com.fenghuangzhujia.eshop.prudoct.packages.space.dto.DecorateSpaceInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class DecorateSpaceService extends
	DtoSpecificationService<DecorateSpace, DecorateSpaceDto, DecorateSpaceInputArgs, String> {

	public List<DecorateSpaceDto> findByPackage(String packageId) {
		Sort sort=new Sort(new Sort.Order("ordernum"));
		List<DecorateSpace> spaces=getRepository().findByDecoratePackageId(packageId,sort);
		return adapter.convertDoList(spaces);
	}
	
	@Override
	public DecorateSpaceRepository getRepository() {
		return (DecorateSpaceRepository)super.getRepository();
	}
}
