package gaea.hubeimobile.statics.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * <br>〈错误统一页面〉<br>
 *
 * @author Administrator
 * @created 2020/8/24
 * @since 1.0.0
 */
@Controller
public class MyErrorController implements ErrorController {

    @RequestMapping(value = "/error")
    public String error() {
        return "error";
    }

    @Override
    public String getErrorPath() {
        return "error";
    }
}
