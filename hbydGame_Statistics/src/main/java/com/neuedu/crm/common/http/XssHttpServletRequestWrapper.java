package com.neuedu.crm.common.http;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
	
	public XssHttpServletRequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}
	 @Override
	public String[] getParameterValues(String parameter) {
		String[] values = super.getParameterValues(parameter);
 		if (values == null) return null; 
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) { 
			encodedValues[i] = cleanXSS(values[i]);
		}
		
		return encodedValues; 
	}
	 @Override
	public String getParameter(String parameter) {
		
		String value = super.getParameter(parameter);
		if (value == null) return null;
		
		return cleanXSS(value);
	}
	
	 @Override
	public String getHeader(String name) {
		String value = super.getHeader(name);
		if (value == null) return null;
		
		return cleanXSS(value);
	}
	
	private String cleanXSS(String value) {
        
		value = value.replaceAll(";", ";");
		value = value.replaceAll("\\(", "(").replaceAll("\\)", ")");
		value = value.replaceAll("'", "'");
		value = value.replaceAll("`", "`");
		value = value.replaceAll("alert", "");
		value = value.replaceAll("CR", ""); //换行
		value = value.replaceAll("LF", ""); //回车
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        //value = value.replaceAll("script", "");
		/** filter script tag*/
		String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
		Pattern p_script =Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(value);   
		value = m_script.replaceAll("");
		value = value.replaceAll("<", "").replaceAll(">", "");
		return value;
	}


}
