package com.fenghuangzhujia.eshop.live;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.live.dto.LiveDetailDto;
import com.fenghuangzhujia.eshop.live.dto.LiveDetailInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Service
@Transactional
public class LiveDetailService extends DtoSpecificationService<LiveDetail, LiveDetailDto, LiveDetailInputArgs, String> {

	@Autowired
	private LiveRepository liveRepository;
	
	public PagedList<LiveDetailDto> liveDetailPage(String liveId, int page, int size) {
		PageRequest pageable=new PageRequest(page-1, size,Direction.DESC,"date");
		Page<LiveDetail> details=getRepository().findByLiveId(liveId,pageable);
		Page<LiveDetailDto> result=details.map(adapter);
		return new PagedList<>(result);
	}
	
	public PagedList<LiveDetailDto> userLiveDetailPage(String liveId, String userId, int page, int size) {
		PageRequest pageable=new PageRequest(page-1, size,Direction.DESC,"date");
		Live live=liveRepository.findOne(liveId);
		if(live==null)return null;
		if(!live.getUser().getId().equals(userId))
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "这不是您的直播数据");
		Page<LiveDetail> details=getRepository().findByLive(live,pageable);
		Page<LiveDetailDto> result=details.map(adapter);
		return new PagedList<>(result);
	}
	
	@Override
	public LiveDetailRepository getRepository() {
		return (LiveDetailRepository)super.getRepository();
	}
}
