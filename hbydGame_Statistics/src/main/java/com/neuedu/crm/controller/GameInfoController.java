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
import com.neuedu.crm.entity.GameCp;
import com.neuedu.crm.entity.GameInfo;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IGameInfoService;
import com.neuedu.crm.vo.DataResult;
import com.neuedu.crm.vo.GameInfoOrderVo;

@RestController
@RequestMapping("/gameInfo")
public class GameInfoController {

	@Autowired
	private IGameInfoService gameInfoService;

	// 根据CP统计 (订购量,游戏点击量,订购发起)
	@RequestMapping(value = "/getOrderListByPage")
	public DataResult<GameInfo> getOrderListByPage(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<GameInfo> list = this.gameInfoService.getOrderListByPage(map, page);
		DataResult<GameInfo> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}

	// 导出 根据CP统计 (订购量,游戏点击量,订购发起)
	@RequestMapping(value = "/getOrderListExport")
	public void getOrderListExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<GameInfo> list = this.gameInfoService.getOrderListExport(map);
		List<GameInfoOrderVo> gameInfoOrderVos = new ArrayList<GameInfoOrderVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "游戏统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (GameInfo gameInfo : list) {
			GameInfoOrderVo gameInfoOrderVo = new GameInfoOrderVo();
			gameInfoOrderVo.setGameName(gameInfo.getGameName() == null ? "--" : gameInfo.getGameName());
			gameInfoOrderVo.setPackageName(gameInfo.getPackageName() == null ? "--" : gameInfo.getPackageName());
			gameInfoOrderVo.setCpName(gameInfo.getCpName() == null ? "--" : gameInfo.getCpName());
			gameInfoOrderVo.setOrderCount(gameInfo.getOrderCount());
			gameInfoOrderVo.setGameCount(gameInfo.getGameCount());
			gameInfoOrderVo.setOrderAllCount(gameInfo.getOrderAllCount());
			gameInfoOrderVos.add(gameInfoOrderVo);
		}
		if (!gameInfoOrderVos.isEmpty()) {
			ExportExcel<GameInfoOrderVo> ex = new ExportExcel<GameInfoOrderVo>();
			ex.exportExcelBigData("游戏统计表", GameInfoOrderVo.getHeaders(), gameInfoOrderVos, out, "yyyy-MM-dd");
		}
	}

}
