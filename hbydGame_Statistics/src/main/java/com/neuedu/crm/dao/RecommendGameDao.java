package com.neuedu.crm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.neuedu.crm.entity.GameVisit;
import com.neuedu.crm.entity.RecommendGame;

@Repository
public interface RecommendGameDao {
	// 推荐位PvUv
	public List<RecommendGame> getListPvUvByPage(RecommendGame recommendGame);

	// 导出 推荐位PvUv
	public List<RecommendGame> getListPvUvExport(Map<String, String> map);
	
	//推荐位
	public List<RecommendGame> getListByPage(RecommendGame recommendGame);
	
	// 导出 推荐位
	public List<RecommendGame> getListExport(Map<String, String> map);

}
