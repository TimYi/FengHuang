package com.fenghuangzhujia.foundation.core.dto;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.fenghuangzhujia.foundation.core.entity.Identified;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.persistance.DynamicSpecifications;
import com.fenghuangzhujia.foundation.core.persistance.SearchFilter;
import com.fenghuangzhujia.foundation.core.persistance.SearchSorter;
import com.fenghuangzhujia.foundation.core.persistance.SpecificationRepository;
import com.fenghuangzhujia.foundation.core.service.SpecifactionService;

public class DtoSpecificationService
	<D extends Identified<ID>, T extends Identified<ID>, ID extends Serializable>
	extends DtoPagingService<D, T, ID>
	implements SpecifactionService<T, ID> {
	
	@Override
	public PagedList<T> findAll(int page, int size,
			List<SearchFilter> filters, Map<String, String> orders) {
		Pageable pageable=SearchSorter.toPageable(page, size, orders);
		Specification<D> specification=DynamicSpecifications.bySearchFilter(filters, null);
		Page<D> dlist=getRepository().findAll(specification, pageable);
		Page<T> list=dlist.map(adapter);
		return new PagedList<T>(list);
	}
	
	@Override
	public SpecificationRepository<D, ID> getRepository() {
		return (SpecificationRepository<D, ID>)super.getRepository();
	}
	
	@Autowired
	public void setSpecificationRepository(
			SpecificationRepository<D, ID> repository) {
		super.setPagingAndSortingRepository(repository);
	}
}
