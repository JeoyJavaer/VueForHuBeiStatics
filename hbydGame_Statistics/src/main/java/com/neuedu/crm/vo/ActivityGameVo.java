package com.neuedu.crm.vo;

public class ActivityGameVo {
	private String userId;
	private String activityName;
	private String typeFmt;
	private String createDateTimeFmt;

	public static String[] getHeaders() {
		return new String[] { "用户Id", "页面名称", "类型", "创建时间" };
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getTypeFmt() {
		return typeFmt;
	}

	public void setTypeFmt(String typeFmt) {
		this.typeFmt = typeFmt;
	}

	public String getCreateDateTimeFmt() {
		return createDateTimeFmt;
	}

	public void setCreateDateTimeFmt(String createDateTimeFmt) {
		this.createDateTimeFmt = createDateTimeFmt;
	}

}
