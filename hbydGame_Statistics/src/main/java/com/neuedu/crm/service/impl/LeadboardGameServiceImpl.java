package com.neuedu.crm.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.crm.dao.LeadboardGameDao;
import com.neuedu.crm.entity.LeadboardGame;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.ILeadboardGameService;

@Service
public class LeadboardGameServiceImpl implements ILeadboardGameService {

	@Autowired
	private LeadboardGameDao leadboardGameDao;

	// 排行榜PV UV
	@Override
	public List<LeadboardGame> getListLeadboardGameDayByPage(Map<String, String> map, PageParameter page) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		LeadboardGame leadboardGame = new LeadboardGame();
		leadboardGame.setPage(page);
		leadboardGame.setPackageName(map.get("packageName"));
		leadboardGame.setGameName(map.get("gameName"));
		leadboardGame.setCpId(map.get("cpId"));
		String dayDate = map.get("dayDate");
		String yesterday = null;
		if (dayDate != null && !dayDate.equals("")) {
			leadboardGame.setDayDate(dayDate); // 当天
			// 前一天
			try {
				Date yesterdayDate = new Date();
				long yesterdayLong = sdf.parse(dayDate).getTime() - 86400 * 1000;
				yesterdayDate.setTime(yesterdayLong);
				yesterday = sdf.format(yesterdayDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else {
			Calendar calendar = Calendar.getInstance();
			Calendar yesterdayCalendar = Calendar.getInstance();// 前一天
			calendar.add(Calendar.DATE, -1);
			yesterdayCalendar.add(Calendar.DATE, -2);
			dayDate = sdf.format(calendar.getTime());
			yesterday = sdf.format(yesterdayCalendar.getTime());
			leadboardGame.setDayDate(dayDate);
		}
		// 当天的排行榜
		List<LeadboardGame> list = this.leadboardGameDao.getListLeadboardGameDayByPage(leadboardGame);
		// 前一天
		leadboardGame.setDayDate(yesterday);
		List<LeadboardGame> yesterdayList = this.getListLeadboardGameYesterday(yesterday);
		for (int i = 0; i < list.size(); i++) {
			int gameRank = (Integer.parseInt(map.get("page")) - 1) * Integer.parseInt(map.get("limit")) + i + 1;
			list.get(i).setGameRank(gameRank);
			if(yesterdayList.size()==0) {
				list.get(i).setRankChange("new");
			}else {
				for (int j = 0; j < yesterdayList.size(); j++) {
					if (list.get(i).getPackageName().equals(yesterdayList.get(j).getPackageName())) {
						int rankChange = yesterdayList.get(j).getGameRank() - list.get(i).getGameRank();
						list.get(i).setRankChange(rankChange + "");
						list.get(i).setYesterdayCountPV(yesterdayList.get(j).getCountPV());
						list.get(i).setYesterdayCountUV(yesterdayList.get(j).getCountUV());
						break;
					} else if (j == yesterdayList.size() - 1) {
						list.get(i).setRankChange("new");
//						list.get(i).setYesterdayCountPV("0");
//						list.get(i).setYesterdayCountUV("0");
					}
				}
			}
			
			
		}
		return list;
	}

	// 排行榜PV UV (用来获取前一天数据 不分页)
	@Override
	public List<LeadboardGame> getListLeadboardGameYesterday(String dayDate) {
		LeadboardGame leadboardGame = new LeadboardGame();
		leadboardGame.setDayDate(dayDate);
		List<LeadboardGame> list = this.leadboardGameDao.getListLeadboardGameYesterday(leadboardGame);
		for (int i = 0; i < list.size(); i++) {
			int gameRank = i + 1;
			list.get(i).setGameRank(gameRank);
		}
		return list;
	}

	@Override
	public List<LeadboardGame> getListLeadboardGameDayExport(Map<String, String> map) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		LeadboardGame leadboardGame = new LeadboardGame();
		leadboardGame.setPackageName(map.get("packageName"));
		leadboardGame.setGameName(map.get("gameName"));
		leadboardGame.setCpId(map.get("cpId"));
		String dayDate = map.get("dayDate");
		String yesterday = null;
		if (dayDate != null && !dayDate.equals("")) {
			leadboardGame.setDayDate(dayDate); // 当天
			// 前一天
			try {
				Date yesterdayDate = new Date();
				long yesterdayLong = sdf.parse(dayDate).getTime() - 86400 * 1000;
				yesterdayDate.setTime(yesterdayLong);
				yesterday = sdf.format(yesterdayDate);
			} catch (ParseException e) {
				e.printStackTrace();
			}

		} else {
			Calendar calendar = Calendar.getInstance();
			Calendar yesterdayCalendar = Calendar.getInstance();// 前一天
			calendar.add(Calendar.DATE, -1);
			yesterdayCalendar.add(Calendar.DATE, -2);
			dayDate = sdf.format(calendar.getTime());
			yesterday = sdf.format(yesterdayCalendar.getTime());
			leadboardGame.setDayDate(dayDate);
		}
		// 当天的排行榜
		List<LeadboardGame> list = this.leadboardGameDao.getListLeadboardGameDayExport(leadboardGame);
		// 前一天
		leadboardGame.setDayDate(yesterday);
		List<LeadboardGame> yesterdayList = this.getListLeadboardGameYesterday(yesterday);
		for (int i = 0; i < list.size(); i++) {
			int gameRank = i + 1;
			list.get(i).setGameRank(gameRank);
			if(yesterdayList.size()==0) {
				list.get(i).setRankChange("new");
			}else {
				for (int j = 0; j < yesterdayList.size(); j++) {
					if (list.get(i).getPackageName().equals(yesterdayList.get(j).getPackageName())) {
						int rankChange = yesterdayList.get(j).getGameRank() - list.get(i).getGameRank();
						list.get(i).setRankChange(rankChange + "");
						list.get(i).setYesterdayCountPV(yesterdayList.get(j).getCountPV());
						list.get(i).setYesterdayCountUV(yesterdayList.get(j).getCountUV());
						break;
					} else if (j == yesterdayList.size() - 1) {
						list.get(i).setRankChange("new");
//						list.get(i).setYesterdayCountPV("0");
//						list.get(i).setYesterdayCountUV("0");
					}
				}
			}

			
		}
		return list;
	}

	public static void main(String[] args) {
		List<LeadboardGame> list = new ArrayList<LeadboardGame>();
		String z = "vvv";
		
		list.add(new LeadboardGame());
	//	list.get(0).setPackageName("aaaa");
		list.get(0).setPackageName(z);

		List<LeadboardGame> list2 = new ArrayList<LeadboardGame>();
		list2.add(new LeadboardGame());
	//	list2.get(0).setPackageName("aaaa");
		list2.get(0).setPackageName(z);

		System.out.println(list.get(0).getPackageName().hashCode());
		System.out.println(list2.get(0).getPackageName().hashCode());
		System.out.println(list.get(0).getPackageName() == list2.get(0).getPackageName());
		System.out.println(list.get(0).getPackageName().equals(list2.get(0).getPackageName()));
	}

}
