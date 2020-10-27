package com.neuedu.crm.vo;

public class UserActionVo {
	private String userId;
	private String object;
	private String action;
	private String ip;
	private String createDateTimeFmt;
	
	public static String[] getHeaders() {
		return new String[] { "用户Id","目标","用户行为","IP", "创建时间"};
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getObject() {
		return object;
	}

	public void setObject(String object) {
		this.object = object;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCreateDateTimeFmt() {
		return createDateTimeFmt;
	}

	public void setCreateDateTimeFmt(String createDateTimeFmt) {
		this.createDateTimeFmt = createDateTimeFmt;
	}

}
