package com.neuedu.crm.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.neuedu.crm.pub.page.PageParameter;

public class ActivityGame {
	private int id;
	private String packageName;
	private String activityName;
	private String version;
	private String mac;
	private String userId;
	private Date createDateTime;
	private String IP;
	private Long ms;
	private String country;
	private String province;
	private String city;
	private String activityId;
//	private int type; // 类型 0 轮播位 1 小游戏
	private String type; // 类型 0 轮播位 1 小游戏
	private String pageName;

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private PageParameter page;// 分页参数
	private String createDateTimeFmt;
	private String typeFmt; // 类型 0 轮播位 1 小游戏
	private String countDay; // 每日时间
	private int countPV; // 每日pv（每日统计）
	private int countUV; // 每日uv（每日统计）
	private String beginDate; // 查询开始时间
	private String endDate; // 查询结束时间

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

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
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

	public String getIP() {
		return IP;
	}

	public void setIP(String iP) {
		IP = iP;
	}

	public Long getMs() {
		return ms;
	}

	public void setMs(Long ms) {
		this.ms = ms;
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

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId;
	}

//	public int getType() {
//		return type;
//	}
//
//	public void setType(int type) {
//		this.type = type;
//	}

	public String getPageName() {
		return pageName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public void setPageName(String pageName) {
		this.pageName = pageName;
	}

	public PageParameter getPage() {
		return page;
	}

	public void setPage(PageParameter page) {
		this.page = page;
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

	public String getTypeFmt() {
		// 类型 0 轮播位 1 小游戏
		if (type != null && type.equals("0")) {
			return "轮播位";
		}
		if (type != null && type.equals("1")) {
			return "小游戏";
		}
		return null;
	}

	public void setTypeFmt(String typeFmt) {
		this.typeFmt = typeFmt;
	}

}
