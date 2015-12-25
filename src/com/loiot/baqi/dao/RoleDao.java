package com.loiot.baqi.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.stereotype.Repository;

import com.timeloit.pojo.Role;

/**
 * 账号数据访问层。
 * 
 * @author zhengrj
 */
@Repository("roleDao")
public class RoleDao extends SqlSessionDaoSupport {

    /**
     * 查询角色列表条数
     * 
     * @param roleName 角色名
     * @return 角色列表
     */
    public int getRoleListCount(String roleName) {

        Map<String, Object> params = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(roleName)) {
            params.put("roleName", roleName);
        }

        return (Integer) getSqlSession().selectOne("Role.getRoleListCount", params);
    }

    /**
     * 查询角色列表
     * 
     * @param roleName 角色名
     * @param skipResults 跳过的记录数
     * @param maxResults 最大的记录数
     * @return 角色列表
     */
    public List<Role> getRoleList(String roleName, int skipResults, int maxResults) {

        Map<String, Object> params = new HashMap<String, Object>();
        if (!StringUtils.isEmpty(roleName)) {
            params.put("roleName", roleName);
        }

        return getSqlSession().selectList("Role.getRoleList", params, new RowBounds(skipResults, maxResults));
    }

    /**
     * 添加权限
     * 
     * @param roleId 角色ID
     * @param permissionId 权限ID
     */
    public void addPermission(Long roleId, Long permissionId) {

        Map<String, Long> params = new HashMap<String, Long>();
        params.put("roleId", roleId);
        params.put("permissionId", permissionId);
        
        getSqlSession().insert("Role.addPermission", params);
    }

    /**
     * 添加角色
     * 
     * @param role 角色
     * @return 返回新增角色的ID
     */
    public Role addRole(Role role) {
        getSqlSession().insert("Role.addRole", role);
        return role;
    }

    /**
     * 删除角色
     * 
     * @param roleId 角色ID
     */
    public void deleteRole(Long roleId) {
        getSqlSession().delete("Role.deleteRole", roleId);
    }

    /**
     * 删除权限
     * 
     * @param roleId 角色ID
     */
    public void deletePermission(Long roleId) {
        getSqlSession().delete("Role.deletePermission", roleId);
    }

    /**
     * 获得角色
     * 
     * @param roleId 角色ID
     * @return 返回与ID匹配的角色
     */
    public Role getRoleById(Long roleId) {
        return (Role) getSqlSession().selectOne("Role.getRoleById", roleId);
    }
}
