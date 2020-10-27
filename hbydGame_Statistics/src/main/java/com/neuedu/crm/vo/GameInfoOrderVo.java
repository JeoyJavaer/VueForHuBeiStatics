package com.neuedu.crm.vo;

public class GameInfoOrderVo {
	private String gameName;
	private String packageName;
	private String cpName;
	private int orderCount;
	private int gameCount;
	private int orderAllCount;

	public static String[] getHeaders() {
		return new String[] { "游戏名称", "包名", "cp", "订购量", "游戏点击量", "订购发起量" };
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

	public int getOrderCount() {
		return orderCount;
	}

	public void setOrderCount(int orderCount) {
		this.orderCount = orderCount;
	}

	public int getGameCount() {
		return gameCount;
	}

	public void setGameCount(int gameCount) {
		this.gameCount = gameCount;
	}

	public int getOrderAllCount() {
		return orderAllCount;
	}

	public void setOrderAllCount(int orderAllCount) {
		this.orderAllCount = orderAllCount;
	}

}
