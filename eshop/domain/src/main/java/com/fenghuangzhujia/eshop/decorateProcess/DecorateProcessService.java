package com.fenghuangzhujia.eshop.decorateProcess;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.decorateProcess.dto.DecorateProcessDto;
import com.fenghuangzhujia.eshop.decorateProcess.dto.DecorateProcessInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoPagingService;

@Service
@Transactional
public class DecorateProcessService extends 
	DtoPagingService<DecorateProcess, DecorateProcessDto, DecorateProcessInputArgs, String> {

}
