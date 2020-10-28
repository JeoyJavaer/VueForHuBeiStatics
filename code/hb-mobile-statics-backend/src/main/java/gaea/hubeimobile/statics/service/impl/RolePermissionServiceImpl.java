package gaea.hubeimobile.statics.service.impl;


import gaea.hubeimobile.statics.mapper.RolePermissionMapper;
import gaea.hubeimobile.statics.pojo.RolePermission;
import gaea.hubeimobile.statics.pojo.RolePermissionExample;
import gaea.hubeimobile.statics.service.IRolePermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 
 * @author malizhi
 *
 */
@Service
@Transactional
public class RolePermissionServiceImpl implements IRolePermissionService {
	
	@Autowired
	private RolePermissionMapper rolePermissionMapper;
	
	/**
	 * 根据RolePermissionExample统计条数
	 * @author malizhi
	 * @param rolePermissionExample
	 * @return long
	 * @version 1.0
	 * @exception
	 */
	@Override
    public long countByRolePermissionExample(RolePermissionExample rolePermissionExample){
    	return rolePermissionMapper.countByExample(rolePermissionExample);
    }

    /**
     * 根据RolePermissionExample删除rolePermission
     * @author malizhi
     * @param rolePermissionExample
     * @return boolean
     * @version 1.0
     * @exception
     */
	@Override
    public boolean deleteByRolePermissionExample(RolePermissionExample rolePermissionExample){
    	int ret = rolePermissionMapper.deleteByExample(rolePermissionExample);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 根据主键id删除rolePermission
     * @author malizhi
     * @param id
     * @return boolean
     * @version 1.0
     * @exception
     */
	@Override
    public boolean deleteRolePermissionByPrimaryKey(Integer id){
    	int ret = rolePermissionMapper.deleteByPrimaryKey(id);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 插入一条数据，如果字段为空，插入的字段也为空
     * @author malizhi
     * @param rolePermission
     * @return boolean
     * @version 1.0
     * @exception
     */
	@Override
    public boolean insertRolePermission(RolePermission rolePermission){
    	int ret = rolePermissionMapper.insert(rolePermission);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 插入一条数据，如果参数rolePermission中字段为空，代表字段为数据库默认值
     * @author malizhi
     * @param rolePermission
     * @return boolean
     * @version 1.0
     * @exception
     */
	@Override
    public boolean insertRolePermissionSelective(RolePermission rolePermission){
    	int ret = rolePermissionMapper.insertSelective(rolePermission);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 根据RolePermissionExample查找
     * @author malizhi
     * @param rolePermissionExample
     * @return List<RolePermission>
     * @version 1.0
     * @exception
     */
	@Override
    public List<RolePermission> selectByRolePermissionExample(RolePermissionExample rolePermissionExample){
    	return rolePermissionMapper.selectByExample(rolePermissionExample);
    }

    /**
     * 根据主键id查找RolePermission
     * @author malizhi
     * @param id
     * @return RolePermission
     * @version 1.0
     * @exception
     */
	@Override
    public RolePermission selectRolePermissionByPrimaryKey(Integer id){
    	return rolePermissionMapper.selectByPrimaryKey(id);
    }

    /**
     * 更新RolePermission，如果传入的参数rolePermission中字段为空，代表不更新此字段，RolePermissionExample为where条件
     * @author malizhi
     * @param rolePermission
     * @param rolePermissionExample
     * @return boolean
     * @version 1.0
     * @exception
     */
	@Override
    public boolean updateByRolePermissionExampleSelective(RolePermission rolePermission, RolePermissionExample rolePermissionExample){
    	int ret = rolePermissionMapper.updateByExampleSelective(rolePermission, rolePermissionExample);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 更新RolePermission，如果传入的参数rolePermission中字段为空，代表此字段也更新为空，RolePermissionExample为where条件
     * @author malizhi
     * @param rolePermission
     * @param rolePermissionExample
     * @return boolean
     * @version 1.0
     * @exception
     */
	@Override
    public boolean updateByRolePermissionExample(RolePermission rolePermission, RolePermissionExample rolePermissionExample){
    	int ret = rolePermissionMapper.updateByExample(rolePermission, rolePermissionExample);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 更新RolePermission，如果传入的参数rolePermission中字段为空，代表不更新此字段
     * @author malizhi
     * @param rolePermission
     * @return boolean
     * @version 1.0
     * @exception
     */
	@Override
    public boolean updateRolePermissionByPrimaryKeySelective(RolePermission rolePermission){
    	int ret = rolePermissionMapper.updateByPrimaryKeySelective(rolePermission);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

    /**
     * 更新RolePermission,如果传入的参数rolePermission中字段为空，那么相应字段也更新为空
     * @author malizhi
     * @param rolePermission
     * @return boolean
     * @version 1.0
     * @exception
     */
	@Override
    public boolean updateRolePermissionByPrimaryKey(RolePermission rolePermission){
    	int ret = rolePermissionMapper.updateByPrimaryKey(rolePermission);
    	if(ret != 0){
    		return true;
    	}else{
    		return false;
    	}
    }

	/**
     * 角色权限分配
     * @author huangqingwen
     * @param
     * @return int  插入的记录行数
     * @version 1.0
     * @exception
     */
	@Override
	public int allotPermission(Integer[] permissionIds, Integer roleId) {
		int count = 0;
		try {
			//1. 先将角色原来的权限全部删除
			RolePermissionExample example = new RolePermissionExample();
			example.createCriteria().andRoleIdEqualTo(roleId);
			rolePermissionMapper.deleteByExample(example);
			
			//2. 重新插入该角色的权限
			if(permissionIds.length > 0) {
				count = rolePermissionMapper.insertRolePermission(permissionIds, roleId);
			}
			
		}catch (Exception e) {
			return 0;
		}
		
		return count;
	}
	
}
