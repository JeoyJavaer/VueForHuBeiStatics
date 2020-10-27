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
import com.neuedu.crm.entity.GameCategory;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IGameCategoryService;
import com.neuedu.crm.vo.DataResult;
import com.neuedu.crm.vo.GameCategoryPvUvVo;

@RestController
@RequestMapping("/gameCategory")
public class GameCategoryController {

	@Autowired
	private IGameCategoryService gameCategoryService;

	@RequestMapping(value = "/getGameCategory")
	public List<GameCategory> getGameCategory(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		List<GameCategory> list = this.gameCategoryService.getGameCategory();
		return list;
	}

	@RequestMapping(value = "/getListDayCountPvUv")
	public DataResult<GameCategory> getListDayCountPvUv(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<GameCategory> list = this.gameCategoryService.getListPvUvByPage(map, page);
		DataResult<GameCategory> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}
	
	@RequestMapping(value = "/getListDayCountPvUvExport")
	public void getListDayCountPvUvExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<GameCategory> list = this.gameCategoryService.getListPvUvExport(map);
		List<GameCategoryPvUvVo> gameCategoryPvUvVos = new ArrayList<GameCategoryPvUvVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "游戏分类PvUv统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (GameCategory gameCategory : list) {
			GameCategoryPvUvVo gameCategoryPvUvVo = new GameCategoryPvUvVo();
			gameCategoryPvUvVo.setCategoryName(gameCategory.getCategoryName()==null?"--":gameCategory.getCategoryName());
			gameCategoryPvUvVo.setCountDay(gameCategory.getCountDay()==null?"--":gameCategory.getCountDay());
			gameCategoryPvUvVo.setCountPV(gameCategory.getCountPV());
			gameCategoryPvUvVo.setCountUV(gameCategory.getCountUV());
			gameCategoryPvUvVos.add(gameCategoryPvUvVo);
		}
		if (!gameCategoryPvUvVos.isEmpty()) {
			ExportExcel<GameCategoryPvUvVo> ex = new ExportExcel<GameCategoryPvUvVo>();
			ex.exportExcelBigData("游戏分类PvUv统计表", GameCategoryPvUvVo.getHeaders(), gameCategoryPvUvVos, out, "yyyy-MM-dd");
		}
	}

}
