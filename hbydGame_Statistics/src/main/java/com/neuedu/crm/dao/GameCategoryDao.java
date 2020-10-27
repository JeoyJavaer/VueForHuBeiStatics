package com.neuedu.crm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.neuedu.crm.entity.GameCategory;

@Repository
public interface GameCategoryDao {
	// 获取所有游戏分类
	public List<GameCategory> getGameCategory();

	// 分类PvUv
	public List<GameCategory> getListPvUvByPage(GameCategory gameCategory);

	// 导出 分类PvUv
	public List<GameCategory> getListPvUvExport(Map<String, String> map);

}
