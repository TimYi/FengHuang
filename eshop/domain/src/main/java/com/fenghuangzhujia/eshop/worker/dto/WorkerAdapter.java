package com.fenghuangzhujia.eshop.worker.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationManager;
import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.eshop.core.authentication.role.RoleRepository;
import com.fenghuangzhujia.eshop.core.authentication.token.UserToken;
import com.fenghuangzhujia.eshop.core.base.AuthorityValues;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.eshop.worker.Worker;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

@Component
public class WorkerAdapter extends AbstractDtoAdapter<Worker, WorkerDto, WorkerInputArgs> {

	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	
	@Override
	public WorkerDto postConvert(Worker d, WorkerDto t) {
		return t;
	}

	@Override
	public Worker postConvertToDo(WorkerInputArgs i, Worker d) {
		UserToken token=authenticationManager.regist(i.getUsername(), i.getPassword(), null);//建立新工人用户
		User user=token.getUser();
		Role role=roleRepository.getByName(AuthorityValues.WORKER);
		if(role.getUsers()==null) {
			Set<User> users=new HashSet<>();
			role.setUsers(users);
		}
	    role.getUsers().add(user);
	    roleRepository.save(role);
	    d.setUser(user);
	    return d;
	}

	@Override
	public Worker postUpdate(WorkerInputArgs i, Worker d) {
		return d;
	}

}
