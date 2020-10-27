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
import com.neuedu.crm.entity.PlayTimeInfo;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IPlayTimeService;
import com.neuedu.crm.vo.DataResult;
import com.neuedu.crm.vo.PlayTimeInfoPvUvVo;
import com.neuedu.crm.vo.PlayTimeInfoVo;

@RestController
@RequestMapping("/count")
public class RecommendedController {

	@Autowired
	private IPlayTimeService playtimeService;

	@RequestMapping(value = "/playtimeDay")
	public DataResult<PlayTimeInfo> playtimeDay(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<PlayTimeInfo> list = this.playtimeService.getListPlayTimeDayByPage(map, page);
//		DataResult<PlayTimeInfo> rd = new DataResult<PlayTimeInfo>(list);
//		rd.setCount(page.getTotalCount());
//		rd.setCode(0);
//		rd.setMsg("查询成功");
		DataResult<PlayTimeInfo> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}

	@RequestMapping(value = "/getListDayCountPvUv")
	public DataResult<PlayTimeInfo> getListDayCountPvUv(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));

		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<PlayTimeInfo> list = this.playtimeService.getListDayCountPvUvByPage(map, page);
		DataResult<PlayTimeInfo> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}

	@RequestMapping(value = "/gamelistExport")
	public void gamelistExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);
		List<PlayTimeInfo> list = this.playtimeService.gamelistExport(map);
		List<PlayTimeInfoVo> musicInfoVos = new ArrayList<PlayTimeInfoVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "游戏单日PvUv统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (PlayTimeInfo musicInfo : list) {
			PlayTimeInfoVo musicInfoVo = new PlayTimeInfoVo();
			musicInfoVo.setGameName(musicInfo.getGameName() == null ? "--" : musicInfo.getGameName());
			musicInfoVo.setPackageName(musicInfo.getPackageName() == null ? "--" : musicInfo.getPackageName());
			musicInfoVo.setCpName(musicInfo.getCpName() == null ? "--" : musicInfo.getCpName());
			musicInfoVo.setCountPV(musicInfo.getCountPV() == null ? "--" : musicInfo.getCountPV());
			musicInfoVo.setCountUV(musicInfo.getCountUV() == null ? "--" : musicInfo.getCountUV());
			musicInfoVo.setCountDayTime(musicInfo.getCountDayTime() == null ? "--" : musicInfo.getCountDayTime());
			musicInfoVos.add(musicInfoVo);
		}
		if (!musicInfoVos.isEmpty()) {
			ExportExcel<PlayTimeInfoVo> ex = new ExportExcel<PlayTimeInfoVo>();
			ex.exportExcelBigData("游戏单日PvUv统计表", PlayTimeInfoVo.getHeaders(), musicInfoVos, out, "yyyy-MM-dd");
		}
	}

	@RequestMapping(value = "/getListDayCountPvUvExport")
	public void getListDayCountPvUvExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);
		List<PlayTimeInfo> list = this.playtimeService.getListDayCountPvUvExport(map);
		List<PlayTimeInfoPvUvVo> musicInfoVos = new ArrayList<PlayTimeInfoPvUvVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "单日PvUv统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (PlayTimeInfo musicInfo : list) {
			PlayTimeInfoPvUvVo musicInfoVo = new PlayTimeInfoPvUvVo();
			musicInfoVo.setCountDayTime(musicInfo.getCountDayTime() == null ? "--" : musicInfo.getCountDayTime());
			musicInfoVo.setCountPV(musicInfo.getCountPV() == null ? "--" : musicInfo.getCountPV());
			musicInfoVo.setCountUV(musicInfo.getCountUV() == null ? "--" : musicInfo.getCountUV());
			musicInfoVos.add(musicInfoVo);
		}
		if (!musicInfoVos.isEmpty()) {
			ExportExcel<PlayTimeInfoPvUvVo> ex = new ExportExcel<PlayTimeInfoPvUvVo>();
			ex.exportExcelBigData("单日PvUv统计表", PlayTimeInfoPvUvVo.getHeaders(), musicInfoVos, out, "yyyy-MM-dd");
		}
	}

}
