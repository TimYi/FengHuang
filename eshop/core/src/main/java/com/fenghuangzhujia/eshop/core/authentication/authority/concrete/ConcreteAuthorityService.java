package com.fenghuangzhujia.eshop.core.authentication.authority.concrete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fenghuangzhujia.eshop.core.authentication.authority.opration.OperationAuthority;
import com.fenghuangzhujia.eshop.core.authentication.authority.opration.OperationAuthorityRepository;
import com.fenghuangzhujia.foundation.core.service.AbstractPagingService;

@Service
@Transactional
public class ConcreteAuthorityService extends AbstractPagingService<ConcreteAuthority, String> {
	
	@Autowired
	private OperationAuthorityRepository operationRepository;
	
	@Override
	public ConcreteAuthority add(ConcreteAuthority t) {
		String operationid=t.getOperationid();
		if(operationid!=null) {
			OperationAuthority operation=operationRepository.findOne(operationid);
			t.setOperation(operation);
		}		
		return super.add(t);
	}
	
	/**
	 * 尝试根据资源名称和操作名称添加权限
	 * @param resourceName
	 * @param operationName
	 * @param resourceid
	 * @return
	 */
	public ConcreteAuthority add(String resourceName, String operationName, String resourceid) {
		if(resourceName==null || operationName==null || resourceid==null)return null;
		ConcreteAuthority authority=getRepository().getOne(resourceName, operationName, resourceid);
		if(authority!=null)return authority;
		OperationAuthority operation=operationRepository.getByResourceNameAndName(resourceName, operationName);
		if(operation==null)return null;
		authority=new ConcreteAuthority();
		authority.setOperation(operation);
		authority.setResourceid(resourceid);
		authority=getRepository().save(authority);
		return authority;
	}
	
	@Override
	public ConcreteAuthority update(ConcreteAuthority t) {
		ConcreteAuthority authority=getRepository().findOne(t.getId());
		if(authority==null)return null;
		String operationid=t.getOperationid();
		if(operationid!=null) {
			OperationAuthority operation=operationRepository.findOne(operationid);
			authority.setOperation(operation);
		}		
		authority.setResourceid(t.getResourceid());
		authority=getRepository().save(authority);
		return authority;
	}
	
	@Autowired
	protected void setAuthorityRepository(ConcreteAuthorityRepository repository) {
		super.setRepository(repository);
	}
	@Override
	public ConcreteAuthorityRepository getRepository() {
		return (ConcreteAuthorityRepository)super.getRepository();
	}
}
