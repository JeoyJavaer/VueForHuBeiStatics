package com.neuedu.crm.vo;

public class LeadboardGameVo {
	private int gameRank; // 每日排名
	private String rankChange; // 每日排名变化
	private String gameName; // 游戏名称
	private String packageName; // 包名
	private String cpName;
	private int countPV; // 每日pv（每日统计）
	private int countUV; // 每日uv（每日统计）
	private int yesterdayCountPV; // 前一日pv（每日统计）
	private int yesterdayCountUV; // 前一日uv（每日统计）
	private String countDayTime; // 每日时间

	public static String[] getHeaders() {
		return new String[] { "排名", "趋势", "游戏名称", "包名", "CP", "PV", "UV", "前一日PV", "前一日UV", "时间" };
	}

	public int getGameRank() {
		return gameRank;
	}

	public void setGameRank(int gameRank) {
		this.gameRank = gameRank;
	}

	public String getRankChange() {
		return rankChange;
	}

	public void setRankChange(String rankChange) {
		this.rankChange = rankChange;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
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

	public int getYesterdayCountPV() {
		return yesterdayCountPV;
	}

	public void setYesterdayCountPV(int yesterdayCountPV) {
		this.yesterdayCountPV = yesterdayCountPV;
	}

	public int getYesterdayCountUV() {
		return yesterdayCountUV;
	}

	public void setYesterdayCountUV(int yesterdayCountUV) {
		this.yesterdayCountUV = yesterdayCountUV;
	}

	public String getCountDayTime() {
		return countDayTime;
	}

	public void setCountDayTime(String countDayTime) {
		this.countDayTime = countDayTime;
	}

}
