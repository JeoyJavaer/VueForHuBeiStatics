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
import com.neuedu.crm.entity.ActivityGame;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IActivityGameService;
import com.neuedu.crm.vo.ActivityGamePvUvVo;
import com.neuedu.crm.vo.ActivityGameVo;
import com.neuedu.crm.vo.DataResult;

@RestController
@RequestMapping("/activityGame")
public class ActivityGameController {

	@Autowired
	private IActivityGameService activityGameService;

	@RequestMapping(value = "/getListDayCountPvUv")
	public DataResult<ActivityGame> getListDayCountPvUv(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<ActivityGame> list = this.activityGameService.getListPvUvByPage(map, page);
		DataResult<ActivityGame> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}

	@RequestMapping(value = "/getListDayCountPvUvExport")
	public void getListDayCountPvUvExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<ActivityGame> list = this.activityGameService.getListPvUvExport(map);
		List<ActivityGamePvUvVo> activityGameVos = new ArrayList<ActivityGamePvUvVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "轮播位PvUv统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (ActivityGame activityGame : list) {
			ActivityGamePvUvVo activityGameVo = new ActivityGamePvUvVo();
			activityGameVo.setActivityId(activityGame.getActivityId() == null ? "--" : activityGame.getActivityId());
			activityGameVo.setTypeFmt(activityGame.getTypeFmt() == null ? "--" : activityGame.getTypeFmt());
			activityGameVo.setCountDay(activityGame.getCountDay() == null ? "--" : activityGame.getCountDay());
			activityGameVo.setCountPV(activityGame.getCountPV());
			activityGameVo.setCountUV(activityGame.getCountUV());
			activityGameVos.add(activityGameVo);
		}
		if (!activityGameVos.isEmpty()) {
			ExportExcel<ActivityGamePvUvVo> ex = new ExportExcel<ActivityGamePvUvVo>();
			ex.exportExcelBigData("轮播位PvUv统计表", ActivityGamePvUvVo.getHeaders(), activityGameVos, out, "yyyy-MM-dd");
		}
	}

	@RequestMapping(value = "/getListByPage")
	public DataResult<ActivityGame> getListByPage(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<ActivityGame> list = this.activityGameService.getListByPage(map, page);
		DataResult<ActivityGame> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}

	@RequestMapping(value = "/getListExport")
	public void getListExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<ActivityGame> list = this.activityGameService.getListExport(map);
		List<ActivityGameVo> activityGameVos = new ArrayList<ActivityGameVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "轮播位统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (ActivityGame activityGame : list) {
			ActivityGameVo activityGameVo = new ActivityGameVo();
			activityGameVo.setUserId(activityGame.getUserId() == null ? "--" : activityGame.getUserId());
			activityGameVo.setTypeFmt(activityGame.getTypeFmt() == null ? "--" : activityGame.getTypeFmt());
			activityGameVo
					.setActivityName(activityGame.getActivityName() == null ? "--" : activityGame.getActivityName());
			activityGameVo.setCreateDateTimeFmt(
					activityGame.getCreateDateTimeFmt() == null ? "--" : activityGame.getCreateDateTimeFmt());
			activityGameVos.add(activityGameVo);
		}
		if (!activityGameVos.isEmpty()) {
			ExportExcel<ActivityGameVo> ex = new ExportExcel<ActivityGameVo>();
			ex.exportExcelBigData("轮播位统计表", ActivityGameVo.getHeaders(), activityGameVos, out, "yyyy-MM-dd");
		}
	}

}
