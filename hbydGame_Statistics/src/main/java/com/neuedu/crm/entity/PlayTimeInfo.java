package com.neuedu.crm.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.neuedu.crm.pub.page.PageParameter;

public class PlayTimeInfo {
	private int id;
	private String packageName; // 包名
	private String gameName; // 游戏名称
	private String pageName; // 页面名称
	private String version; // 版本号
	private String mac; // MAC地址
	private String userId; // 盒子用户userId
	private Date createDateTime; // 创建时间
	private int IP; // 客户端IP
	private String country; // 国家
	private String province; // 省
	private String city; // 市

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String cpId;
	private String cpName;
	private PageParameter page;// 分页参数
	private String beginDate;// 查询开始时间
	private String endDate;// 查询结束时间
	private String createDateTimeFmt;
	private String countDayTime; // 每日时间
	private String countPV; // 每日pv（每日统计）
	private String countUV; // 每日uv（每日统计）

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getPageName() {
		return pageName;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getMac() {
		return mac;
	}

	public void setMac(String mac) {
		this.mac = mac;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public int getIP() {
		return IP;
	}

	public void setIP(int iP) {
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

	public String getCreateDateTimeFmt() {
		if (this.createDateTime != null) {
			return this.sdf.format(this.createDateTime);
		}
		return this.createDateTimeFmt;
	}

	public void setCreateDateTimeFmt(String createDateTimeFmt) {
		this.createDateTimeFmt = createDateTimeFmt;
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

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public PageParameter getPage() {
		return page;
	}

	public void setPage(PageParameter page) {
		this.page = page;
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

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String getCountDayTime() {
		return countDayTime;
	}

	public void setCountDayTime(String countDayTime) {
		this.countDayTime = countDayTime;
	}

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

}
