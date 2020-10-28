package gaea.hubeimobile.statics.controller;

import gaea.hubeimobile.statics.base.BaseResponse;
import gaea.hubeimobile.statics.pojo.User;
import gaea.hubeimobile.statics.service.IUserService;
import gaea.hubeimobile.statics.utils.StringUtils;
import lombok.Data;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/user")
public class UserController {
    private Logger logger=LoggerFactory.getLogger(UserController.class.getSimpleName());

    @Autowired
    private IUserService userService;

    /*
    * 获取验证码信息
    * */
    @RequestMapping(value = "/getVerifyCode")
    public BaseResponse getVerifyCode(HttpServletRequest request) {
        String code = StringUtils.getCodeString();
        logger.info("getVerifyCode:"+code);
        request.getSession().setAttribute("verifyCode",code);
        return BaseResponse.success("获取验证码成功",new Code(code));
    }


    /*
    * 登录接口
    * */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public BaseResponse login(HttpServletRequest request, @RequestParam String  account,@RequestParam String  password,@RequestParam String verifyCode){
        String sessionCode = (String)request.getSession().getAttribute("verifyCode");
        if (!org.apache.commons.lang3.StringUtils.equals(sessionCode,verifyCode)) {
            return BaseResponse.fail("验证码有误");
        }
        //2.判断用户账号密码
        User user = new User();
        user.setAccount(account);
        user.setPassword(password);

        Subject subject = SecurityUtils.getSubject();
        //判断用户是否已经登陆
        if(!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(user.getAccount(),user.getPassword());
            try {
                subject.login(token);
                //验证成功
                //登陆成功后的用户账号信息
                String userAccount =  (String)subject.getPrincipal();

                //根据用户账号，查询用户，并保存到session域中
                User loginUser = userService.findByAccount(userAccount);
                LocalDateTime now = LocalDateTime.now();
                //如果上次登陆的时间为空，则说明这次是首次登陆，前台应该跳转到修改密码界面
                if(loginUser.getLastLoginTime() == null) {
                    return  BaseResponse.fail(201,"修改账户密码");
                }
                //修改最后一次登录的时间,并保存到数据库
                loginUser.setLastLoginTime(now);
                loginUser.setPassword(null);

                //新创建对象用来更新user的最后一次登录时间
                User updateUser = new User();
                updateUser.setId(loginUser.getId());
                updateUser.setLastLoginTime(now);
                userService.edit(updateUser);

                request.getSession().setAttribute("user", loginUser);
                request.getSession().setAttribute("account", loginUser.getAccount());
                logger.info("登陆的用户信息:" + loginUser.toString());
                request.getSession().setAttribute("user", loginUser);
                return BaseResponse.success("登录成功",user);
                //账号被锁定
            } catch(LockedAccountException e){
                return   BaseResponse.fail(201,"账号已被锁定，请联系管理员进行处理！");
                //不存在的账号
            } catch(UnknownAccountException e) {
                return   BaseResponse.fail(202,"账号错误");
                //密码错误
            } catch(IncorrectCredentialsException e) {
                return   BaseResponse.fail(203,"密码错误");
            }catch (Exception e) {
              return   BaseResponse.fail(200,"系统出了小差，请稍等...");
            }
        }else {
            return   BaseResponse.fail("未知错误");
        }

    }


    @Data
    class Code implements  Serializable{
        String code;
        public Code(String code) {
            this.code = code;
        }
    }


}
