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
import com.neuedu.crm.entity.GameVisit;
import com.neuedu.crm.entity.RecommendGame;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IRecommendGameService;
import com.neuedu.crm.vo.DataResult;
import com.neuedu.crm.vo.GameVisitVo;
import com.neuedu.crm.vo.RecommendGamePvUvVo;
import com.neuedu.crm.vo.RecommendGameVo;

@RestController
@RequestMapping("/recommendGame")
public class RecommendGameController {

	@Autowired
	private IRecommendGameService recommendGameService;

	@RequestMapping(value = "/getListPvUvByPage")
	public DataResult<RecommendGame> getListPvUvByPage(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<RecommendGame> list = this.recommendGameService.getListPvUvByPage(map, page);
		DataResult<RecommendGame> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}

	@RequestMapping(value = "/getListPvUvExport")
	public void getListPvUvExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<RecommendGame> list = this.recommendGameService.getListPvUvExport(map);
		List<RecommendGamePvUvVo> recommendGameVos = new ArrayList<RecommendGamePvUvVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "推荐位PvUv统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (RecommendGame recommendGame : list) {
			RecommendGamePvUvVo recommendGameVo = new RecommendGamePvUvVo();
			recommendGameVo.setRecName(recommendGame.getRecName() == null ? "--" : recommendGame.getRecName());
			recommendGameVo.setCountDay(recommendGame.getCountDay() == null ? "--" : recommendGame.getCountDay());
			recommendGameVo.setCountPV(Integer.parseInt(recommendGame.getCountPV()));
			recommendGameVo.setCountUV(Integer.parseInt(recommendGame.getCountUV()));
			recommendGameVos.add(recommendGameVo);
		}
		if (!recommendGameVos.isEmpty()) {
			ExportExcel<RecommendGamePvUvVo> ex = new ExportExcel<RecommendGamePvUvVo>();
			ex.exportExcelBigData("推荐位PvUv统计表", RecommendGamePvUvVo.getHeaders(), recommendGameVos, out, "yyyy-MM-dd");
		}
	}

	@RequestMapping(value = "/getListByPage")
	public DataResult<RecommendGame> getListByPage(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<RecommendGame> list = this.recommendGameService.getListByPage(map, page);
		DataResult<RecommendGame> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}

	@RequestMapping(value = "/getListExport")
	public void getListExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<RecommendGame> list = this.recommendGameService.getListExport(map);
		List<RecommendGameVo> recommendGameVos = new ArrayList<RecommendGameVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "推荐位统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (RecommendGame recommendGame : list) {
			RecommendGameVo recommendGameVo = new RecommendGameVo();
			recommendGameVo.setRecName(recommendGame.getRecName() == null ? "--" : recommendGame.getRecName());
			recommendGameVo.setUserId(recommendGame.getUserId() == null ? "--" : recommendGame.getUserId());
			recommendGameVo.setPageName(recommendGame.getPageName() == null ? "--" : recommendGame.getPageName());
			recommendGameVo.setRecClickTimeFmt(
					recommendGame.getRecClickTimeFmt() == null ? "--" : recommendGame.getRecClickTimeFmt());
			recommendGameVos.add(recommendGameVo);
		}
		if (!recommendGameVos.isEmpty()) {
			ExportExcel<RecommendGameVo> ex = new ExportExcel<RecommendGameVo>();
			ex.exportExcelBigData("推荐位统计表", RecommendGameVo.getHeaders(), recommendGameVos, out, "yyyy-MM-dd");
		}
	}

}
