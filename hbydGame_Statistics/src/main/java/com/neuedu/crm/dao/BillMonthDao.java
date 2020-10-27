package com.neuedu.crm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.neuedu.crm.entity.BillMonth;

@Repository
public interface BillMonthDao {
	// 每日实际消费金额总计
	public List<BillMonth> getCountTotalFeeByPage(BillMonth billMonth);

	// 导出 每日实际消费金额总计
	public List<BillMonth> getCountTotalFeeByPageExport(Map<String, String> map);

}
