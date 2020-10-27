package com.neuedu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.crm.dao.GameDetailDao;
import com.neuedu.crm.entity.GameDetail;
import com.neuedu.crm.entity.GameVisit;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IGameDetailService;

@Service
public class GameDetailServiceImpl implements IGameDetailService {

	@Autowired
	private GameDetailDao gameDetailDao;

	@Override
	public List<GameDetail> getListDayCountPvUvByPage(Map<String, String> map, PageParameter page) {
		GameDetail gameDetail = new GameDetail();
		gameDetail.setBeginDate(map.get("beginDate"));
		gameDetail.setEndDate(map.get("endDate"));
		gameDetail.setPage(page);
		List<GameDetail> list = this.gameDetailDao.getListDayCountPvUvByPage(gameDetail);
		return list;
	}

	@Override
	public List<GameDetail> getListDayCountPvUvExport(Map<String, String> map) {
		List<GameDetail> musicInfos = this.gameDetailDao.getListDayCountPvUvExport(map);
		return musicInfos;
	}

}
