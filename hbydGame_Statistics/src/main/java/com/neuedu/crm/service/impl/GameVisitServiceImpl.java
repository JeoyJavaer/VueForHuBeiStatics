package com.neuedu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.crm.dao.GameVisitDao;
import com.neuedu.crm.entity.GameVisit;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IGameVisitService;

@Service
public class GameVisitServiceImpl implements IGameVisitService {

	@Autowired
	private GameVisitDao gameVisitDao;

	@Override
	public List<GameVisit> getListDayCountPvUvByPage(Map<String, String> map, PageParameter page) {
		GameVisit gameVisit = new GameVisit();
		gameVisit.setBeginDate(map.get("beginDate"));
		gameVisit.setEndDate(map.get("endDate"));
		gameVisit.setPage(page);
		List<GameVisit> list = this.gameVisitDao.getListDayCountPvUvByPage(gameVisit);
		return list;
	}

	@Override
	public List<GameVisit> getListDayCountPvUvExport(Map<String, String> map) {
		List<GameVisit> musicInfos = this.gameVisitDao.getListDayCountPvUvExport(map);
		return musicInfos;
	}

	@Override
	public List<GameVisit> getListByPage(Map<String, String> map, PageParameter page) {
		GameVisit gameVisit = new GameVisit();
		gameVisit.setPage(page);
		gameVisit.setUserId(map.get("userId"));
		gameVisit.setPageName(map.get("pageName"));
		gameVisit.setBeginDate(map.get("beginDate"));
		gameVisit.setEndDate(map.get("endDate"));
		List<GameVisit> list = this.gameVisitDao.getListByPage(gameVisit);
		return list;
	}

	@Override
	public List<GameVisit> getListExport(Map<String, String> map) {
		List<GameVisit> musicInfos = this.gameVisitDao.getListExport(map);
		return musicInfos;
	}

	@Override
	public List<GameVisit> getSumPvUv(Map<String, String> map) {
		GameVisit gameVisit = new GameVisit();
		gameVisit.setBeginDate(map.get("beginDate"));
		gameVisit.setEndDate(map.get("endDate"));
		List<GameVisit> list = this.gameVisitDao.getSumPvUv(gameVisit);
		return list;
	}

}
