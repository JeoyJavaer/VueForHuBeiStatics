package gaea.hubeimobile.statics.pojo;


import gaea.hubeimobile.statics.utils.LocalDateTimeUtil;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * user
 * @author 
 */
@Data
public class User implements Serializable {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户账号
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realName;

    /**
     * MD5盐值
     */
    private String salt;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户手机号
     */
    private String phoneNumber;

    /**
     * 用户创建时间
     */
    private LocalDateTime createTime;

    /**
     * 上一次登陆时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 角色id
     */
    private Integer roleId;

    /**
     * 角色对象
     */
    private Role role;
    
    /**
     * 账号锁定状态 0 为未锁定 1为1锁定
     */
    private Integer status;

    private static final long serialVersionUID = 1L;

    public String getCreateTime() {
        return LocalDateTimeUtil.formatTime(createTime, "yyyy-MM-dd HH:mm:ss");
    }

    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    public String getLastLoginTime() {
        return LocalDateTimeUtil.formatTime(lastLoginTime, "yyyy-MM-dd HH:mm:ss");
    }


}