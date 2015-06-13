package com.fenghuangzhujia.eshop.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.houseInfo.HouseInfoService;
import com.fenghuangzhujia.eshop.houseInfo.dto.HouseInfoDto;
import com.fenghuangzhujia.eshop.houseInfo.dto.HouseInfoInputArgs;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class HouseInfoController {
	
	@Autowired
	private HouseInfoService houseInfoService;
	
	/**
	 * 新增用户房屋信息
	 * @param args
	 * @return
	 */
	@RequestMapping(value="user/houses",method=RequestMethod.POST)
	public String addUserHouseInfo(HouseInfoInputArgs args) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		args.setUserId(userId);
		HouseInfoDto result=houseInfoService.add(args);
		return RequestResult.success(result).toJson();
	}

	/**
	 * 获取全部用户房屋信息
	 * @return
	 */
	@RequestMapping(value="user/houses",method=RequestMethod.GET)
	public String getUserHouseInfos() {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		List<HouseInfoDto> result=houseInfoService.findByUser(userId);
		return RequestResult.success(result).toJson();
	}
	
	/**
	 * 获取用户单个房屋信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="user/house/{id}",method=RequestMethod.GET)
	public String getUserHouseInfo(@PathVariable String id) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		HouseInfoDto result=houseInfoService.findByUserAndId(userId, id);
		return RequestResult.success(result).toJson();
	}
	
	/**
	 * 修改用户单个房屋信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="user/house/{id}",method=RequestMethod.POST)
	public String editUserHouseInfo(@PathVariable String id, HouseInfoInputArgs args) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		HouseInfoDto result=houseInfoService.editByUser(userId, args);
		return RequestResult.success(result).toJson();
	}
	
	/**
	 * 删除用户房屋信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value="user/house/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable String id) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		houseInfoService.deleteByUser(userId, id);
		return RequestResult.success("删除成功").toJson();
	}
}
