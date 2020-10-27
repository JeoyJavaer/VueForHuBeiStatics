package com.neuedu.crm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.neuedu.crm.entity.LeadboardGame;

@Repository
public interface LeadboardGameDao {
	// 排行榜PV UV
	public List<LeadboardGame> getListLeadboardGameDayByPage(LeadboardGame leadboardGame);

	// 导出 排行榜PV UV
	public List<LeadboardGame> getListLeadboardGameDayExport(LeadboardGame leadboardGame);

	// 排行榜PV UV (用来获取前一天数据 不分页)
	public List<LeadboardGame> getListLeadboardGameYesterday(LeadboardGame leadboardGame);

}
