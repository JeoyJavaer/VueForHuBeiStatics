package com.neuedu.crm.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.neuedu.crm.pub.page.PageParameter;

public class GameCategory {
	private int id;
	private String categoryName; // 分类名称
	private String categoryCode; // 分类代码
	private String icon; // 图标地址
	private String newImgUrl; // 新版分类图片地址
	private int orderSeq; // 排序
	private Date createDateTime;
	private Date modifyDateTime;
	private String selUrl; // 分类选中图
	private String nselUrl; // 未选中图

	private String categoryId; // 推荐位ID
	private String version; // 版本号
	private String userId; // 盒子用户编号
	private String mac; // 盒子mac
	private String pageName; // 来源页面
	private String IP; // 客户端IP
	private String country; // 国家
	private String province; // 省
	private String city; // 市
	private String accessParam;

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private PageParameter page;// 分页参数
	private String createDateTimeFmt;
	private String modifyDateTimeFmt;
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

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getCategoryCode() {
		return categoryCode;
	}

	public void setCategoryCode(String categoryCode) {
		this.categoryCode = categoryCode;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public String getNewImgUrl() {
		return newImgUrl;
	}

	public void setNewImgUrl(String newImgUrl) {
		this.newImgUrl = newImgUrl;
	}

	public int getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(int orderSeq) {
		this.orderSeq = orderSeq;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public Date getModifyDateTime() {
		return modifyDateTime;
	}

	public void setModifyDateTime(Date modifyDateTime) {
		this.modifyDateTime = modifyDateTime;
	}

	public String getSelUrl() {
		return selUrl;
	}

	public void setSelUrl(String selUrl) {
		this.selUrl = selUrl;
	}

	public String getNselUrl() {
		return nselUrl;
	}

	public void setNselUrl(String nselUrl) {
		this.nselUrl = nselUrl;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
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

	public String getCreateDateTimeFmt() {
		if (this.createDateTime != null) {
			return this.sdf.format(this.createDateTime);
		}
		return this.createDateTimeFmt;
	}

	public void setCreateDateTimeFmt(String createDateTimeFmt) {
		this.createDateTimeFmt = createDateTimeFmt;
	}

	public String getModifyDateTimeFmt() {
		if (this.modifyDateTime != null) {
			return this.sdf.format(this.modifyDateTime);
		}
		return this.modifyDateTimeFmt;
	}

	public void setModifyDateTimeFmt(String modifyDateTimeFmt) {
		this.modifyDateTimeFmt = modifyDateTimeFmt;
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

}
