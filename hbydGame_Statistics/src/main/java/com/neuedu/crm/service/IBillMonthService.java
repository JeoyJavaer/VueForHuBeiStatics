package com.neuedu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.neuedu.crm.entity.BillMonth;
import com.neuedu.crm.pub.page.PageParameter;

@Service
public interface IBillMonthService {
	// 每日实际消费金额总计
	public List<BillMonth> getCountTotalFeeByPage(Map<String, String> map, PageParameter page);

	// 导出 每日实际消费金额总计
	public List<BillMonth> getCountTotalFeeExport(Map<String, String> map);

}
