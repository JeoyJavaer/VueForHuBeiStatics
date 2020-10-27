package com.neuedu.crm.vo;

public class RecommendGamePvUvVo {
	private String recName;
	private String countDay;
	private int countPV;
	private int countUV;

	public static String[] getHeaders() {
		return new String[] { "推荐位名称", "时间", "PV", "UV" };
	}

	public String getRecName() {
		return recName;
	}

	public void setRecName(String recName) {
		this.recName = recName;
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
