package com.fenghuangzhujia.eshop.worker;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.worker.dto.WorkerDto;
import com.fenghuangzhujia.eshop.worker.dto.WorkerInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class WorkerService extends DtoSpecificationService<Worker, WorkerDto, WorkerInputArgs, String> {

}
