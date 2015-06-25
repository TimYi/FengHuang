package com.fenghuangzhujia.eshop.worker.dto;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationManager;
import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.eshop.core.authentication.role.RoleRepository;
import com.fenghuangzhujia.eshop.core.authentication.token.UserToken;
import com.fenghuangzhujia.eshop.core.base.AuthorityValues;
import com.fenghuangzhujia.eshop.core.base.Dics;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.worker.Worker;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.dics.CategoryItem;
import com.fenghuangzhujia.foundation.dics.CategoryItemRepository;

@Component
public class WorkerAdapter extends AbstractDtoAdapter<Worker, WorkerDto, WorkerInputArgs> {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private CategoryItemRepository categoryItemRepository;
	
	@Override
	public WorkerDto postConvert(Worker d, WorkerDto t) {
		return t;
	}

	/**创建工人账户的同时，创建一个普通用户账户，和工人账户关联，并为普通用户账户添加WORKER权限*/
	@Override
	public Worker postConvertToDo(WorkerInputArgs i, Worker d) {
		//WARN 可能应该连同手机号码一同保存
		UserToken token=authenticationManager.regist(i.getUsername(), i.getPassword(), null, null);//建立新工人用户
		User user=token.getUser();
		Role role=roleRepository.getByName(AuthorityValues.WORKER);
		if(role.getUsers()==null) {
			Set<User> users=new HashSet<>();
			role.setUsers(users);
		}
	    role.getUsers().add(user);
	    roleRepository.save(role);
	    d.setUser(user);
	    d=postUpdate(i, d);
	    return d;
	}

	@Override
	public Worker postUpdate(WorkerInputArgs i, Worker d) {
		String typeId=i.getTypeId();
		if(StringUtils.isNotBlank(typeId)) {
			CategoryItem type=categoryItemRepository.findOne(typeId);
			if(type!=null && !type.getType().equals(Dics.WORKER))
				throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "工人类型错误");
			d.setType(type);
		}		
		return d;
	}

}
