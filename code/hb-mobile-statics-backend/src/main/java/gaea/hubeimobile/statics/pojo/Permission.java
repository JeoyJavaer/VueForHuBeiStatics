package gaea.hubeimobile.statics.pojo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * permission
 * @author 
 */
@Data
public class Permission implements Serializable {
    /**
     * 权限id
     */
    private Integer id;

    /**
     * 上级id
     */
    private Integer pid;
    
    /**
     * 父级权限
     */
    private Permission parentPermission;
    
    /**
     * 子级权限
     */
    private List<Permission> childPermission;
    
    

	/**
     * 概要类型：0为菜单 1为功能
     */
    private Integer type;

    /**
     * 权限标题
     */
    private String title;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 权限对应可使用的url
     */
    private String url;

    /**
     * 权限编码
     */
    private String code;

    /**
     * 权限状态：0 为正常 1为禁用
     */
    private Integer status;

    private static final long serialVersionUID = 1L;


    
}