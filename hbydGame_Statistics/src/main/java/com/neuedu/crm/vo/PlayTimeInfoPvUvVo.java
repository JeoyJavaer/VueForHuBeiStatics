package com.neuedu.crm.vo;

public class PlayTimeInfoPvUvVo {
	private String countDayTime; // 每日时间
	private String countPV; // 每日pv（每日统计）
	private String countUV; // 每日uv（每日统计）

	public static String[] getHeaders() {
		return new String[] { "每日时间", "PV", "UV" };
	}

	public String getCountDayTime() {
		return countDayTime;
	}

	public void setCountDayTime(String countDayTime) {
		this.countDayTime = countDayTime;
	}

	public String getCountPV() {
		return countPV;
	}

	public void setCountPV(String countPV) {
		this.countPV = countPV;
	}

	public String getCountUV() {
		return countUV;
	}

	public void setCountUV(String countUV) {
		this.countUV = countUV;
	}

}
