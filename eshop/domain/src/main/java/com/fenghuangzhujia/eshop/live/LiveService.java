package com.fenghuangzhujia.eshop.live;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.live.Live.ProjectProgress;
import com.fenghuangzhujia.eshop.live.dto.LiveDto;
import com.fenghuangzhujia.eshop.live.dto.LiveInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;

@Service
@Transactional
public class LiveService extends DtoSpecificationService<Live, LiveDto, LiveInputArgs, String> {

	public PagedList<LiveDto> findByStatus(ProjectProgress status, int page, int size) {
		PageRequest pageable=new PageRequest(page-1, size);
		Page<Live> list=getRepository().findByStatus(status, pageable);
		Page<LiveDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public PagedList<LiveDto> findDateLater(Date date, int page, int size) {
		PageRequest pageable=new PageRequest(page-1, size);
		Page<Live> list=getRepository().findDateLater(date, pageable);
		Page<LiveDto> result=list.map(adapter);
		return new PagedList<>(result);
	}
	
	public List<LiveDto> findByUser(String userId) {
		List<Live> list=getRepository().findByUserId(userId);
		return adapter.convertDoList(list);
	}
	
	public LiveDto findOneByUser(String userId, String id) {
		Live live=getRepository().findOne(id);
		if(live==null)return null;
		if(!live.getUser().getId().equals(userId))
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "您只能获取自己的直播！");
		return adapter.convertToDetailedDto(live);
	}
	
	
	@Override
	public LiveRepository getRepository() {
		return (LiveRepository)super.getRepository();
	}
	
	@Autowired
	public void setLiveRepository(LiveRepository repository) {
		super.setRepository(repository);
	}
}
