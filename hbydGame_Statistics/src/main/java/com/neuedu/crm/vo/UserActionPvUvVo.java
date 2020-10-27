package com.neuedu.crm.vo;

public class UserActionPvUvVo {
	private String countDay; // 每日时间
	private int countPV; // 每日pv（每日统计）
	private int countUV; // 每日uv（每日统计）

	public static String[] getHeaders() {
		return new String[] { "时间", "PV", "UV" };
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
