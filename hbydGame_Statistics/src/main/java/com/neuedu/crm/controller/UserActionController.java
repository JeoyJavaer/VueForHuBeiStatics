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
import com.neuedu.crm.entity.UserAction;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IUserActionService;
import com.neuedu.crm.vo.DataResult;
import com.neuedu.crm.vo.GameVisitVo;
import com.neuedu.crm.vo.UserActionPvUvVo;
import com.neuedu.crm.vo.UserActionVo;

@RestController
@RequestMapping("/userAction")
public class UserActionController {

	@Autowired
	private IUserActionService userActionService;

	@RequestMapping(value = "/getListByPage")
	public DataResult<UserAction> getListByPage(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<UserAction> list = this.userActionService.getListByPage(map, page);
		DataResult<UserAction> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}

	@RequestMapping(value = "/getListExport")
	public void getListExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<UserAction> list = this.userActionService.getListExport(map);
		List<UserActionVo> musicInfoVos = new ArrayList<UserActionVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "用户行为统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (UserAction userAction : list) {
			UserActionVo gameVisitVo = new UserActionVo();
			gameVisitVo.setUserId(userAction.getUserId() == null ? "--" : userAction.getUserId());
			gameVisitVo.setObject(userAction.getObject() == null ? "--" : userAction.getObject());
			gameVisitVo.setIp(userAction.getIp() == null ? "--" : userAction.getIp());
			gameVisitVo.setAction(userAction.getAction() == null ? "--" : userAction.getAction());
			gameVisitVo.setCreateDateTimeFmt(
					userAction.getCreateDateTimeFmt() == null ? "--" : userAction.getCreateDateTimeFmt());
			musicInfoVos.add(gameVisitVo);
		}
		if (!musicInfoVos.isEmpty()) {
			ExportExcel<UserActionVo> ex = new ExportExcel<UserActionVo>();
			ex.exportExcelBigData("用户行为统计表", UserActionVo.getHeaders(), musicInfoVos, out, "yyyy-MM-dd");
		}
	}

	@RequestMapping(value = "/getListPvUvByPage")
	public DataResult<UserAction> getListPvUvByPage(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<UserAction> list = this.userActionService.getListPvUvByPage(map, page);
		DataResult<UserAction> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}

	@RequestMapping(value = "/getListPvUvExport")
	public void getListPvUvExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<UserAction> list = this.userActionService.getListPvUvExport(map);
		List<UserActionPvUvVo> musicInfoVos = new ArrayList<UserActionPvUvVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "用户行为PvUv统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (UserAction userAction : list) {
			UserActionPvUvVo userActionPvUvVo = new UserActionPvUvVo();
			userActionPvUvVo.setCountDay(userAction.getCountDay() == null ? "--" : userAction.getCountDay());
			userActionPvUvVo.setCountPV(userAction.getCountPV());
			userActionPvUvVo.setCountUV(userAction.getCountUV());
			musicInfoVos.add(userActionPvUvVo);
		}
		if (!musicInfoVos.isEmpty()) {
			ExportExcel<UserActionPvUvVo> ex = new ExportExcel<UserActionPvUvVo>();
			ex.exportExcelBigData("用户行为PvUv统计表", UserActionPvUvVo.getHeaders(), musicInfoVos, out, "yyyy-MM-dd");
		}
	}

}
