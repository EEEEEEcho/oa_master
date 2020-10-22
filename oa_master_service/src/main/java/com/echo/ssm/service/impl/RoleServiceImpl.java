package com.echo.ssm.service.impl;

import com.echo.ssm.dao.IRoleDao;
import com.echo.ssm.domain.Permission;
import com.echo.ssm.domain.Role;
import com.echo.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao iRoleDao;

    @Override
    public List<Role> findAll() {
        return iRoleDao.findAll();
    }

    @Override
    public void save(Role role) {
        iRoleDao.save(role);
    }

    @Override
    public Role findRoleById(String roleId){
        return iRoleDao.findRoleById(roleId);
    }

    @Override
    public List<Permission> findOtherPermissionByRoleId(String roleId) {
        return iRoleDao.findOtherPermissionByRoleId(roleId);
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) {
        for(String permissionId : permissionIds){
            iRoleDao.addPermissionToRole(roleId,permissionId);
        }
    }
}
