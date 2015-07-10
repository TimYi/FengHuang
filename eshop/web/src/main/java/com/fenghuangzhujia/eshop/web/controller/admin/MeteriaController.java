package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.meteriaManage.meteria.MeteriaService;
import com.fenghuangzhujia.eshop.meteriaManage.meteria.dto.MeteriaDto;
import com.fenghuangzhujia.eshop.meteriaManage.meteria.dto.MeteriaInputArgs;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController("adminMeteriaController")
@RequestMapping("admin/meteria")
public class MeteriaController extends SpecificationController<MeteriaDto, MeteriaInputArgs> {

	@Autowired
	private MeteriaService service;
	
	@Override
	public MeteriaService getService() {
		return service;
	}
	
	@RequestMapping(value="order",method=RequestMethod.POST)
	public String order(String[] ids) {
		getService().reOrder(ids);
		return RequestResult.success("排序完成").toJson();
	}
}
