package com.neuedu.crm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.neuedu.crm.entity.GameInfo;

@Repository
public interface GameInfoDao {
	// 根据game_info统计(订购量,游戏点击量,订购发起)
	public List<GameInfo> getOrderListByPage(GameInfo gameInfo);

	// 导出 根据game_info统计 (订购量,游戏点击量,订购发起)
	public List<GameInfo> gameOrderlistExport(Map<String, String> map);

}
