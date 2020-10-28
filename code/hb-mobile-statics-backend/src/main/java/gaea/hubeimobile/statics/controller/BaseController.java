package gaea.hubeimobile.statics.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class BaseController<T> {


    @ExceptionHandler()
    public  String authorizationException(){
        return "authorizationException";
    }

    public static boolean isAjaxRequest(HttpServletRequest request, HttpServletResponse response) {
        String header = request.getHeader("x-requested-with");
        return null != header && header.equalsIgnoreCase("XMLHttpRequest");
    }
}
