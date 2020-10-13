package com.echo.ssm.service;

import com.echo.ssm.domain.Role;

import java.util.List;

public interface IRoleService {

    public List<Role> findAll();

    void save(Role role);
}
