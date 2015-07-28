package com.fenghuangzhujia.eshop.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.live.LiveDetailService;
import com.fenghuangzhujia.eshop.live.dto.LiveDetailDto;
import com.fenghuangzhujia.eshop.live.dto.LiveDetailInputArgs;
import com.fenghuangzhujia.foundation.core.rest.RequestResult;
import com.fenghuangzhujia.foundation.core.rest.SpecificationController;

@RestController
@RequestMapping(value="liveDetail")
public class LiveDetailController extends SpecificationController<LiveDetailDto, LiveDetailInputArgs> {

	@Autowired
	private LiveDetailService service;
	
	@Override
	public LiveDetailService getService() {
		return service;
	}
	
	@RequestMapping(value="{id}/pics",method=RequestMethod.POST)
	public String addPic(@PathVariable String id, MultipartFile picFile) {
		getService().addPic(id, picFile);
		return RequestResult.success("添加成功").toJson();
	}	
	@RequestMapping(value="{id}/pic/{picId}",method=RequestMethod.DELETE)
	public String deletePic(@PathVariable String id, @PathVariable String picId) {
		getService().deletePic(id, picId);
		return RequestResult.success("删除成功").toJson();
	}	
	
	@RequestMapping(value="{id}/interactPics",method=RequestMethod.POST)
	public String addInteractPic(@PathVariable String id, MultipartFile picFile) {
		getService().addInteractPic(id, picFile);
		return RequestResult.success("添加成功").toJson();
	}	
	@RequestMapping(value="{id}/interactPic/{picId}",method=RequestMethod.DELETE)	
	public String deleteInteractPic(@PathVariable String id, @PathVariable String picId) {
		getService().deleteInteractPic(id, picId);
		return RequestResult.success("删除成功").toJson();
	}
}
