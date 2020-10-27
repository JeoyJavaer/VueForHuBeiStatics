package com.neuedu.crm.vo;

public class PlayTimeInfoVo {
	private String gameName; // 游戏名称
	private String packageName; // 包名
	private String cpName;
	private String countPV; // 每日pv（每日统计）
	private String countUV; // 每日uv（每日统计）
	private String countDayTime; // 每日时间

	public static String[] getHeaders() {
		return new String[] { "游戏名称", "包名", "CP", "PV", "UV", "每日时间" };
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
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

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

}
