package com.neuedu.crm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.neuedu.crm.entity.GameVisit;

@Repository
public interface GameVisitDao {
	// 每日PvUv
	public List<GameVisit> getListDayCountPvUvByPage(GameVisit gameVisit);

	// 导出 每日PvUv
	public List<GameVisit> getListDayCountPvUvExport(Map<String, String> map);

	// 所有记录
	public List<GameVisit> getListByPage(GameVisit gameVisit);

	// 导出 所有记录
	public List<GameVisit> getListExport(Map<String, String> map);

	// 总PvUv
	public List<GameVisit> getSumPvUv(GameVisit gameVisit);

}
