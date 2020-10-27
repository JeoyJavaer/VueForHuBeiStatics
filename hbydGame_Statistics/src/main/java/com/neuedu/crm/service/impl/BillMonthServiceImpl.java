package com.neuedu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.crm.dao.BillMonthDao;
import com.neuedu.crm.entity.BillMonth;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IBillMonthService;

@Service
public class BillMonthServiceImpl implements IBillMonthService {

	@Autowired
	private BillMonthDao billMonthDao;

	@Override
	public List<BillMonth> getCountTotalFeeByPage(Map<String, String> map, PageParameter page) {
		BillMonth billMonth = new BillMonth();
		billMonth.setPage(page);
		billMonth.setCpId(map.get("cpId"));
		billMonth.setBeginDate(map.get("beginDate"));
		billMonth.setEndDate(map.get("endDate"));
		List<BillMonth> list = this.billMonthDao.getCountTotalFeeByPage(billMonth);
		return list;
	}

	@Override
	public List<BillMonth> getCountTotalFeeExport(Map<String, String> map) {
		List<BillMonth> list = this.billMonthDao.getCountTotalFeeByPageExport(map);
		return list;
	}

}
