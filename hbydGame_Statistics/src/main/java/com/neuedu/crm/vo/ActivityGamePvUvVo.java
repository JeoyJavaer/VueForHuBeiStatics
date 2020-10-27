package com.neuedu.crm.vo;

public class ActivityGamePvUvVo {
	private String activityName;
	private String activityId;
	private String typeFmt;
	private String countDay;
	private int countPV;
	private int countUV;

	public static String[] getHeaders() {
		return new String[] { "页面名称","轮播位ID/小游戏ID", "类型", "时间", "PV", "UV" };
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

	public String getTypeFmt() {
		return typeFmt;
	}

	public void setTypeFmt(String typeFmt) {
		this.typeFmt = typeFmt;
	}

	public String getCountDay() {
		return countDay;
	}

	public void setCountDay(String countDay) {
		this.countDay = countDay;
	}

	public int getCountPV() {
		return countPV;
	}

	public void setCountPV(int countPV) {
		this.countPV = countPV;
	}

	public int getCountUV() {
		return countUV;
	}

	public void setCountUV(int countUV) {
		this.countUV = countUV;
	}

}
