package com.fenghuangzhujia.eshop.live;

import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.live.dto.LiveDetailDto;
import com.fenghuangzhujia.eshop.live.dto.LiveDetailInputArgs;
import com.fenghuangzhujia.foundation.core.dto.DtoSpecificationService;
import com.fenghuangzhujia.foundation.core.model.PagedList;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Service
@Transactional
public class LiveDetailService extends DtoSpecificationService<LiveDetail, LiveDetailDto, LiveDetailInputArgs, String> {

	@Autowired
	private LiveRepository liveRepository;
	@Autowired
	private MediaService mediaService;
	
	public PagedList<LiveDetailDto> liveDetailPage(String liveId, int page, int size) {
		PageRequest pageable=new PageRequest(page-1, size,Direction.DESC,"date");
		Page<LiveDetail> details=getRepository().findByLiveId(liveId,pageable);
		Page<LiveDetailDto> result=details.map(adapter);
		return new PagedList<>(result);
	}
	
	public PagedList<LiveDetailDto> userLiveDetailPage(String liveId, String userId, int page, int size) {
		PageRequest pageable=new PageRequest(page-1, size,Direction.DESC,"date");
		Live live=liveRepository.findOne(liveId);
		if(live==null)return null;
		if(!live.getUser().getId().equals(userId))
			throw new ErrorCodeException(SystemErrorCodes.ILLEGAL_ARGUMENT, "这不是您的直播数据");
		Page<LiveDetail> details=getRepository().findByLive(live,pageable);
		Page<LiveDetailDto> result=details.map(adapter);
		return new PagedList<>(result);
	}
	
	/**删除直播图片*/
	public void deletePic(String liveDetailId, String picId) {
		LiveDetail detail=getRepository().findOne(liveDetailId);
		MediaContent media=mediaService.getMedia(picId);
		detail.getPics().remove(media);
		try {
			mediaService.delete(media);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}		
	}
	
	/**添加直播图片*/
	public void addPic(String liveDetailId, MultipartFile picFile) {
		LiveDetail detail=getRepository().findOne(liveDetailId);
		if(detail.getPics()==null) {
			detail.setPics(new HashSet<>());
		}
		try {
			MediaContent pic=mediaService.save(picFile);
			detail.getPics().add(pic);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	/**删除微信互动图片*/
	public void deleteInteractPic(String liveDetailId, String picId) {
		LiveDetail detail=getRepository().findOne(liveDetailId);
		MediaContent media=mediaService.getMedia(picId);
		detail.getInteractPics().remove(media);
		try {
			mediaService.delete(media);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}		
	}
	
	/**添加微信互动图片*/
	public void addInteractPic(String liveDetailId, MultipartFile picFile) {
		LiveDetail detail=getRepository().findOne(liveDetailId);
		if(detail.getInteractPics()==null) {
			detail.setInteractPics(new HashSet<>());
		}
		try {
			MediaContent pic=mediaService.save(picFile);
			detail.getInteractPics().add(pic);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}
	
	@Override
	public LiveDetailRepository getRepository() {
		return (LiveDetailRepository)super.getRepository();
	}
}
