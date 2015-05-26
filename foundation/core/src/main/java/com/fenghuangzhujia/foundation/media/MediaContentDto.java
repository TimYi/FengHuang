package com.fenghuangzhujia.foundation.media;

import com.fenghuangzhujia.foundation.core.entity.Identified;

public class MediaContentDto implements Identified<String> {
	private String id;
	private String url;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
}
