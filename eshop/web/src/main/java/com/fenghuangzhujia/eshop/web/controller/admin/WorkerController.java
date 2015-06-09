package com.fenghuangzhujia.eshop.web.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.worker.WorkerService;
import com.fenghuangzhujia.eshop.worker.dto.WorkerDto;
import com.fenghuangzhujia.eshop.worker.dto.WorkerInputArgs;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController
@RequestMapping("admin/worker")
public class WorkerController extends SpecificationController<WorkerDto, WorkerInputArgs> {

	@Autowired
	private WorkerService service;
	
	@Override
	public WorkerService getService() {
		return service;
	}
}
