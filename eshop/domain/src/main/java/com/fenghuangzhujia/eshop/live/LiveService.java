package com.fenghuangzhujia.eshop.live;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
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
	
	public PagedList<LiveDto> findLivesToShow(Date date, ProjectProgress status, int page, int size) {
		PageRequest pageable=new PageRequest(page-1, size);
		Specification<Live> spec=new Specification<Live>() {
			@Override
			public Predicate toPredicate(Root<Live> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicates = new ArrayList<Predicate>();
				Path<Boolean> shouldShow=root.get("shouldShow");
				predicates.add(cb.equal(shouldShow, true));//限制值返回shouldShow的lives
				
				if(date==null && status==null) 
					return cb.and(predicates.toArray(new Predicate[predicates.size()]));
				
				//首先匹配日期，返回最新直播
				if(date!=null) {
					Path<Date> startDate=root.get("startDate");
					predicates.add(cb.greaterThan(startDate, date));
					return cb.and(predicates.toArray(new Predicate[predicates.size()]));
				}
				
				//匹配直播状态
				Path<ProjectProgress> statusPath=root.get("status");
				predicates.add(cb.equal(statusPath, status));
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		Page<Live> list=getRepository().findAll(spec, pageable);
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
