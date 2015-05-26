package com.fenghuangzhujia.eshop.core.authentication.authority.opration;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.authentication.authority.resource.ResourceAuthority;
import com.fenghuangzhujia.eshop.core.authentication.authority.resource.ResourceAuthorityRepository;
import com.fenghuangzhujia.foundation.core.service.AbstractCrudService;

@Service
@Transactional
public class OperationAuthorityService extends AbstractCrudService<OperationAuthority, String> {
	
	@Autowired
	private ResourceAuthorityRepository resourceAuthorityRepository;
	
	@Override
	public OperationAuthority add(OperationAuthority entity) {
		String resourceid=entity.getResourceid();
		if(resourceid!=null) {
			ResourceAuthority resource=resourceAuthorityRepository.findOne(resourceid);
			entity.setResource(resource);
		}
		return super.add(entity);
	}
	
	@Override
	public OperationAuthority update(OperationAuthority entity) {
		String id=entity.getId();
		OperationAuthority operation=getRepository().findOne(id);
		if(operation==null)return null;
		String resourceid=entity.getResourceid();
		if(resourceid!=null) {
			ResourceAuthority resource=resourceAuthorityRepository.findOne(resourceid);
			operation.setResource(resource);
		}
		operation.setName(entity.getName());
		operation.setDescription(entity.getDescription());
		getRepository().save(operation);
		return operation;
	}
	
	/**
	 * 按照资源id和操作名称获取唯一操作
	 * @param resourceName
	 * @param name
	 * @return
	 */
	public OperationAuthority getByResourceAndName(String resourceName, String name) {
		if(resourceName==null || name==null)return null;
		return getRepository().getByResourceNameAndName(resourceName, name);
	}
	
	public List<OperationAuthority> findByResource(ResourceAuthority resource) {
		return getRepository().findByResource(resource);
	}
	
	public List<OperationAuthority> findByResourceId(String id) {
		return getRepository().findByResourceId(id);
	}
	
	public void setOperationAuthorityRepository(OperationAuthorityRepository repository) {
		super.setRepository(repository);
	}
	
	@Override
	public OperationAuthorityRepository getRepository() {
		return (OperationAuthorityRepository)super.getRepository();
	}
}
