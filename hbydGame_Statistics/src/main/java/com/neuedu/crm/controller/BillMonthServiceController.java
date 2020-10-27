package com.neuedu.crm.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.crm.common.ExportExcel;
import com.neuedu.crm.common.http.WebParameterUtils;
import com.neuedu.crm.entity.BillMonth;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IBillMonthService;
import com.neuedu.crm.vo.BillMonthCountTotalFeeVo;
import com.neuedu.crm.vo.DataResult;

@RestController
@RequestMapping("/billMonth")
public class BillMonthServiceController {

	@Autowired
	private IBillMonthService billMonthService;

	@RequestMapping(value = "/getCountTotalFee")
	public DataResult<BillMonth> getCountTotalFee(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<BillMonth> list = this.billMonthService.getCountTotalFeeByPage(map, page);
		DataResult<BillMonth> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}
	
	@RequestMapping(value = "/getCountTotalFeeExport")
	public void getCountTotalFeeExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<BillMonth> list = this.billMonthService.getCountTotalFeeExport(map);
		List<BillMonthCountTotalFeeVo> billMonthCountTotalFeeVos = new ArrayList<BillMonthCountTotalFeeVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "每日订购金额总计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (BillMonth billMonth : list) {
			BillMonthCountTotalFeeVo billMonthCountTotalFeeVo = new BillMonthCountTotalFeeVo();
			billMonthCountTotalFeeVo.setCpName(billMonth.getCpName() == null ? "--" : billMonth.getCpName());
			billMonthCountTotalFeeVo.setCountTotalFee(billMonth.getCountTotalFee() == null ? "--" : billMonth.getCountTotalFee());
			billMonthCountTotalFeeVo.setCountDay(billMonth.getCountDay() == null ? "--" : billMonth.getCountDay());
			billMonthCountTotalFeeVos.add(billMonthCountTotalFeeVo);
		}
		if (!billMonthCountTotalFeeVos.isEmpty()) {
			ExportExcel<BillMonthCountTotalFeeVo> ex = new ExportExcel<BillMonthCountTotalFeeVo>();
			ex.exportExcelBigData("每日订购金额总计表", BillMonthCountTotalFeeVo.getHeaders(), billMonthCountTotalFeeVos, out, "yyyy-MM-dd");
		}
	}

}
