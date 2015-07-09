package com.fenghuangzhujia.eshop.meteriaManage.meteria;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.meteriaManage.meteria.dto.MeteriaDto;
import com.fenghuangzhujia.eshop.meteriaManage.meteria.dto.MeteriaInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class MeteriaService extends DtoSpecificationService<Meteria, MeteriaDto, MeteriaInputArgs, String> {

	/**
	 * 为素材重新排序
	 * @param meterias
	 */
	public void reOrder(String[] ids) {
		int i=0;
		for (String id : ids) {
			Meteria meteria=getRepository().findOne(id);
			meteria.setOrdernum(i);
			i++;
		}
	}
}
