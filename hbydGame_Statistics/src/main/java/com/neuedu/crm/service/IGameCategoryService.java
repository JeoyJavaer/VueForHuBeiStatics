package com.neuedu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.neuedu.crm.entity.GameCategory;
import com.neuedu.crm.pub.page.PageParameter;

@Service
public interface IGameCategoryService {
	// 获取所有游戏分类
	public List<GameCategory> getGameCategory();

	// 推荐位PvUv
	public List<GameCategory> getListPvUvByPage(Map<String, String> map, PageParameter page);

	// 导出 推荐位PvUv
	public List<GameCategory> getListPvUvExport(Map<String, String> map);

}
