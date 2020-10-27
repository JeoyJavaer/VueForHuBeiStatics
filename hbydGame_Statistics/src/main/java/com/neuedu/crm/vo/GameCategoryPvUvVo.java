package com.neuedu.crm.vo;

public class GameCategoryPvUvVo {
	private String categoryName;
	private String countDay;
	private int countPV;
	private int countUV;

	public static String[] getHeaders() {
		return new String[] { "分类名称", "时间", "PV", "UV" };
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
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
