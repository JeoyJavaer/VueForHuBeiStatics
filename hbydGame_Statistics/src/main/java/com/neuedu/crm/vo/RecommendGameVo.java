package com.neuedu.crm.vo;

public class RecommendGameVo {
	private String userId;
	private String recName;
	private String pageName;
	private String recClickTimeFmt;

	public static String[] getHeaders() {
		return new String[] { "用户Id", "推荐位名称", "来源页面", "创建时间" };
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRecName() {
		return recName;
	}

	public void setRecName(String recName) {
		this.recName = recName;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getRecClickTimeFmt() {
		return recClickTimeFmt;
	}

	public void setRecClickTimeFmt(String recClickTimeFmt) {
		this.recClickTimeFmt = recClickTimeFmt;
	}

}
