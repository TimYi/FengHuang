package com.fenghuangzhujia.eshop.cases.dto;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.fenghuangzhujia.eshop.cases.CaseTag;
import com.fenghuangzhujia.eshop.cases.CaseTagRepository;
import com.fenghuangzhujia.eshop.cases.DecorateCase;
import com.fenghuangzhujia.eshop.core.base.SystemErrorCodes;
import com.fenghuangzhujia.foundation.core.dto.adapter.AbstractDtoAdapter;
import com.fenghuangzhujia.foundation.core.rest.ErrorCodeException;
import com.fenghuangzhujia.foundation.media.MediaContent;
import com.fenghuangzhujia.foundation.media.MediaService;

@Component
public class DecorateCaseDtoAdapter extends AbstractDtoAdapter<DecorateCase, DecorateCaseDto, DecorateCaseInputArgs> {

	@Autowired
	private MediaService mediaService;
	@Autowired
	private CaseTagRepository caseTagRepository;
	
	
	@Override
	public DecorateCaseDto postConvert(DecorateCase d, DecorateCaseDto t) {
		return t;
	}

	@Override
	public DecorateCase postConvertToDo(DecorateCaseInputArgs t, DecorateCase d) {
		return postUpdate(t, d);
	}

	@Override
	public DecorateCase postUpdate(DecorateCaseInputArgs t, DecorateCase d) {
		try {
			MultipartFile mainPicFile=t.getMainPicFile();
			if(mainPicFile!=null) {
				MediaContent mainPic=d.getMainPic();
				mainPic=mediaService.update(mainPic, mainPicFile);
				d.setMainPic(mainPic);
			}
			String tagExpression=t.getTagExpression();
			if(StringUtils.isNotBlank(tagExpression)) {
				String[] tagStrings=StringUtils.split(tagExpression, ' ');
				Set<CaseTag> tags=new HashSet<>();
				for (String tagstring : tagStrings) {
					CaseTag tag=caseTagRepository.findOne(tagstring);
					if(tag==null) {
						tag=new CaseTag();
						tag.setName(tagstring);
					}					
					tags.add(tag);
				}
				d.setTags(tags);
			}
			return d;
		} catch (IOException e) {
			throw new ErrorCodeException(SystemErrorCodes.FILE_ERROR, e.getMessage());
		}
	}

}
