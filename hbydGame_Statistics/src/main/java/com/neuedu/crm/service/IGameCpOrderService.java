package com.neuedu.crm.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.neuedu.crm.entity.GameCp;
import com.neuedu.crm.entity.GameVisit;
import com.neuedu.crm.pub.page.PageParameter;

@Service
public interface IGameCpOrderService {
	// 根据CP统计 (订购量,游戏点击量,订购发起)
	public List<GameCp> getListByPage(Map<String, String> map, PageParameter page);

	// 导出 根据CP统计 (订购量,游戏点击量,订购发起)
	public List<GameCp> getListExport(Map<String, String> map);

}
