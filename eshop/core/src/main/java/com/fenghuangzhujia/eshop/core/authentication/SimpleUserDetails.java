package com.fenghuangzhujia.eshop.core.authentication;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fenghuangzhujia.eshop.core.authentication.authority.AbstractAuthority;
import com.fenghuangzhujia.eshop.core.authentication.role.Role;
import com.fenghuangzhujia.eshop.core.user.User;

/**
 * Spring Security框架中用到的用户信息
 * @author pc
 *
 */
public class SimpleUserDetails implements UserDetails {
	
	public static String PREFIX="ROLE_";
	
	private static final long serialVersionUID = -9197074182768134154L;
	
	private String id;
	private String cname;
	private String ename;
	private String mobile;
	private boolean infoComplete; //用户个人信息是否完整
	private String username;
	private String password;
	private boolean isVerified;
	private Set<GrantedAuthority> authorities;
	
	public SimpleUserDetails(User user) {
		this.id=user.getId();
		this.ename=user.getCnname();
		this.ename=user.getEname();
		this.mobile=user.getMobile();
		this.infoComplete=user.getInfoComplete();
		this.username=user.getUsername();
		this.password=user.getPassword();
		this.isVerified=user.isVerified();
		//初始化authorityList
		Set<GrantedAuthority> authorityList=new HashSet<>();
		if(user.getRoles()!=null) {
			for (Role role : user.getRoles()) {
				if(role.getAuthorities()==null)continue;
				for (AbstractAuthority authority : role.getAuthorities()) {
					//authorityList.add(new PermissionAuthority(authority));
				}
			}
		}
		if(user.getAuthorities()!=null) {
			for (AbstractAuthority authority : user.getAuthorities()) {
				//authorityList.add(new PermissionAuthority(authority));
				//暂时不允许细粒度的权限配置
			}
		}
		//初始化roleList
		if(user.getRoles()!=null) {
			for (Role role : user.getRoles()) {
				String roleString=PREFIX+role.getName();
				SimpleGrantedAuthority authority=new SimpleGrantedAuthority(roleString);
				authorityList.add(authority);
			}
		}
		this.authorities=authorityList;
	}
	
	public String getId() {
		return id;
	}
	
	public String getCname() {
		return cname;
	}
	
	public String getEname() {
		return ename;
	}
	
	public String getMobile() {
		return mobile;
	}
	
	public boolean getInfoComplete() {
		return infoComplete;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return isVerified;
	}

	@Override
	public boolean isAccountNonLocked() {
		return isVerified;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return isVerified;
	}

	@Override
	public boolean isEnabled() {
		return isVerified;
	}
}
