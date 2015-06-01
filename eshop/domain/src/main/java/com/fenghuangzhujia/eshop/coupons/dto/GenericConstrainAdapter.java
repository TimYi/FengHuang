package com.fenghuangzhujia.eshop.coupons.dto;

import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.coupons.entity.CouponsConstrainEntity;
import com.fenghuangzhujia.foundation.core.dto.polymorphic.SimplePolymorphicDtoAdapter;

/**
 * 不做任何限制的优惠条件转换器
 * @author pc
 *
 */
@Component
public class GenericConstrainAdapter
	extends SimplePolymorphicDtoAdapter<CouponsConstrainEntity, 
		CouponsConstrainDto, CouponsConstrainEntity, CouponsConstrainDto>{
}
