package com.neuedu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.crm.dao.GameTimeDao;
import com.neuedu.crm.entity.PlayTimeInfo;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IPlayTimeService;

@Service
public class PlayTimeServiceImpl implements IPlayTimeService {

	@Autowired
	private GameTimeDao gameTimeDao;

	@Override
	public List<PlayTimeInfo> getListPlayTimeDayByPage(Map<String, String> map, PageParameter page) {
		PlayTimeInfo playTimeInfo = new PlayTimeInfo();
		playTimeInfo.setPage(page);
		// 其他查询参数
		String gameName = map.get("gameName");
		String packageName = map.get("packageName");
		String cpId = map.get("cpId");
		String beginDate = map.get("beginDate");
		String endDate = map.get("endDate");
		playTimeInfo.setGameName(gameName);
		playTimeInfo.setPackageName(packageName);
		playTimeInfo.setCpId(cpId);
		playTimeInfo.setBeginDate(beginDate);
		playTimeInfo.setEndDate(endDate);
		List<PlayTimeInfo> list = this.gameTimeDao.getListPlayTimeDayByPage(playTimeInfo);
		return list;
	}

	@Override
	public List<PlayTimeInfo> getListDayCountPvUvByPage(Map<String, String> map, PageParameter page) {
		PlayTimeInfo playTimeInfo = new PlayTimeInfo();
		playTimeInfo.setPage(page);
		// 其他查询参数
		String beginDate = map.get("beginDate");
		String endDate = map.get("endDate");
		playTimeInfo.setBeginDate(beginDate);
		playTimeInfo.setEndDate(endDate);
		List<PlayTimeInfo> list = this.gameTimeDao.getListDayCountPvUvByPage(playTimeInfo);
		return list;
	}

	@Override
	public List<PlayTimeInfo> gamelistExport(Map<String, String> map) {
		List<PlayTimeInfo> musicInfos = this.gameTimeDao.gamelistExport(map);
		return musicInfos;
	}

	@Override
	public List<PlayTimeInfo> getListDayCountPvUvExport(Map<String, String> map) {
		List<PlayTimeInfo> musicInfos = this.gameTimeDao.getListDayCountPvUvExport(map);
		return musicInfos;
	}

}
