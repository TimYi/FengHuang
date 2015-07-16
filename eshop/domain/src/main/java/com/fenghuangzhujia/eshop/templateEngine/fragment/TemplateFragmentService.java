package com.fenghuangzhujia.eshop.templateEngine.fragment;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.templateEngine.fragment.dto.TemplateFragmentDto;
import com.fenghuangzhujia.eshop.templateEngine.fragment.dto.TemplateFragmentInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class TemplateFragmentService extends 
	DtoSpecificationService<TemplateFragment, TemplateFragmentDto, TemplateFragmentInputArgs, String> {

}
