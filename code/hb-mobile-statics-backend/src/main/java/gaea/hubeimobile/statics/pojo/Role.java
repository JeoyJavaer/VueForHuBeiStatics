package gaea.hubeimobile.statics.pojo;

import lombok.Data;

import java.io.Serializable;

/**
 * role
 * @author 
 */
@Data
public class Role implements Serializable {
    /**
     * 角色id
     */
    private Integer id;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

    private static final long serialVersionUID = 1L;


}