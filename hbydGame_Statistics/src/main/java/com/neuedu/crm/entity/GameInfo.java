package com.neuedu.crm.entity;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.neuedu.crm.pub.page.PageParameter;

public class GameInfo {
	private int gameId;
	private String cpId; // CP号
	private String gameName; // 游戏名称
	private String englishName; // 英文名称
	private String packageName; // 包名
	private String versionName; // 版本名称
	private String versionCode; // 版本号
	private String iconUrl; // 图标地址
	private String iconBigUrl; // 大图标
	private long byteSize; // 字节数
	private String gameSize; // 游戏大小
	private String locationUrl; // 文件地址
	private String fileType; // 0:单个apk游戏 3:网页游戏 4:视频 5:云游戏 6活动 7专题
	private String gameDesc; // 游戏详细描述
	private String shortDesc; // 一句话描述
	private String online; // 上线状态
	private int dlCounter; // 显示下载量，可运营修改
	private int downloadFee; // 下载金额
	private String isFree; // 是否免费下载，0免费 1付费
	private String operationType; // 操作类型，1 多人游戏，2 手柄游戏，3，遥控器游戏，逗号分隔
	private String mark; // 游戏标记：0 无 1 最新 2 热门
	private String language; // 语言，0:中文 1:英文
	private String suitable; // 适合人群，0：0岁以上；1：6岁以上；2：12岁以上；3：18岁以上
	private String remark; // 注意事项
	private Date lastUpdateTime; // 最后更新版本时间
	private String status; // 状态 0-可用 -1-不可用
	private Date createDateTime; // 创建时间
	private Date modifyDateTime; // 修改时间
	private String memo; // 备注
	private String upcoming; // 上线状态： 0 已上线； 1 即将上线
	private String smallPic; // 小图
	private String bigPic; // 大图
	private String packageMonth; // 是否包月 0-是 1-否
	private String firstLetter; // 游戏名称首字母
	private String grantUrl; // 授权文件下载地址
	private String installType; // 安装类型 0 表示普通安装 1 droidPlugin 安装 2 rePlugin 默认是0
	private String launchActivity; // 表示 APK的LaunchActivity
	private String appId; // 对应卓影游戏APPID
	private int startUpCounter; // 游戏启动量
	private String score; // 星级

	private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	private PageParameter page;// 分页参数
	private String lastUpdateTimeFmt;
	private String createDateTimeFmt;
	private String modifyDateTimeFmt;
	private String cpName;
	private int orderCount; // 发起订购量
	private int gameCount; // 游戏点击量
	private int orderAllCount; // 发起订购量
	private String beginDate; // 查询开始时间
	private String endDate; // 查询结束时间

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
	}

	public String getCpId() {
		return cpId;
	}

	public void setCpId(String cpId) {
		this.cpId = cpId;
	}

	public String getGameName() {
		return gameName;
	}

	public void setGameName(String gameName) {
		this.gameName = gameName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public String getPackageName() {
		return packageName;
	}

	public void setPackageName(String packageName) {
		this.packageName = packageName;
	}

	public String getVersionName() {
		return versionName;
	}

	public void setVersionName(String versionName) {
		this.versionName = versionName;
	}

	public String getVersionCode() {
		return versionCode;
	}

	public void setVersionCode(String versionCode) {
		this.versionCode = versionCode;
	}

	public String getIconUrl() {
		return iconUrl;
	}

	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}

	public String getIconBigUrl() {
		return iconBigUrl;
	}

	public void setIconBigUrl(String iconBigUrl) {
		this.iconBigUrl = iconBigUrl;
	}

	public long getByteSize() {
		return byteSize;
	}

	public void setByteSize(long byteSize) {
		this.byteSize = byteSize;
	}

	public String getGameSize() {
		return gameSize;
	}

	public void setGameSize(String gameSize) {
		this.gameSize = gameSize;
	}

	public String getLocationUrl() {
		return locationUrl;
	}

	public void setLocationUrl(String locationUrl) {
		this.locationUrl = locationUrl;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getGameDesc() {
		return gameDesc;
	}

	public void setGameDesc(String gameDesc) {
		this.gameDesc = gameDesc;
	}

	public String getShortDesc() {
		return shortDesc;
	}

	public void setShortDesc(String shortDesc) {
		this.shortDesc = shortDesc;
	}

	public String getOnline() {
		return online;
	}

	public void setOnline(String online) {
		this.online = online;
	}

	public int getDlCounter() {
		return dlCounter;
	}

	public void setDlCounter(int dlCounter) {
		this.dlCounter = dlCounter;
	}

	public String getIsFree() {
		return isFree;
	}

	public void setIsFree(String isFree) {
		this.isFree = isFree;
	}

	public String getMark() {
		return mark;
	}

	public void setMark(String mark) {
		this.mark = mark;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getSuitable() {
		return suitable;
	}

	public void setSuitable(String suitable) {
		this.suitable = suitable;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
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

	public String getMemo() {
		return memo;
	}

	public void setMemo(String memo) {
		this.memo = memo;
	}

	public String getUpcoming() {
		return upcoming;
	}

	public void setUpcoming(String upcoming) {
		this.upcoming = upcoming;
	}

	public String getSmallPic() {
		return smallPic;
	}

	public void setSmallPic(String smallPic) {
		this.smallPic = smallPic;
	}

	public String getBigPic() {
		return bigPic;
	}

	public void setBigPic(String bigPic) {
		this.bigPic = bigPic;
	}

	public String getPackageMonth() {
		return packageMonth;
	}

	public void setPackageMonth(String packageMonth) {
		this.packageMonth = packageMonth;
	}

	public String getFirstLetter() {
		return firstLetter;
	}

	public void setFirstLetter(String firstLetter) {
		this.firstLetter = firstLetter;
	}

	public String getGrantUrl() {
		return grantUrl;
	}

	public void setGrantUrl(String grantUrl) {
		this.grantUrl = grantUrl;
	}

	public String getInstallType() {
		return installType;
	}

	public void setInstallType(String installType) {
		this.installType = installType;
	}

	public String getLaunchActivity() {
		return launchActivity;
	}

	public void setLaunchActivity(String launchActivity) {
		this.launchActivity = launchActivity;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public int getStartUpCounter() {
		return startUpCounter;
	}

	public void setStartUpCounter(int startUpCounter) {
		this.startUpCounter = startUpCounter;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getLastUpdateTimeFmt() {
		if (this.lastUpdateTime != null) {
			return this.sdf.format(this.lastUpdateTime);
		}
		return this.lastUpdateTimeFmt;
	}

	public void setLastUpdateTimeFmt(String lastUpdateTimeFmt) {
		this.lastUpdateTimeFmt = lastUpdateTimeFmt;
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

	public int getDownloadFee() {
		return downloadFee;
	}

	public void setDownloadFee(int downloadFee) {
		this.downloadFee = downloadFee;
	}

	public String getOperationType() {
		return operationType;
	}

	public void setOperationType(String operationType) {
		this.operationType = operationType;
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

	public String getCpName() {
		return cpName;
	}

	public void setCpName(String cpName) {
		this.cpName = cpName;
	}

}
