package com.echo.ssm.service;

import com.echo.ssm.domain.Permission;

import java.util.List;

public interface IPremissionService {

    List<Permission> findAll();

    void save(Permission permission);
}
