package com.neuedu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.crm.dao.GameInfoDao;
import com.neuedu.crm.entity.GameInfo;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IGameInfoService;

@Service
public class GameInfoServiceImpl implements IGameInfoService {

	@Autowired
	private GameInfoDao gameInfoDao;

	@Override
	public List<GameInfo> getOrderListByPage(Map<String, String> map, PageParameter page) {
		GameInfo gameInfo = new GameInfo();
		gameInfo.setPage(page);
		gameInfo.setCpId(map.get("cpId"));
		gameInfo.setGameName(map.get("gameName"));
		gameInfo.setPackageName(map.get("packageName"));
		gameInfo.setBeginDate(map.get("beginDate"));
		gameInfo.setEndDate(map.get("endDate"));
		List<GameInfo> list = this.gameInfoDao.getOrderListByPage(gameInfo);
		return list;
	}

	@Override
	public List<GameInfo> getOrderListExport(Map<String, String> map) {
		List<GameInfo> list = this.gameInfoDao.gameOrderlistExport(map);
		return list;
	}

}
