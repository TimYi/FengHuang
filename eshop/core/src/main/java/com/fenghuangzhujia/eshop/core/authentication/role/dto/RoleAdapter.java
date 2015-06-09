package com.fenghuangzhujia.eshop.core.authentication.role.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.eshop.core.user.User;
import com.fenghuangzhujia.eshop.core.user.UserRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;

@Component
public class RoleAdapter extends AbstractDtoAdapter<Role, RoleDto, RoleInputArgs> {
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public RoleDto postConvert(Role d, RoleDto t) {
		return t;
	}

	@Override
	public Role postConvertToDo(RoleInputArgs i, Role d) {
		return postUpdate(i, d);
	}

	@Override
	public Role postUpdate(RoleInputArgs i, Role d) {
		String[] userids=i.getUserids();
		if(userids!=null) {
			Set<User> users=new HashSet<>();
			for (String id : userids) {
				User user=userRepository.findOne(id);
				if(user!=null) {
					users.add(user);
				}
			}
			d.setUsers(users);
		}		
		return d;
	}

}
