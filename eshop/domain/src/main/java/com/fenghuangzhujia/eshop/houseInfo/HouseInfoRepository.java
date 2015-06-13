package com.fenghuangzhujia.eshop.houseInfo;

import java.util.List;

import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;

public interface HouseInfoRepository extends SpecificationRepository<HouseInfo, String> {

	List<HouseInfo> findByUserId(String userid);
}
