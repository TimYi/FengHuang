package com.fenghuangzhujia.eshop.coupons.stragys.quota;

import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.coupons.dto.CouponsStragyDto;
import com.fenghuangzhujia.eshop.coupons.entity.CouponsStragyEntity;
import com.fenghuangzhujia.foundation.core.dto.polymorphic.SimplePolymorphicDtoAdapter;

/**
 * 定额优惠策略转换器
 * @author pc
 *
 */
@Component
public class QuotaStragyAdapter
	extends SimplePolymorphicDtoAdapter<QuotaStragy, QuotaStragyDto, 
		CouponsStragyEntity, CouponsStragyDto>{

}
