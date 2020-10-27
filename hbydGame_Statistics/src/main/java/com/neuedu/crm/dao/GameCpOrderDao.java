package com.neuedu.crm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.neuedu.crm.entity.GameCp;

@Repository
public interface GameCpOrderDao {
	// 根据CP统计 (订购量,游戏点击量,订购发起)
	public List<GameCp> getListByPage(GameCp gameCp);

	// 导出 根据CP统计 (订购量,游戏点击量,订购发起)
	public List<GameCp> gamelistExport(Map<String, String> map);

}
