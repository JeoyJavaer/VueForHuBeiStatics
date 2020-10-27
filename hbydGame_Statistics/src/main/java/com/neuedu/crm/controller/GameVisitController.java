package com.neuedu.crm.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.neuedu.crm.common.ExportExcel;
import com.neuedu.crm.common.http.WebParameterUtils;
import com.neuedu.crm.entity.GameVisit;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IGameVisitService;
import com.neuedu.crm.vo.DataResult;
import com.neuedu.crm.vo.GameVisitListVo;
import com.neuedu.crm.vo.GameVisitVo;

@RestController
@RequestMapping("/gameVisit")
public class GameVisitController {

	@Autowired
	private IGameVisitService gameVisitService;

	@RequestMapping(value = "/getListDayCountPvUv")
	public DataResult<GameVisit> getListDayCountPvUv(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<GameVisit> list = this.gameVisitService.getListDayCountPvUvByPage(map, page);
		DataResult<GameVisit> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}

	@RequestMapping(value = "/getListDayCountPvUvExport")
	public void getListDayCountPvUvExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<GameVisit> list = this.gameVisitService.getListDayCountPvUvExport(map);
		List<GameVisitVo> musicInfoVos = new ArrayList<GameVisitVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "大厅PvUv统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (GameVisit gameVisit : list) {
			GameVisitVo gameVisitVo = new GameVisitVo();
			gameVisitVo.setCountDayTime(gameVisit.getCountDayTime() == null ? "--" : gameVisit.getCountDayTime());
			gameVisitVo.setCountPV(gameVisit.getCountPV());
			gameVisitVo.setCountUV(gameVisit.getCountUV());
			gameVisitVo.setCountOrder(gameVisit.getCountOrder());
			gameVisitVo.setCountOrderRate(gameVisit.getCountOrderRate() == null ? "--" : gameVisit.getCountOrderRate());
			musicInfoVos.add(gameVisitVo);
		}
		if (!musicInfoVos.isEmpty()) {
			ExportExcel<GameVisitVo> ex = new ExportExcel<GameVisitVo>();
			ex.exportExcelBigData("大厅PvUv统计表", GameVisitVo.getHeaders(), musicInfoVos, out, "yyyy-MM-dd");
		}
	}

	@RequestMapping(value = "/getListByPage")
	public DataResult<GameVisit> getListByPage(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<GameVisit> list = this.gameVisitService.getListByPage(map, page);
		DataResult<GameVisit> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}

	@RequestMapping(value = "/getListExport")
	public void getListExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<GameVisit> list = this.gameVisitService.getListExport(map);
		List<GameVisitListVo> musicInfoVos = new ArrayList<GameVisitListVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "大厅统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (GameVisit gameVisit : list) {
			GameVisitListVo gameVisitListVo = new GameVisitListVo();
			gameVisitListVo.setUserId(gameVisit.getUserId() == null ? "--" : gameVisit.getUserId());
			gameVisitListVo.setPageName(gameVisit.getPageName() == null ? "--" : gameVisit.getPageName());
			gameVisitListVo.setVisitTime(gameVisit.getVisitTimeFmt() == null ? "--" : gameVisit.getVisitTimeFmt());
			gameVisitListVo.setIP(gameVisit.getIP() == null ? "--" : gameVisit.getIP());
			gameVisitListVo.setCountry(gameVisit.getCountry() == null ? "--" : gameVisit.getCountry());
			gameVisitListVo.setProvince(gameVisit.getProvince() == null ? "--" : gameVisit.getProvince());
			gameVisitListVo.setCity(gameVisit.getCity() == null ? "--" : gameVisit.getCity());
			musicInfoVos.add(gameVisitListVo);
		}
		if (!musicInfoVos.isEmpty()) {
			ExportExcel<GameVisitListVo> ex = new ExportExcel<GameVisitListVo>();
			ex.exportExcelBigData("大厅统计表", GameVisitListVo.getHeaders(), musicInfoVos, out, "yyyy-MM-dd");
		}
	}
	
	//总PvUv
	@RequestMapping(value = "/getSumPvUv")
	public Map<String,String> getSumPvUv(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		Map<String,String> resultMap = new HashMap<String,String>();
		List<GameVisit> list = this.gameVisitService.getSumPvUv(map);
		resultMap.put("sumPv", list.get(0).getSumPV()+"");
		resultMap.put("sumUv", list.get(0).getSumUV()+"");
		return resultMap;
	}

}
