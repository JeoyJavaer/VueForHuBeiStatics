package com.neuedu.crm.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.neuedu.crm.pub.page.PageParameter;

public class GameDetail {
	private int id;
	private String packageName; // 包名
	private String gameName; // 游戏名称
	private String pageName; // 页面名称
	private String version; // 版本号
	private String mac; // MAC地址
	private String userId; // 盒子用户userId
	private Date createDateTime; // 创建时间
	private String IP; // 客户端IP
	private String country; // 国家
	private String province; // 省
	private String city; // 市
	private String gameId; // 游戏编号
	private String productId; // 产品编号

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private PageParameter page;// 分页参数
	private String createDateTimeFmt;
	private int countPV; // 每日pv（每日统计）
	private int countUV; // 每日uv（每日统计）
	private String countDayTime;
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

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
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

	public String getCountDayTime() {
		return countDayTime;
	}

	public void setCountDayTime(String countDayTime) {
		this.countDayTime = countDayTime;
	}

}
