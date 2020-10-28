package gaea.hubeimobile.statics.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * <br>〈功能简述〉<br>
 *
 * @author Administrator
 * @create 2020/8/21
 * @since 1.0.0
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    public static final String DEFAULT_ERROR_MESSAGE = "系统维护，请稍后访问";


    /*
    * 201 首次登录用户，需要去修改密码
    * 1 正常
    *
    * */

    /**
     * 500  所有异常
     * @param req  请求
     * @param e 异常
     * @return  失败
     */
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse handleAllError(HttpServletRequest req, Exception e) {
        log.error("系统内部异常",e);
        return BaseResponse.fail();
    }


    /**
     * 500 的事务异常
     * @param req
     * @param e
     * @return
     */
    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public BaseResponse handleBizError(HttpServletRequest req,BaseException e){
        log.error("业务异常",e);
        return BaseResponse.fail(e.getMsg());
    }


}
