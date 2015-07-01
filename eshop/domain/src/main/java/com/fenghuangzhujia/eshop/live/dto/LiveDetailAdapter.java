package com.fenghuangzhujia.eshop.live.dto;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.eshop.live.Live;
import com.fenghuangzhujia.eshop.live.LiveDetail;
import com.fenghuangzhujia.eshop.live.LiveRepository;
import com.fenghuangzhujia.eshop.worker.Worker;
import com.fenghuangzhujia.eshop.worker.WorkerRepository;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class LiveDetailAdapter extends AbstractDtoAdapter<LiveDetail, LiveDetailDto, LiveDetailInputArgs> {

	@Autowired
	private WorkerRepository workerRepository;
	@Autowired
	private MediaService mediaService;
	@Autowired
	private LiveRepository liveRepository;
	
	@Override
	public LiveDetailDto postConvert(LiveDetail d, LiveDetailDto t) {
		return t;
	}

	@Override
	public LiveDetail postConvertToDo(LiveDetailInputArgs i, LiveDetail d) {
		d=postUpdate(i, d);
		String liveId=i.getLiveId();
		Live live=liveRepository.findOne(liveId);
		d.setLive(live);
		try {
			Set<MultipartFile> picFiles=i.getPicFiles();
			if(picFiles!=null) {
				Set<MediaContent> pics=new HashSet<>();
				for (MultipartFile picFile : picFiles) {
					MediaContent pic=mediaService.save(picFile);
					pics.add(pic);
				}
				d.setPics(pics);
			}
			
			Set<MultipartFile> interactPicFiles=i.getInteractPicFiles();
			if(interactPicFiles!=null) {
				Set<MediaContent> interactPics=new HashSet<>();
				for (MultipartFile picFile : interactPicFiles) {
					MediaContent pic=mediaService.save(picFile);
					interactPics.add(pic);
				}
				d.setInteractPics(interactPics);
			}
			
			return d;
		} catch (IOException e) {
			throw new ErrorCodeException(SystemErrorCodes.OTHER, "文件保存错误！");
		}		
	}

	@Override
	public LiveDetail postUpdate(LiveDetailInputArgs i, LiveDetail d) {
		Set<String> workerids=i.getWorkerids();		
		if(workerids!=null) {
			Set<Worker> workers=new HashSet<>();
			for (String workerid : workerids) {
				Worker worker=workerRepository.findOne(workerid);
				workers.add(worker);
			}
			d.setWorkers(workers);
		}
		return d;
	}

}
