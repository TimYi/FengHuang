package com.fenghuangzhujia.eshop.web.controller.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.appoint.AppointService;
import com.fenghuangzhujia.eshop.collect.CollectService;
import com.fenghuangzhujia.eshop.comment.CommentItemService;
import com.fenghuangzhujia.eshop.core.authentication.AuthenticationManager;
import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.core.commerce.coupons.CouponsService;
import com.fenghuangzhujia.eshop.core.user.UserService;
import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.eshop.core.user.dto.UserInputArgs;
import com.fenghuangzhujia.eshop.core.validate.message.MessageManager;
import com.fenghuangzhujia.eshop.message.MessageService;
import com.fenghuangzhujia.eshop.userGroup.UserGroupService;
import com.fenghuangzhujia.eshop.userGroup.dto.UserGroupDto;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class UserController {
	
	@Autowired
	private CollectService collectService;
	@Autowired
	private CommentItemService commentService;
	@Autowired
	private CouponsService couponsService;
	@Autowired
	private MessageService messageService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserGroupService userGroupService;
	@Autowired
	private MessageManager messageManager;
	@Autowired
	private AppointService appointService;
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 * 获取用户个人信息
	 * @return
	 */
	@RequestMapping(value="user/profile",method=RequestMethod.GET)
	public String profile() {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		UserDto userDto=userService.findOne(userid);
		UserVo result=new UserVo();
		result.setUser(userDto);
		UserGroupDto group=userGroupService.inGroup(userDto.getExpVal());
		result.setGroup(group);
		return RequestResult.success(result).toJson();
	}
	
	/**
	 * 编辑用户个人信息
	 * @param args
	 * @return
	 */
	@RequestMapping(value="user/profile",method=RequestMethod.POST)
	public String editProfile(UserInputArgs args) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		args.setId(userid);
		UserDto result=userService.update(args);
		return RequestResult.success(result).toJson();
	}
	
	/**
	 * 绑定用户手机号码
	 * @param mobile
	 * @param validater
	 * @return
	 */
	@RequestMapping(value="user/bindMobile",method=RequestMethod.POST)
	public String bindMobile(@RequestParam String mobile,@RequestParam String validater) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		messageManager.validate(mobile, validater);
		userService.bindMobile(userid, mobile);
		return RequestResult.success("绑定成功").toJson();
	}
	
	/**
	 * 上传头像
	 * @param avatar
	 * @return
	 */
	@RequestMapping(value="user/changeAvatar",method=RequestMethod.POST)
	public String changeAvatar(MultipartFile avatar, String token, HttpServletResponse response) {
		SimpleUserDetails details=authenticationManager.authenticate(token);
		String userid=details.getId();
		userService.changeAvater(userid, avatar);
		response.setHeader("X-Frame-Options", "ALLOW-FROM*");
		return RequestResult.success("上传成功").toJson();
	}
	
	/**
	 * 获取评论、留言等信息数量
	 * @return
	 */
	@RequestMapping(value="user/unreads",method=RequestMethod.GET)
	public String infoCounts() {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userid=details.getId();
		Map<String, Long> counts=new HashMap<>();
		Long messageCount=messageService.countByIsReaded(userid, false);
		counts.put("messages", messageCount);
		Long appointCount=appointService.countByIsReaded(userid, false);
		counts.put("appoints", appointCount);
		Long commentCount=commentService.countByIsReaded(userid, false);
		counts.put("comments", commentCount);
		Long collectCount=collectService.countByIsReaded(userid, false);
		counts.put("collects", collectCount);
		Long couponsCount=couponsService.countByIsReaded(userid, false);
		counts.put("coupons", couponsCount);
		return RequestResult.success(counts).toJson();
	}
	
	public static class UserVo {
		
		private UserGroupDto group;
		private UserDto user;

		public UserGroupDto getGroup() {
			return group;
		}

		public void setGroup(UserGroupDto group) {
			this.group = group;
		}

		public UserDto getUser() {
			return user;
		}

		public void setUser(UserDto user) {
			this.user = user;
		}
	}
}
