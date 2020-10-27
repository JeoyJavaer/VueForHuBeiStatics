package com.neuedu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.crm.dao.ActivityGameDao;
import com.neuedu.crm.entity.ActivityGame;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IActivityGameService;

@Service
public class ActivityGameServiceImpl implements IActivityGameService {

	@Autowired
	private ActivityGameDao activityGameDao;

	@Override
	public List<ActivityGame> getListPvUvByPage(Map<String, String> map, PageParameter page) {
		ActivityGame activityGame = new ActivityGame();
		activityGame.setPage(page);
		activityGame.setActivityName(map.get("activityName"));
		activityGame.setActivityId(map.get("activityId"));
//		System.out.println("+++++++++" + map.get("type") == "");
//		if (map.get("type") != null && !map.get("type").equals("")) {
//			activityGame.setType(Integer.parseInt(map.get("type")));
//		}
		activityGame.setType(map.get("type"));
		activityGame.setBeginDate(map.get("beginDate"));
		activityGame.setEndDate(map.get("endDate"));
		List<ActivityGame> list = this.activityGameDao.getListPvUvByPage(activityGame);
		return list;
	}

	@Override
	public List<ActivityGame> getListPvUvExport(Map<String, String> map) {
		List<ActivityGame> list = this.activityGameDao.getListPvUvExport(map);
		return list;
	}

	@Override
	public List<ActivityGame> getListByPage(Map<String, String> map, PageParameter page) {
		ActivityGame activityGame = new ActivityGame();
		activityGame.setPage(page);
		activityGame.setUserId(map.get("userId"));
		activityGame.setActivityName(map.get("activityName"));
		activityGame.setType(map.get("type"));
		activityGame.setBeginDate(map.get("beginDate"));
		activityGame.setEndDate(map.get("endDate"));
		List<ActivityGame> list = this.activityGameDao.getListByPage(activityGame);
		return list;
	}

	@Override
	public List<ActivityGame> getListExport(Map<String, String> map) {
		List<ActivityGame> list = this.activityGameDao.getListExport(map);
		return list;
	}

}
