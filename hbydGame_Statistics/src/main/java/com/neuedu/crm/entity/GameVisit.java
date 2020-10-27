package com.neuedu.crm.entity;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.neuedu.crm.pub.page.PageParameter;

public class GameVisit {
	private int id;
	private String userId;
	private String mac;
	private Date visitTime;
	private String pageName;
	private String IP;
	private String country;
	private String province;
	private String city;
	private String accessParam; // 从什么位置倒入

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private PageParameter page;// 分页参数
	private String visitTimeFmt;
	private int countPV; // 每日pv（每日统计）
	private int countUV; // 每日uv（每日统计）
	private int countOrder; // 每日订购量（每日统计）
	private String countOrderRate; // 每日转化率（每日统计 订购量/UV）
	private final DecimalFormat df = new DecimalFormat("0.00");
	private int sumPV; // 总pv
	private int sumUV; // 总uv
	private String countDayTime;
	private String beginDate; // 查询开始时间
	private String endDate; // 查询结束时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public Date getVisitTime() {
		return visitTime;
	}

	public void setVisitTime(Date visitTime) {
		this.visitTime = visitTime;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAccessParam() {
		return accessParam;
	}

	public void setAccessParam(String accessParam) {
		this.accessParam = accessParam;
	}

	public String getVisitTimeFmt() {
		if (this.visitTime != null) {
			return this.sdf.format(this.visitTime);
		}
		return this.visitTimeFmt;
	}

	public void setVisitTimeFmt(String visitTimeFmt) {
		this.visitTimeFmt = visitTimeFmt;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
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

	public String getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(String beginDate) {
		this.beginDate = beginDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public PageParameter getPage() {
		return page;
	}

	public void setPage(PageParameter page) {
		this.page = page;
	}

	public String getCountDayTime() {
		return countDayTime;
	}

	public void setCountDayTime(String countDayTime) {
		this.countDayTime = countDayTime;
	}

	public int getCountOrder() {
		return countOrder;
	}

	public void setCountOrder(int countOrder) {
		this.countOrder = countOrder;
	}

	public String getCountOrderRate() {
		if (countUV == 0) {
			return "0%";
		} else {
			return df.format((float) countOrder / countUV * 100) + "%";
		}
	}

	public void setCountOrderRate(String countOrderRate) {
		this.countOrderRate = countOrderRate;
	}

	public int getSumPV() {
		return sumPV;
	}

	public void setSumPV(int sumPV) {
		this.sumPV = sumPV;
	}

	public int getSumUV() {
		return sumUV;
	}

	public void setSumUV(int sumUV) {
		this.sumUV = sumUV;
	}

}
