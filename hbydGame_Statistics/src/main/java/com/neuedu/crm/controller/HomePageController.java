package com.neuedu.crm.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.neuedu.crm.common.http.WebParameterUtils;
import com.neuedu.crm.service.HomePageService;
import com.neuedu.crm.utils.Operation;


/**
 * 首页查询数据
 * @author admin
 */
@Operation(name = "报表控制器")
@Controller
@RequestMapping("/homePage")
public class HomePageController {
	
	@Autowired
	private HomePageService service;
	
	
	/**
	 * 描述：统计新增用户数量和活跃用户数量
	 */
	@RequiresAuthentication  //登录认证   公用
	@Operation(name = "统计公司新增用户量和活跃用户量")
	@RequestMapping("/countUser")
	@ResponseBody
	public Map<String, Object> countUser(HttpServletRequest request) {
		Map<String, String> params = WebParameterUtils.getParameterMap(request);

		Map<String, Object> maps = new HashMap<String, Object>(16);

		// 新增用户数据
		List<Map<String, Object>> countNewUser = service.getCountNewUser(params);

		// 活跃用户数据
		List<Map<String, Object>> countActiveUser = service.getCountActiveUser(params);
		
		//设置转换的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long betweenDate=7;
        if(params.get("beginDate")!=null&&params.get("endDate")!=null&&!"".equals(params.get("beginDate"))&&!"".equals(params.get("endDate"))){
	        try {
		        //开始时间
		        Date startDate = sdf.parse(params.get("beginDate"));
		        //结束时间
		        Date endDate = sdf.parse(params.get("endDate"));
		        //得到相差的天数 betweenDate
		         betweenDate = (endDate.getTime() - startDate.getTime())/(60*60*24*1000);
			
	        } catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
	        }
        }
        maps.put("betweenDate", betweenDate);
		maps.put("countNewUser", countNewUser);
		maps.put("countActiveUser", countActiveUser);
		
		maps.put("code", 0);
		return maps;
	}
	
	/**
	 * 描述：订单新增量和新增总量
	 */
	@RequiresAuthentication  //登录认证   公用
	@Operation(name = "统计公司新增订单量和订单总量")
	@RequestMapping("/countOrder")
	@ResponseBody
	public Map<String, Object> countOrder(HttpServletRequest request) {
		Map<String, String> params = WebParameterUtils.getParameterMap(request);
		Map<String, Object> maps = new HashMap<String, Object>(16);
		// 新增订单数据
		List<Map<String, Object>> countNewOrder = service.getCountNewOrder(params);
		// 订购人数
		List<Map<String, Object>> countUserOrder = service.getCountUserOrder(params);
		
		
		//设置转换的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long betweenDate=7;
        if(params.get("beginDate")!=null&&params.get("endDate")!=null&&!"".equals(params.get("beginDate"))&&!"".equals(params.get("endDate"))){
	        try {
		        //开始时间
		        Date startDate = sdf.parse(params.get("beginDate"));
		        //结束时间
		        Date endDate = sdf.parse(params.get("endDate"));
		        //得到相差的天数 betweenDate
		         betweenDate = (endDate.getTime() - startDate.getTime())/(60*60*24*1000);
			
	        } catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
	        }
        }
        maps.put("betweenDate", betweenDate);
		
		maps.put("countNewOrder", countNewOrder);
		maps.put("countUserOrder", countUserOrder);
		maps.put("code", 0);
		return maps;
	}
	/**
	 * 描述：金额新增量
	 */
	@RequiresAuthentication  //登录认证   公用
	@Operation(name = "统计公司新增金额量")
	@RequestMapping("/countPrice")
	@ResponseBody
	public Map<String, Object> countPrice(HttpServletRequest request) {
		Map<String, String> params = WebParameterUtils.getParameterMap(request);
		Map<String, Object> maps = new HashMap<String, Object>(16);
		// 新增金额数据
		List<Map<String, Object>> countNewPrice = service.getCountNewPrice(params);
		//设置转换的日期格式
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long betweenDate=7;
        if(params.get("beginDate")!=null&&params.get("endDate")!=null&&!"".equals(params.get("beginDate"))&&!"".equals(params.get("endDate"))){
	        try {
		        //开始时间
		        Date startDate = sdf.parse(params.get("beginDate"));
		        //结束时间
		        Date endDate = sdf.parse(params.get("endDate"));
		        //得到相差的天数 betweenDate
		         betweenDate = (endDate.getTime() - startDate.getTime())/(60*60*24*1000);
			
	        } catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
	        }
        }
        maps.put("betweenDate", betweenDate);
		
		maps.put("countNewPrice", countNewPrice);
		maps.put("code", 0);
		return maps;
	}


}
