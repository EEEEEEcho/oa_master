package com.echo.ssm.service;

import com.echo.ssm.domain.Permission;
import com.echo.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll();

    void save(Role role);

    Role findRoleById(String roldId);

    List<Permission> findOtherPermissionByRoleId(String roldId);

    void addPermissionToRole(String id, String[] permissionIds);
}
