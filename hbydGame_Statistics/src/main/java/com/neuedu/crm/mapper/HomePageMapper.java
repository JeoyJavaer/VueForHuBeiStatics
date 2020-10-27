package com.neuedu.crm.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HomePageMapper {
	
	
	public List<Map<String, Object>> selectCountNewUser(Map<String, String> params);
	
	
	public List<Map<String, Object>> selectCountActiveUser(Map<String, String> params);
	
	
	public List<Map<String, Object>> selectCountNewOrder(Map<String, String> params);
	
	
	public List<Map<String, Object>> selectCountUserOrder(Map<String, String> params);
	
	
	public List<Map<String, Object>> selectCountNewPrice(Map<String, String> params);

}
