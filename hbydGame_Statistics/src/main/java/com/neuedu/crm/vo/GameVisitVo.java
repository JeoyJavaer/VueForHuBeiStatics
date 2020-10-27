package com.neuedu.crm.vo;

public class GameVisitVo {
	private String countDayTime; // 每日时间
	private int countPV; // 每日pv（每日统计）
	private int countUV; // 每日uv（每日统计）
	private int countOrder; // 每日订购量（每日统计）
	private String countOrderRate; // 每日转化率（每日统计 订购量/UV）

	public static String[] getHeaders() {
		return new String[] { "每日时间", "PV", "UV", "订购量", "转化率" };
	}

	public String getCountDayTime() {
		return countDayTime;
	}

	public void setCountDayTime(String countDayTime) {
		this.countDayTime = countDayTime;
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

	public int getCountOrder() {
		return countOrder;
	}

	public void setCountOrder(int countOrder) {
		this.countOrder = countOrder;
	}

	public String getCountOrderRate() {
		return countOrderRate;
	}

	public void setCountOrderRate(String countOrderRate) {
		this.countOrderRate = countOrderRate;
	}

}
