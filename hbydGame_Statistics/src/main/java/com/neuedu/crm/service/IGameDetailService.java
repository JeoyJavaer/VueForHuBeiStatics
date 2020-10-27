package com.neuedu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.neuedu.crm.entity.GameDetail;
import com.neuedu.crm.pub.page.PageParameter;

@Service
public interface IGameDetailService {
	// 详情页每日PvUv
	public List<GameDetail> getListDayCountPvUvByPage(Map<String, String> map, PageParameter page);

	// 导出 详情页每日PvUv
	public List<GameDetail> getListDayCountPvUvExport(Map<String, String> map);

}
