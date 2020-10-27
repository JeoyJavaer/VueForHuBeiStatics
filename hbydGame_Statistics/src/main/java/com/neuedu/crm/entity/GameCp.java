package com.neuedu.crm.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.neuedu.crm.pub.page.PageParameter;

public class GameCp {
	private int id;
	private String cpId;
	private String cpName;
	private String memo;
	private Date createDateTime;
	private Date modifyDateTime;

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private PageParameter page;// 分页参数
	private String createDateTimeFmt;
	private String modifyDateTimeFmt;
	private int orderCount; // 订购量
	private int gameCount; // 游戏点击量
	private int orderAllCount; // 发起订购量
	private String beginDate; // 查询开始时间
	private String endDate; // 查询结束时间

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
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

	public SimpleDateFormat getSdf() {
		return sdf;
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

}
