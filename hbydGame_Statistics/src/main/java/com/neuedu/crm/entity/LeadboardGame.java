package com.neuedu.crm.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.neuedu.crm.pub.page.PageParameter;

public class LeadboardGame {
	private int id;
	private String packageName; // 包名
	private String gameName; // 游戏名称
	private Date createDateTime; // 创建时间

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private String cpId;
	private String cpName;
	private PageParameter page; // 分页参数
	private String beginDate; // 查询开始时间
	private String endDate; // 查询结束时间
	private String dayDate; // 查询一天时间
	private String createDateTimeFmt;
	private String countDayTime; // 每日时间
	private int countPV; // 每日pv（每日统计）
	private int countUV; // 每日uv（每日统计）
	private int yesterdayCountPV; // 前一日pv（每日统计）
	private int yesterdayCountUV; // 前一日uv（每日统计）
	private int gameRank; // 每日排名
	private String rankChange; // 每日排名变化

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

	public String getCreateDateTimeFmt() {
		if (this.createDateTime != null) {
			return this.sdf.format(this.createDateTime);
		}
		return this.createDateTimeFmt;
	}

	public void setCreateDateTimeFmt(String createDateTimeFmt) {
		this.createDateTimeFmt = createDateTimeFmt;
	}

	public String getCountDayTime() {
		return countDayTime;
	}

	public void setCountDayTime(String countDayTime) {
		this.countDayTime = countDayTime;
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

	public SimpleDateFormat getSdf() {
		return sdf;
	}

	public Date getCreateDateTime() {
		return createDateTime;
	}

	public void setCreateDateTime(Date createDateTime) {
		this.createDateTime = createDateTime;
	}

	public String getDayDate() {
		return dayDate;
	}

	public void setDayDate(String dayDate) {
		this.dayDate = dayDate;
	}

	public int getGameRank() {
		return gameRank;
	}

	public void setGameRank(int gameRank) {
		this.gameRank = gameRank;
	}

	public String getRankChange() {
		return rankChange;
	}

	public void setRankChange(String rankChange) {
		this.rankChange = rankChange;
	}

	public int getYesterdayCountPV() {
		return yesterdayCountPV;
	}

	public void setYesterdayCountPV(int yesterdayCountPV) {
		this.yesterdayCountPV = yesterdayCountPV;
	}

	public int getYesterdayCountUV() {
		return yesterdayCountUV;
	}

	public void setYesterdayCountUV(int yesterdayCountUV) {
		this.yesterdayCountUV = yesterdayCountUV;
	}

}
