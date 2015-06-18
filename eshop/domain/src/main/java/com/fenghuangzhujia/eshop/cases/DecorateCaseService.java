package com.fenghuangzhujia.eshop.cases;


import java.util.ArrayList;
import java.util.Collection;
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

import com.fenghuangzhujia.eshop.cases.dto.DecorateCaseDto;
import com.fenghuangzhujia.eshop.cases.dto.DecorateCaseInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.core.model.PagedList;

@Service
@Transactional
public class DecorateCaseService extends DtoSpecificationService<DecorateCase, DecorateCaseDto, DecorateCaseInputArgs, String> {
	
	@Autowired
	private CaseTagRepository tagRepository;
	
	public List<CaseTag> findAllTags() {
		Iterable<CaseTag> tags=tagRepository.findAll();
		if(tags==null)return null;
		List<CaseTag> result=new ArrayList<CaseTag>();
		for (CaseTag tag : tags) {
			result.add(tag);
		}
		return result;
	}
	
	public PagedList<DecorateCaseDto> findByTags(Collection<String> tags, int page, int size) {
		PageRequest pageable=new PageRequest(page-1, size);
		Specification<DecorateCase> spec=new Specification<DecorateCase>() {
			@Override
			public Predicate toPredicate(Root<DecorateCase> root,
					CriteriaQuery<?> query, CriteriaBuilder cb) {
				if(tags==null || tags.isEmpty()) {
					return cb.conjunction();
				}
				List<Predicate> predicates = new ArrayList<Predicate>();
				for (String tag : tags) {
					CaseTag caseTag=new CaseTag();
					caseTag.setName(tag);
					Path<Collection<CaseTag>> tagSet=root.get("tags");
					predicates.add(cb.isMember(caseTag, tagSet));
				}
				return cb.and(predicates.toArray(new Predicate[predicates.size()]));
			}
		};
		Page<DecorateCase> cases=getRepository().findAll(spec, pageable);
		Page<DecorateCaseDto> result=cases.map(adapter);
		return new PagedList<>(result);
	}
	
	@Override
	public DecorateCaseRepository getRepository() {
		return (DecorateCaseRepository)super.getRepository();
	}
	
	@Autowired
	public void setDecorateCaseRepository(DecorateCaseRepository repository) {
		super.setRepository(repository);
	}
}
