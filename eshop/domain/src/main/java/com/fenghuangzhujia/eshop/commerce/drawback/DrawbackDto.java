package com.fenghuangzhujia.eshop.commerce.drawback;

import java.util.Date;

import com.fenghuangzhujia.eshop.commerce.drawback.Drawback.DrawbackStatus;
import com.fenghuangzhujia.eshop.commerce.order.GoodOrder.OrderStatus;
import com.fenghuangzhujia.eshop.core.user.dto.UserDto;
import com.fenghuangzhujia.foundation.core.dto.DtoBaseModel;
import com.fenghuangzhujia.foundation.media.MediaContentDto;

public class DrawbackDto extends DtoBaseModel {

	/**申请退款的订单*/
	private OrderDto order;
	/**用户输入退款原因*/
	private String reason;
	/**不同意退款原因*/
	private String reasonForDisapprove;
	/**审核处理人*/
	private String authenticater;
	/**处理日期*/
	private Date processDate;
	/**退款状态*/
	private DrawbackStatus status;
	
	public OrderDto getOrder() {
		return order;
	}
	public void setOrder(OrderDto order) {
		this.order = order;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getReasonForDisapprove() {
		return reasonForDisapprove;
	}
	public void setReasonForDisapprove(String reasonForDisapprove) {
		this.reasonForDisapprove = reasonForDisapprove;
	}
	public String getAuthenticater() {
		return authenticater;
	}
	public void setAuthenticater(String authenticater) {
		this.authenticater = authenticater;
	}
	public Date getProcessDate() {
		return processDate;
	}
	public void setProcessDate(Date processDate) {
		this.processDate = processDate;
	}
	public DrawbackStatus getStatus() {
		return status;
	}
	public void setStatus(DrawbackStatus status) {
		this.status = status;
	}
	
	public static class OrderDto {
		private double price;	
		private OrderStatus status;			
		private Integer count;
		private String name;
		private String type;
		private MediaContentDto mainPic;	
		private UserDto user;
		/**交易时间*/
		private Date payTime;
		private String code;
		private String mobile;
		private String realName;
		
		public double getPrice() {
			return price;
		}
		public OrderStatus getStatus() {
			return status;
		}
		public Integer getCount() {
			return count;
		}
		public String getName() {
			return name;
		}
		public String getType() {
			return type;
		}
		public MediaContentDto getMainPic() {
			return mainPic;
		}
		public UserDto getUser() {
			return user;
		}
		public Date getPayTime() {
			return payTime;
		}
		public String getCode() {
			return code;
		}
		public String getMobile() {
			return mobile;
		}
		public String getRealName() {
			return realName;
		}
		public void setPrice(double price) {
			this.price = price;
		}
		public void setStatus(OrderStatus status) {
			this.status = status;
		}
		public void setCount(Integer count) {
			this.count = count;
		}
		public void setName(String name) {
			this.name = name;
		}
		public void setType(String type) {
			this.type = type;
		}
		public void setMainPic(MediaContentDto mainPic) {
			this.mainPic = mainPic;
		}
		public void setUser(UserDto user) {
			this.user = user;
		}
		public void setPayTime(Date payTime) {
			this.payTime = payTime;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public void setMobile(String mobile) {
			this.mobile = mobile;
		}
		public void setRealName(String realName) {
			this.realName = realName;
		}
	}
}
