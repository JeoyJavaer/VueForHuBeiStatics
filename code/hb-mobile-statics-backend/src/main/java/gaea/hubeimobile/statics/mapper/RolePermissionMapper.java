package gaea.hubeimobile.statics.mapper;


import gaea.hubeimobile.statics.pojo.RolePermission;
import gaea.hubeimobile.statics.pojo.RolePermissionExample;
import org.apache.ibatis.annotations.Param;

/**
 * RolePermissionMapper继承基类
 * @author MybatisGenerator
 */
public interface RolePermissionMapper extends MyBatisBaseDao<RolePermission, Integer, RolePermissionExample> {

	/**
	 * 分配权限，批量插入
	 * @param permissionIds
	 * @param roleId
	 * @return
	 * @author huangqingwen
	 */
	public int insertRolePermission(@Param("permissionIds") Integer[] permissionIds, @Param("roleId") Integer roleId);
}