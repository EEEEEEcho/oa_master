package com.echo.ssm.service.impl;

import com.echo.ssm.dao.IPermissionDao;
import com.echo.ssm.domain.Permission;
import com.echo.ssm.service.IPremissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements IPremissionService {
    @Autowired
    IPermissionDao iPermissionDao;
    @Override
    public List<Permission> findAll() {
        return iPermissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        iPermissionDao.save(permission);
    }
}
