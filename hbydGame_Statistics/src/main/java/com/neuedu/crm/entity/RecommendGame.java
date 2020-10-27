package com.neuedu.crm.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.neuedu.crm.pub.page.PageParameter;

public class RecommendGame {
	private int id;
	private String recName;
	private String recId;
	private String version;
	private String userId;
	private String mac;
	private Date recClickTime;
	private String pageName;
	private String IP;
	private String country;
	private String province;
	private String city;
	private String accessParam;

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private PageParameter page;// 分页参数
	private String recClickTimeFmt;
	private String beginDate; // 查询开始时间
	private String endDate; // 查询结束时间
	private String countDay; // 每日时间
	private String countPV; // 每日pv（每日统计）
	private String countUV; // 每日uv（每日统计）

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRecName() {
		return recName;
	}

	public void setRecName(String recName) {
		this.recName = recName;
	}

	public String getRecId() {
		return recId;
	}

	public void setRecId(String recId) {
		this.recId = recId;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
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

	public Date getRecClickTime() {
		return recClickTime;
	}

	public void setRecClickTime(Date recClickTime) {
		this.recClickTime = recClickTime;
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

	public PageParameter getPage() {
		return page;
	}

	public void setPage(PageParameter page) {
		this.page = page;
	}

	public String getRecClickTimeFmt() {
		if (this.recClickTime != null) {
			return this.sdf.format(this.recClickTime);
		}
		return this.recClickTimeFmt;
	}

	public void setRecClickTimeFmt(String recClickTimeFmt) {
		this.recClickTimeFmt = recClickTimeFmt;
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

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public String getCountDay() {
		return countDay;
	}

	public void setCountDay(String countDay) {
		this.countDay = countDay;
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
