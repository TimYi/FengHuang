package com.fenghuangzhujia.eshop.meteriaManage.meteria.dto;

import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class MeteriaDto extends DtoBaseModel {

	/**排序序号*/
	private int ordernum;
	/**展示图片*/
	private MediaContentDto pic;
	
	public int getOrdernum() {
		return ordernum;
	}
	public void setOrdernum(int ordernum) {
		this.ordernum = ordernum;
	}
	public MediaContentDto getPic() {
		return pic;
	}
	public void setPic(MediaContentDto pic) {
		this.pic = pic;
	}
}
