package com.fenghuangzhujia.eshop.web.controller.api;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fenghuangzhujia.eshop.core.authentication.AuthenticationService;
import com.fenghuangzhujia.eshop.core.authentication.SimpleUserDetails;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.live.LiveDetailService;
import com.fenghuangzhujia.eshop.live.LiveService;
import com.fenghuangzhujia.eshop.live.Live.ProjectProgress;
import com.fenghuangzhujia.eshop.live.dto.LiveDetailDto;
import com.fenghuangzhujia.eshop.live.dto.LiveDto;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;

@RestController
public class LiveController {
	
	@Autowired
	private LiveService liveService;
	@Autowired
	private LiveDetailService liveDetailService;

	@RequestMapping(value="lives",method=RequestMethod.GET)
	public String searchLives(@DateTimeFormat(pattern="yyyy-MM-dd") Date date, ProjectProgress status,
			@RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="8") int size) {
		PagedList<LiveDto> result=liveService.findLivesToShow(date, status, page, size);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="live/{id}",method=RequestMethod.GET)
	public String searchLive(@PathVariable String id) {
		LiveDto result=liveService.findOne(id);
		if(result!=null && !result.getShouldShow()) {
			throw new ErrorCodeException(SystemErrorCodes.OTHER, "该直播并不对公众展示!");
		}
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="live/{id}/details",method=RequestMethod.GET)
	public String liveDetails(@PathVariable String id, 
			@RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="8") int size) {
		LiveDto result=liveService.findOne(id);
		if(result!=null && !result.getShouldShow()) {
			throw new ErrorCodeException(SystemErrorCodes.OTHER, "该直播并不对公众展示!");
		}
		PagedList<LiveDetailDto> details=liveDetailService.liveDetailPage(id, page, size);
		return RequestResult.success(details).toJson();
	}
	
	@RequestMapping(value="user/lives",method=RequestMethod.GET)
	public String myLives() {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		List<LiveDto> result=liveService.findByUser(userId);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="user/live/{id}",method=RequestMethod.GET)
	public String myLive(@PathVariable String id) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		LiveDto result=liveService.findOneByUser(userId, id);
		return RequestResult.success(result).toJson();
	}
	
	@RequestMapping(value="user/live/{id}/details",method=RequestMethod.GET)
	public String myLiveDetails(@PathVariable String id, 
			@RequestParam(defaultValue="1") int page, @RequestParam(defaultValue="8") int size) {
		SimpleUserDetails details=AuthenticationService.getUserDetail();
		String userId=details.getId();
		PagedList<LiveDetailDto> result=liveDetailService.userLiveDetailPage(id, userId, page, size);
		return RequestResult.success(result).toJson();
	}
}
