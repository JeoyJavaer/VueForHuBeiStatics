package com.neuedu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.crm.dao.GameCategoryDao;
import com.neuedu.crm.entity.GameCategory;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IGameCategoryService;

@Service
public class GameCategoryServiceImpl implements IGameCategoryService {

	@Autowired
	private GameCategoryDao gameCategoryDao;

	@Override
	public List<GameCategory> getListPvUvByPage(Map<String, String> map, PageParameter page) {
		GameCategory gameCategory = new GameCategory();
		gameCategory.setPage(page);
		gameCategory.setCategoryId(map.get("categoryId"));
		gameCategory.setBeginDate(map.get("beginDate"));
		gameCategory.setEndDate(map.get("endDate"));
		List<GameCategory> list = this.gameCategoryDao.getListPvUvByPage(gameCategory);
		return list;
	}

	@Override
	public List<GameCategory> getGameCategory() {
		List<GameCategory> list = this.gameCategoryDao.getGameCategory();
		return list;
	}

	@Override
	public List<GameCategory> getListPvUvExport(Map<String, String> map) {
		List<GameCategory> list = this.gameCategoryDao.getListPvUvExport(map);
		return list;
	}

}
