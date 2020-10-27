package com.neuedu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.neuedu.crm.entity.LeadboardGame;
import com.neuedu.crm.pub.page.PageParameter;

@Service
public interface ILeadboardGameService {
	// 排行榜PV UV
	public List<LeadboardGame> getListLeadboardGameDayByPage(Map<String, String> map, PageParameter page);

	// 导出 排行榜PV UV
	public List<LeadboardGame> getListLeadboardGameDayExport(Map<String, String> map);

	// 排行榜PV UV (用来获取前一天数据 不分页)
	public List<LeadboardGame> getListLeadboardGameYesterday(String dayDate);

}
