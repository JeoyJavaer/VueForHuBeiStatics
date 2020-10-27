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
import com.neuedu.crm.entity.LeadboardGame;
import com.neuedu.crm.entity.PlayTimeInfo;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.ILeadboardGameService;
import com.neuedu.crm.vo.DataResult;
import com.neuedu.crm.vo.GameVisitVo;
import com.neuedu.crm.vo.LeadboardGameVo;

@RestController
@RequestMapping("/leadboardGame")
public class LeadboardGameController {

	@Autowired
	private ILeadboardGameService leadboardGameService;

	@RequestMapping(value = "/leadboardGameDay")
	public DataResult<LeadboardGame> leadboardGameDay(HttpServletRequest request) {
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		int currentPage = Integer.parseInt(map.get("page"));
		int pageSize = 20;
		try {
			pageSize = Integer.parseInt(map.get("limit"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		PageParameter page = new PageParameter(currentPage, pageSize);
		List<LeadboardGame> list = this.leadboardGameService.getListLeadboardGameDayByPage(map, page);
//		DataResult<PlayTimeInfo> rd = new DataResult<PlayTimeInfo>(list);
//		rd.setCount(page.getTotalCount());
//		rd.setCode(0);
//		rd.setMsg("查询成功");
		DataResult<LeadboardGame> rd = new DataResult<>(list, 0, "查询成功", page.getTotalCount());
		return rd;
	}

	@RequestMapping(value = "/leadboardGameDayExport")
	public void leadboardGameDayExport(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// 转码防止乱码
		String loginUser = (String) request.getSession().getAttribute("loginUser");
		Map<String, String> map = WebParameterUtils.getParameterMap(request);// 查询参数
		map.put("loginUser", loginUser);

		List<LeadboardGame> list = this.leadboardGameService.getListLeadboardGameDayExport(map);
		List<LeadboardGameVo> leadboardGameVos = new ArrayList<LeadboardGameVo>();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		String fileName = "每日排行榜统计表_" + df.format(new Date()) + ".xls";
		response.setHeader("Content-disposition",
				"attachment;filename=" + new String(fileName.getBytes("utf8"), "iso8859-1"));
		response.setContentType("application/msexcel;charset=UTF-8");
		OutputStream out = response.getOutputStream();
		for (LeadboardGame leadboardGame : list) {
			LeadboardGameVo leadboardGameVo = new LeadboardGameVo();
			leadboardGameVo.setGameRank(leadboardGame.getGameRank());
			leadboardGameVo.setRankChange(leadboardGame.getRankChange() == null ? "--" : leadboardGame.getRankChange());
			leadboardGameVo.setGameName(leadboardGame.getGameName() == null ? "--" : leadboardGame.getGameName());
			leadboardGameVo
					.setPackageName(leadboardGame.getPackageName() == null ? "--" : leadboardGame.getPackageName());
			leadboardGameVo.setCpName(leadboardGame.getCpName() == null ? "--" : leadboardGame.getCpName());
			leadboardGameVo.setCountPV(leadboardGame.getCountPV());
			leadboardGameVo.setCountUV(leadboardGame.getCountUV());
			leadboardGameVo.setYesterdayCountPV(leadboardGame.getYesterdayCountPV());
			leadboardGameVo.setYesterdayCountUV(leadboardGame.getYesterdayCountUV());
			leadboardGameVo
					.setCountDayTime(leadboardGame.getCountDayTime() == null ? "--" : leadboardGame.getCountDayTime());

			leadboardGameVos.add(leadboardGameVo);
		}
		if (!leadboardGameVos.isEmpty()) {
			ExportExcel<LeadboardGameVo> ex = new ExportExcel<LeadboardGameVo>();
			ex.exportExcelBigData("每日排行榜统计表", LeadboardGameVo.getHeaders(), leadboardGameVos, out, "yyyy-MM-dd");
		}
	}

}
