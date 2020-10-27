package com.neuedu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.neuedu.crm.entity.GameVisit;
import com.neuedu.crm.entity.PlayTimeInfo;
import com.neuedu.crm.pub.page.PageParameter;

@Service
public interface IGameVisitService {
	// 每日PvUv
	public List<GameVisit> getListDayCountPvUvByPage(Map<String, String> map, PageParameter page);

	// 导出 每日PvUv
	public List<GameVisit> getListDayCountPvUvExport(Map<String, String> map);

	// 所有记录
	public List<GameVisit> getListByPage(Map<String, String> map, PageParameter page);

	// 导出 所有记录
	public List<GameVisit> getListExport(Map<String, String> map);

	// 总PvUv
	public List<GameVisit> getSumPvUv(Map<String, String> map);

}
