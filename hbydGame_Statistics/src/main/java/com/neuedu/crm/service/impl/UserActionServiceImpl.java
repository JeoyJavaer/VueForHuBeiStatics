package com.neuedu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.neuedu.crm.dao.UserActionDao;
import com.neuedu.crm.entity.ActivityGame;
import com.neuedu.crm.entity.UserAction;
import com.neuedu.crm.pub.page.PageParameter;
import com.neuedu.crm.service.IUserActionService;

@Service
public class UserActionServiceImpl implements IUserActionService {
	
	@Autowired
	private UserActionDao userActionDao;

	@Override
	public List<UserAction> getListByPage(Map<String, String> map, PageParameter page) {
		UserAction userAction = new UserAction();
		userAction.setPage(page);
		userAction.setUserId(map.get("userId"));
		userAction.setObject(map.get("object"));
		userAction.setAction(map.get("action"));
		userAction.setBeginDate(map.get("beginDate"));
		userAction.setEndDate(map.get("endDate"));
		List<UserAction> list = this.userActionDao.getListByPage(userAction);
		return list;
	}

	@Override
	public List<UserAction> getListExport(Map<String, String> map) {
		List<UserAction> list = this.userActionDao.getListExport(map);
		return list;
	}

	@Override
	public List<UserAction> getListPvUvByPage(Map<String, String> map, PageParameter page) {
		UserAction userAction = new UserAction();
		userAction.setPage(page);
		userAction.setBeginDate(map.get("beginDate"));
		userAction.setEndDate(map.get("endDate"));
		List<UserAction> list = this.userActionDao.getListPvUvByPage(userAction);
		return list;
	}

	@Override
	public List<UserAction> getListPvUvExport(Map<String, String> map) {
		List<UserAction> list = this.userActionDao.getListPvUvExport(map);
		return list;
	}

}
