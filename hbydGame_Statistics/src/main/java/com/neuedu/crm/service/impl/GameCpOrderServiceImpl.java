package com.neuedu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.crm.dao.GameCpOrderDao;
import com.neuedu.crm.entity.GameCp;
import com.neuedu.crm.entity.GameVisit;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IGameCpOrderService;

@Service
public class GameCpOrderServiceImpl implements IGameCpOrderService {

	@Autowired
	private GameCpOrderDao gameCpOrderDao;

	@Override
	public List<GameCp> getListByPage(Map<String, String> map, PageParameter page) {
		GameCp gameCp = new GameCp();
		gameCp.setBeginDate(map.get("beginDate"));
		gameCp.setEndDate(map.get("endDate"));
		gameCp.setCpId(map.get("cpId"));
		gameCp.setPage(page);
		List<GameCp> list = this.gameCpOrderDao.getListByPage(gameCp);
		return list;
	}

	@Override
	public List<GameCp> getListExport(Map<String, String> map) {
		List<GameCp> list = this.gameCpOrderDao.gamelistExport(map);
		return list;
	}

}
