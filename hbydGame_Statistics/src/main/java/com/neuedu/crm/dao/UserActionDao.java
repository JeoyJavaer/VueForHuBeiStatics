package com.neuedu.crm.dao;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.neuedu.crm.entity.UserAction;

@Repository
public interface UserActionDao {
	// 用户行为统计
	public List<UserAction> getListByPage(UserAction userAction);

	// 导出 用户行为统计
	public List<UserAction> getListExport(Map<String, String> map);

	// 用户行为PVUV
	public List<UserAction> getListPvUvByPage(UserAction userAction);

	// 导出 用户行为PVUV
	public List<UserAction> getListPvUvExport(Map<String, String> map);

}
