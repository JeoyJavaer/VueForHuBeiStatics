package com.neuedu.crm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.neuedu.crm.entity.ActivityGame;

@Repository
public interface ActivityGameDao {
	// 推荐位PvUv
	public List<ActivityGame> getListPvUvByPage(ActivityGame activityGame);

	// 导出 推荐位PvUv
	public List<ActivityGame> getListPvUvExport(Map<String, String> map);

	// 推荐位
	public List<ActivityGame> getListByPage(ActivityGame activityGame);

	// 导出 推荐位
	public List<ActivityGame> getListExport(Map<String, String> map);

}
