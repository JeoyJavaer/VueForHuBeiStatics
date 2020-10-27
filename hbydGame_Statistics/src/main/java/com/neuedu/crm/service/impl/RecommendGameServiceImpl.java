package com.neuedu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.crm.dao.RecommendGameDao;
import com.neuedu.crm.entity.RecommendGame;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IRecommendGameService;

@Service
public class RecommendGameServiceImpl implements IRecommendGameService {
	@Autowired
	private RecommendGameDao recommendGameDao;

	@Override
	public List<RecommendGame> getListPvUvByPage(Map<String, String> map, PageParameter page) {
		RecommendGame recommendGame = new RecommendGame();
		recommendGame.setPage(page);
		recommendGame.setRecName(map.get("recName"));
		recommendGame.setBeginDate(map.get("beginDate"));
		recommendGame.setEndDate(map.get("endDate"));
		List<RecommendGame> list = this.recommendGameDao.getListPvUvByPage(recommendGame);
		return list;
	}

	@Override
	public List<RecommendGame> getListPvUvExport(Map<String, String> map) {
		List<RecommendGame> list = this.recommendGameDao.getListPvUvExport(map);
		return list;
	}

	@Override
	public List<RecommendGame> getListByPage(Map<String, String> map, PageParameter page) {
		RecommendGame recommendGame = new RecommendGame();
		recommendGame.setPage(page);
		recommendGame.setUserId(map.get("userId"));
		recommendGame.setPageName(map.get("pageName"));
		recommendGame.setRecName(map.get("recName"));
		recommendGame.setBeginDate(map.get("beginDate"));
		recommendGame.setEndDate(map.get("endDate"));
		List<RecommendGame> list = this.recommendGameDao.getListByPage(recommendGame);
		return list;
	}

	@Override
	public List<RecommendGame> getListExport(Map<String, String> map) {
		List<RecommendGame> list = this.recommendGameDao.getListExport(map);
		return list;
	}

}
