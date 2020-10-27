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
import com.neuedu.crm.entity.GameDetail;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IGameDetailService;
import com.neuedu.crm.vo.DataResult;
import com.neuedu.crm.vo.GameDetailVo;

@RestController
@RequestMapping("/gameDetail")
public class GameDetailController {

	@Autowired
	private IGameDetailService gameDetailService;

	@RequestMapping(value = "/getListDayCountPvUv")
	public DataResult<GameDetail> getListDayCountPvUv(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<GameDetail> list = this.gameDetailService.getListDayCountPvUvByPage(map, page);
		DataResult<GameDetail> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}
	
	@RequestMapping(value = "/getListDayCountPvUvExport")
	public void getListDayCountPvUvExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<GameDetail> list = this.gameDetailService.getListDayCountPvUvExport(map);
		List<GameDetailVo> gameDetailVos = new ArrayList<GameDetailVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "详情页PvUv统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (GameDetail gameDetail : list) {
			GameDetailVo GameDetailVo = new GameDetailVo();
			GameDetailVo.setCountDayTime(gameDetail.getCountDayTime() == null ? "--" : gameDetail.getCountDayTime());
			GameDetailVo.setCountPV(gameDetail.getCountPV());
			GameDetailVo.setCountUV(gameDetail.getCountUV());
			gameDetailVos.add(GameDetailVo);
		}
		if (!gameDetailVos.isEmpty()) {
			ExportExcel<GameDetailVo> ex = new ExportExcel<GameDetailVo>();
			ex.exportExcelBigData("详情页PvUv统计表", GameDetailVo.getHeaders(), gameDetailVos, out, "yyyy-MM-dd");
		}
	}

}
