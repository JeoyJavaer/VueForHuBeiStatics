package com.neuedu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.neuedu.crm.entity.GameVisit;
import com.neuedu.crm.entity.RecommendGame;
import com.neuedu.crm.pub.page.PageParameter;

@Service
public interface IRecommendGameService {
	// 推荐位PvUv
	public List<RecommendGame> getListPvUvByPage(Map<String, String> map, PageParameter page);

	// 导出 推荐位PvUv
	public List<RecommendGame> getListPvUvExport(Map<String, String> map);

	// 推荐位
	public List<RecommendGame> getListByPage(Map<String, String> map, PageParameter page);

	// 导出 推荐位
	public List<RecommendGame> getListExport(Map<String, String> map);

}
