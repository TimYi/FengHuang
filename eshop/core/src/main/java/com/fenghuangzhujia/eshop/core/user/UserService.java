package com.fenghuangzhujia.eshop.core.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationManager;
import com.fenghuangzhujia.eshop.core.authentication.authority.AuthorityRepository;
import com.fenghuangzhujia.eshop.core.authentication.role.RoleRepository;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.eshop.core.user.dto.UserInputArgs;
import com.fenghuangzhujia.eshop.core.utils.LogUtils;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;
import com.fenghuangzhujia.foundation.utils.validater.PhoneNumberValidater;

@Service
@Transactional
public class UserService extends DtoSpecificationService<User, UserDto, UserInputArgs, String> {
	
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private AuthorityRepository authorityRepository;
	@Autowired
	private MediaService mediaService;
	
	/**
	 * 根据用户名称获取用户实体
	 * @param username
	 * @return
	 * @throws UsernameNotFoundException
	 */
	public User getByUsername(String username) {
		return getRepository().getByUsername(username);
	}
	
	@Override
	public UserDto add(UserInputArgs t) {
		String username=t.getUsername();
		User user=getRepository().getByUsername(username);
		if(user!=null) {
			throw new RuntimeException("用户名重复！");
		}
		user=adapter.convertToDo(t);
		user.setVerified(true); //默认新建用户自动通过审核
		encryptPassword(user);
		user=getRepository().save(user);
		return adapter.convert(user);
	}
	
	@Override
	public UserDto update(UserInputArgs t) {		
		User user=getRepository().findOne(t.getId());
		//要处理密码更新问题
		String password=user.getPassword();
		String username=user.getUsername();
		user=adapter.update(t, user);
		user.setUsername(username);//禁止更新用户名
		user.setPassword(password);//禁止直接更新密码
		getRepository().save(user);
		return adapter.convert(user);
	}
	
	/**
	 * 绑定手机号
	 * @param id 用户id
	 * @param mobile 手机号码
	 */
	public void bindMobile(String id,String mobile) {
		if(!PhoneNumberValidater.isMobile(mobile))throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "请输入正确的11位手机号码");
		User user=getRepository().findOne(id);
		user.setMobile(mobile);
		getRepository().save(user);
	}
	
	/**
	 * 修改头像
	 * @param avatar
	 */
	public void changeAvater(String id, MultipartFile avatar) {
		User user=getRepository().findOne(id);
		MediaContent ava=user.getAvatar();
		try {
			ava=mediaService.update(ava, avatar);
			user.setAvatar(ava);
		} catch (Exception e) {
			LogUtils.errorLog(e);
		}
		user.setAvatar(ava);
	}
	
	private void encryptPassword(User user) {
		String password=AuthenticationManager.encryptPassword(user.getPassword());
		user.setPassword(password);
	}
	
	@Autowired
	public void setUserRepository(UserRepository repository) {
		super.setRepository(repository);
	}
	@Override
	public UserRepository getRepository() {
		return (UserRepository)super.getRepository();
	}
}



