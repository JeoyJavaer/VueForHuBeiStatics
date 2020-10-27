package com.neuedu.crm.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.neuedu.crm.mapper.HomePageMapper;
import com.neuedu.crm.service.HomePageService;

@Service
@Transactional(rollbackFor=Exception.class)
public class HomePageServiceImpl implements HomePageService{
	
	@Autowired
	private HomePageMapper mapper;

	@Override
	public List<Map<String, Object>> getCountNewUser(Map<String, String> params) {
		// TODO Auto-generated method stub
		if(params.get("beginDate")!=null&&params.get("endDate")!=null&&!"".equals(params.get("beginDate"))&&!"".equals(params.get("endDate"))){
			params.put("day", "");
		}else {
			params.put("day", "7");
		}
		return mapper.selectCountNewUser(params);
	}

	@Override
	public List<Map<String, Object>> getCountActiveUser(Map<String, String> params) {
		// TODO Auto-generated method stub
		if(params.get("beginDate")!=null&&params.get("endDate")!=null&&!"".equals(params.get("beginDate"))&&!"".equals(params.get("endDate"))){
			params.put("day", "");
		}else {
			params.put("day", "7");
		}
		return mapper.selectCountActiveUser(params);
	}
	
	@Override
	public List<Map<String, Object>> getCountNewOrder(Map<String, String> params) {
		// TODO Auto-generated method stub
		if(params.get("beginDate")!=null&&params.get("endDate")!=null&&!"".equals(params.get("beginDate"))&&!"".equals(params.get("endDate"))){
			params.put("day", "");
		}else {
			params.put("day", "7");
		}
		return mapper.selectCountNewOrder(params);
	}
	
	@Override
	public List<Map<String, Object>> getCountUserOrder(Map<String, String> params) {
		// TODO Auto-generated method stub
		if(params.get("beginDate")!=null&&params.get("endDate")!=null&&!"".equals(params.get("beginDate"))&&!"".equals(params.get("endDate"))){
			params.put("day", "");
		}else {
			params.put("day", "7");
		}
		return mapper.selectCountUserOrder(params);
	}
	
	
	@Override
	public List<Map<String, Object>> getCountNewPrice(Map<String, String> params) {
		// TODO Auto-generated method stub
		if(params.get("beginDate")!=null&&params.get("endDate")!=null&&!"".equals(params.get("beginDate"))&&!"".equals(params.get("endDate"))){
			params.put("day", "");
		}else {
			params.put("day", "7");
		}
		return mapper.selectCountNewPrice(params);
	}

}
