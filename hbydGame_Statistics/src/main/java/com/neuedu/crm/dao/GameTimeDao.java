package com.neuedu.crm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.neuedu.crm.entity.PlayTimeInfo;

@Repository
public interface GameTimeDao {
	// 每个游戏单日PV UV
	public List<PlayTimeInfo> getListPlayTimeDayByPage(PlayTimeInfo playTimeInfo);
	
	// 单日总PV UV
	public List<PlayTimeInfo> getListDayCountPvUvByPage(PlayTimeInfo playTimeInfo);
	
	// 导出 每个游戏单日PV UV
	public List<PlayTimeInfo> gamelistExport(Map<String, String> map);
	
	// 导出 单日总PV UV
	public List<PlayTimeInfo> getListDayCountPvUvExport(Map<String, String> map);

}
