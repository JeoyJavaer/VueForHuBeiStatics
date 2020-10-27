package com.neuedu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.neuedu.crm.entity.ActivityGame;
import com.neuedu.crm.pub.page.PageParameter;

@Service
public interface IActivityGameService {
	// 推荐位PvUv
	public List<ActivityGame> getListPvUvByPage(Map<String, String> map, PageParameter page);

	// 导出 推荐位PvUv
	public List<ActivityGame> getListPvUvExport(Map<String, String> map);

	// 推荐位
	public List<ActivityGame> getListByPage(Map<String, String> map, PageParameter page);

	// 导出 推荐位
	public List<ActivityGame> getListExport(Map<String, String> map);

}
