package com.fenghuangzhujia.eshop.coupons;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.coupons.dto.CouponsDefDto;
import com.fenghuangzhujia.eshop.coupons.entity.CouponsDefEntity;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;

@Service
@Transactional
public class CouponsDefService extends DtoPagingService<CouponsDefEntity, CouponsDefDto, String> {

}
