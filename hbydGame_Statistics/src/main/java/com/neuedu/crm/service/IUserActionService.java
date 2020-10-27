package com.neuedu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.neuedu.crm.entity.ActivityGame;
import com.neuedu.crm.entity.UserAction;
import com.neuedu.crm.pub.page.PageParameter;

@Service
public interface IUserActionService {
	// 用户行为统计
	public List<UserAction> getListByPage(Map<String, String> map, PageParameter page);

	// 导出 用户行为统计
	public List<UserAction> getListExport(Map<String, String> map);

	// 用户行为PvUv
	public List<UserAction> getListPvUvByPage(Map<String, String> map, PageParameter page);

	// 导出 用户行为PvUv
	public List<UserAction> getListPvUvExport(Map<String, String> map);

}
