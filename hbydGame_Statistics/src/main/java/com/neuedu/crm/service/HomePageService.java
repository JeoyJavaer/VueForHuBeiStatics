package com.neuedu.crm.service;

import java.util.List;
import java.util.Map;

public interface HomePageService {
	
	public List<Map<String, Object>> getCountNewUser(Map<String, String> params);
	
	public List<Map<String, Object>> getCountActiveUser(Map<String, String> params);
	
	public List<Map<String, Object>> getCountNewOrder(Map<String, String> params);
	
	public List<Map<String, Object>> getCountUserOrder(Map<String, String> params);
	
	public List<Map<String, Object>> getCountNewPrice(Map<String, String> params);

}
