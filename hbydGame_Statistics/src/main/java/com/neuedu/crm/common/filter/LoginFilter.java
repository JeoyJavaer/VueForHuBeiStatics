package com.neuedu.crm.common.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.filter.OncePerRequestFilter;

public class LoginFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String contextPaht = request.getContextPath();
        // 不过滤的uri，包括登录页面
       
        
        boolean doFilter = true;
        boolean doFilter2 = true;
        String uri = request.getRequestURI();
        if("/hbydGame_Statistics/".equals(uri)){
        	uri = uri.replace(uri, "/hbydGame_Statistics/aaaaaa");
    	}
        String account = (String) request.getSession().getAttribute("account");
        if("test".equals(account)){
        	 String[] notFilter = new String[] { "login", "framework/", "locale/" , "getVerifyCode" ,"userAction","aaaaaa","logout","getRolePermissionMenu","app.jsp","countUser","countOrder","countPrice"};
        	 for (String s : notFilter) {
                 if (uri.contains(s)) {
                	 doFilter2 = false;
                     break;
                 }
             }
        }

        if (doFilter) {
            // 执行过滤
            // 从session中获取登录者实体
            String isFisrtAccess =(String) request.getSession().getAttribute("account");
            String isLogin =(String) request.getSession().getAttribute("account");
            if ("test".equals(isLogin)&&doFilter2) {
                // 如果session中不存在登录者实体，则弹出框提示重新登录
                // 设置request和response的字符集，防止乱码
                request.setCharacterEncoding("UTF-8");
                response.setCharacterEncoding("UTF-8");
                response.setContentType("text/html; charset=UTF-8");
                PrintWriter out = response.getWriter();
                String loginPage = contextPaht + "/#/";
                StringBuilder builder = new StringBuilder();
                builder.append("<script type=\"text/javascript\">");
                if (isFisrtAccess != null) {
                    builder.append("alert('权限异常，请重新登录！');");
                }
                builder.append("window.top.location.href='");
                builder.append(loginPage);
                builder.append("';");
                builder.append("</script>");
                out.print(builder.toString());
            }
            else {
                // 如果session中存在登录者实体，则继续
                filterChain.doFilter(request, response);
            }
        }
        else {
            // 如果不执行过滤，则继续
            filterChain.doFilter(request, response);
        }

    }

}
