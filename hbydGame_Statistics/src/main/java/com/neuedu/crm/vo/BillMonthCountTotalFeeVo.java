package com.neuedu.crm.vo;

public class BillMonthCountTotalFeeVo {
	private String cpName;
	private String countTotalFee; // 每日实际消费金额
	private String countDay; // 每日时间

	public static String[] getHeaders() {
		return new String[] { "CP", "每日实际消费金额", "时间" };
	}

	public String getCountTotalFee() {
		return countTotalFee;
	}

	public void setCountTotalFee(String countTotalFee) {
		this.countTotalFee = countTotalFee;
	}

	public String getCountDay() {
		return countDay;
	}

	public void setCountDay(String countDay) {
		this.countDay = countDay;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

}
