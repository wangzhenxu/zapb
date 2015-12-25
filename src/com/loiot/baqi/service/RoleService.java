package com.loiot.baqi.service;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.loiot.baqi.controller.response.Pager;
import com.loiot.baqi.dao.RoleDao;
import com.timeloit.pojo.Permission;
import com.timeloit.pojo.Role;

/**
 * 角色业务逻辑类
 * 
 * @author zhengrj
 * 
 */
@Service("roleService")
public class RoleService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    /**
     * 角色数据访问接口
     */
    @Resource
    private RoleDao roleDao;

    /**
     * 添加角色
     * 
     * @param role 角色
     */
    public void addRole(Role role) {

        // 打印role信息
        log.debug("role [{}]", role);

        // 添加角色
        roleDao.addRole(role);

        // 增加角色权限
        for (Permission permission : role.getPermissionList()) {
            roleDao.addPermission(role.getRoleId(), permission.getPermissionId());
        }
    }

    /**
     * 修改角色
     * 
     * @param role 角色
     */
    public void updateRole(Role role) {

        // 打印role信息
        log.debug("role [{}]", role);

        // 删除旧权限
        roleDao.deletePermission(role.getRoleId());

        // 增加新权限
        for (Permission permission : role.getPermissionList()) {
            roleDao.addPermission(role.getRoleId(), permission.getPermissionId());
        }
    }

    /**
     * 查询角色列表分页
     * 
     * @param roleName 角色名
     * @param pageIndex 页索引
     * @return
     */
    public Pager<Role> getRoleListPage(String roleName, int pageIndex) {

        // 查询角色列表总条数
        int totalResults = roleDao.getRoleListCount(roleName);

        // 构造一个分页器
        Pager<Role> pager = new Pager<Role>(totalResults, pageIndex);

        // 查询角色列表
        List<Role> roleList = roleDao.getRoleList(roleName, pager.getSkipResults(), pager.getMaxResults());
        pager.setData(roleList);

        return pager;
    }

    /**
     * 获得所有角色
     * 
     * @return 角色列表
     */
    public List<Role> getRoleListAll() {
        return roleDao.getRoleList("", 0, Integer.MAX_VALUE);
    }

    /**
     * 获得角色
     * 
     * @param roleId 角色ID
     * @return 返回与ID匹配的角色
     */
    public Role getRole(Long roleId) {
        return roleDao.getRoleById(roleId);
    }

    /**
     * 删除角色
     * 
     * @param roleId
     */
    public void deleteRole(Long roleId) {

        // 删除权限
        roleDao.deletePermission(roleId);

        // 删除角色
        roleDao.deleteRole(roleId);
    }
}
