package com.neuedu.crm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.neuedu.crm.entity.GameDetail;

@Repository
public interface GameDetailDao {
	// 详情页每日PvUv
	public List<GameDetail> getListDayCountPvUvByPage(GameDetail gameDetail);

	// 导出 详情页每日PvUv
	public List<GameDetail> getListDayCountPvUvExport(Map<String, String> map);

}
