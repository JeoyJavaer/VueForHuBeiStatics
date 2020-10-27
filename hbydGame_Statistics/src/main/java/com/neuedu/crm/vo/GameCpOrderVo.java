package com.neuedu.crm.vo;

public class GameCpOrderVo {
	private String cpId;
	private String cpName;
	private int orderCount; // 订购量
	private int gameCount; // 游戏点击量
	private int orderAllCount; // 发起订购量

	public static String[] getHeaders() {
		return new String[] { "cpId", "cp", "订购量", "游戏点击量", "订购发起量" };
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
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
