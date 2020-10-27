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
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IGameCpOrderService;
import com.neuedu.crm.vo.DataResult;
import com.neuedu.crm.vo.GameCpOrderVo;

@RestController
@RequestMapping("/gameCpOrder")
public class GameCpOrderController {

	@Autowired
	private IGameCpOrderService gameCpOrderService;

	// 根据CP统计 (订购量,游戏点击量,订购发起)
	@RequestMapping(value = "/getListByPage")
	public DataResult<GameCp> getListByPage(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<GameCp> list = this.gameCpOrderService.getListByPage(map, page);
		DataResult<GameCp> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}

	// 导出 根据CP统计 (订购量,游戏点击量,订购发起)
	@RequestMapping(value = "/gamelistExport")
	public void gamelistExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<GameCp> list = this.gameCpOrderService.getListExport(map);
		List<GameCpOrderVo> GameCpOrderVos = new ArrayList<GameCpOrderVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "CP统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (GameCp gameCp : list) {
			GameCpOrderVo gameCpOrderVo = new GameCpOrderVo();
			gameCpOrderVo.setCpId(gameCp.getCpId() == null ? "--" : gameCp.getCpId());
			gameCpOrderVo.setCpName(gameCp.getCpName() == null ? "--" : gameCp.getCpName());
			gameCpOrderVo.setOrderCount(gameCp.getOrderCount());
			gameCpOrderVo.setGameCount(gameCp.getGameCount());
			gameCpOrderVo.setOrderAllCount(gameCp.getOrderAllCount());
			GameCpOrderVos.add(gameCpOrderVo);
		}
		if (!GameCpOrderVos.isEmpty()) {
			ExportExcel<GameCpOrderVo> ex = new ExportExcel<GameCpOrderVo>();
			ex.exportExcelBigData("CP统计表", GameCpOrderVo.getHeaders(), GameCpOrderVos, out, "yyyy-MM-dd");
		}
	}

}
