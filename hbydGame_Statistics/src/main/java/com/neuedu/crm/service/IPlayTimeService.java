package com.neuedu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.neuedu.crm.entity.PlayTimeInfo;
import com.neuedu.crm.pub.page.PageParameter;

@Service
public interface IPlayTimeService {
	public List<PlayTimeInfo> getListPlayTimeDayByPage(Map<String, String> map, PageParameter page);

	public List<PlayTimeInfo> getListDayCountPvUvByPage(Map<String, String> map, PageParameter page);

	// 导出 每个游戏单日PV UV
	public List<PlayTimeInfo> gamelistExport(Map<String, String> map);

	// 导出 单日总PV UV
	public List<PlayTimeInfo> getListDayCountPvUvExport(Map<String, String> map);

}
