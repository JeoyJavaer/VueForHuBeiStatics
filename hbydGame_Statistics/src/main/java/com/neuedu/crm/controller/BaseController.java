package com.neuedu.crm.controller;

import javax.servlet.http.HttpServletRequest;

import com.neuedu.crm.pub.page.PageParameter;


/**
 * 分页
 * 
 * @since 2014年11月3日 下午4:10:56
 * @version 1.0
 * @author Gzj
 */
public class BaseController {

	private final int PAGE = 1;

	private final int ROWS = 20;

	public PageParameter getPageParameter(HttpServletRequest request) {
		int currentPage = this.PAGE;
		try {
			currentPage = Integer.parseInt(request.getParameter("page"));
		} catch (Exception e) {
		}
		int rows = this.ROWS;

		try {
			rows = Integer.parseInt(request.getParameter("rows"));
		} catch (Exception e) {
		}

		return new PageParameter(currentPage, rows);
	}

}
