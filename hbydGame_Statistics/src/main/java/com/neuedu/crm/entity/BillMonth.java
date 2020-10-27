package com.neuedu.crm.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.neuedu.crm.pub.page.PageParameter;

public class BillMonth {
	private String orderNo; // 包月订单流水号
	private String userId; // 设备编号
	private String orderId; // BOSS订单号
	private String total_fee; // 实际消费金额
	private String productId; // 产品编号
	private String productName; // 产品名称
	private int status; // 当前状态 1正常 2退订 3失败
	private String ipAddress; // 订购时ip地址
	private Date createTime; // 创建日期
	private Date endTime; // 有效日期
	private Date updateTime; // 更新时间
	private String isRenew; // 是否有自动续订，1是，2否
	private String monthlyName; // 中九包月名称
	private String monthlyNo; // 中九包月编号
	private String uploadFlag; // 帐单上传标识 0未上传 1已上传
	private String catvid; // 广电号
	private String customID; // 客户编号
	private String transactionID; // cp订单号
	private String bossOrderNo;
	private String action;
	private String gameId; // 游戏编号

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private PageParameter page;// 分页参数
	private String cpId;
	private String cpName;
	private String createTimeFmt;
	private String endTimeFmt;
	private String updateTimeFmt;
	private String beginDate; // 查询开始时间
	private String endDate; // 查询结束时间
	private String countDay; // 每日时间
	private String countTotalFee; // 每日实际消费金额

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getTotal_fee() {
		return total_fee;
	}

	public void setTotal_fee(String total_fee) {
		this.total_fee = total_fee;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getIsRenew() {
		return isRenew;
	}

	public void setIsRenew(String isRenew) {
		this.isRenew = isRenew;
	}

	public String getMonthlyName() {
		return monthlyName;
	}

	public void setMonthlyName(String monthlyName) {
		this.monthlyName = monthlyName;
	}

	public String getMonthlyNo() {
		return monthlyNo;
	}

	public void setMonthlyNo(String monthlyNo) {
		this.monthlyNo = monthlyNo;
	}

	public String getUploadFlag() {
		return uploadFlag;
	}

	public void setUploadFlag(String uploadFlag) {
		this.uploadFlag = uploadFlag;
	}

	public String getCatvid() {
		return catvid;
	}

	public void setCatvid(String catvid) {
		this.catvid = catvid;
	}

	public String getCustomID() {
		return customID;
	}

	public void setCustomID(String customID) {
		this.customID = customID;
	}

	public String getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	public String getBossOrderNo() {
		return bossOrderNo;
	}

	public void setBossOrderNo(String bossOrderNo) {
		this.bossOrderNo = bossOrderNo;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getGameId() {
		return gameId;
	}

	public void setGameId(String gameId) {
		this.gameId = gameId;
	}

	public PageParameter getPage() {
		return page;
	}

	public void setPage(PageParameter page) {
		this.page = page;
	}

	public String getCreateTimeFmt() {
		if (this.createTime != null) {
			return this.sdf.format(this.createTime);
		}
		return this.createTimeFmt;
	}

	public void setCreateTimeFmt(String createTimeFmt) {
		this.createTimeFmt = createTimeFmt;
	}

	public String getEndTimeFmt() {
		if (this.endTime != null) {
			return this.sdf.format(this.endTime);
		}
		return this.endTimeFmt;
	}

	public void setEndTimeFmt(String endTimeFmt) {
		this.endTimeFmt = endTimeFmt;
	}

	public String getUpdateTimeFmt() {
		if (this.updateTime != null) {
			return this.sdf.format(this.updateTime);
		}
		return this.updateTimeFmt;
	}

	public void setUpdateTimeFmt(String updateTimeFmt) {
		this.updateTimeFmt = updateTimeFmt;
	}

	public String getCountDay() {
		return countDay;
	}

	public void setCountDay(String countDay) {
		this.countDay = countDay;
	}

	public String getCountTotalFee() {
		return countTotalFee;
	}

	public void setCountTotalFee(String countTotalFee) {
		this.countTotalFee = countTotalFee;
	}

	public SimpleDateFormat getSdf() {
		return sdf;
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

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

}
