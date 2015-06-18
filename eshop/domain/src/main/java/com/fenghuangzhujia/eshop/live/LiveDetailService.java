package com.fenghuangzhujia.eshop.live;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.live.dto.LiveDetailDto;
import com.fenghuangzhujia.eshop.live.dto.LiveDetailInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;

@Service
@Transactional
public class LiveDetailService extends DtoSpecificationService<LiveDetail, LiveDetailDto, LiveDetailInputArgs, String> {

}
