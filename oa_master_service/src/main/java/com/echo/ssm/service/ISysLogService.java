package com.echo.ssm.service;

import com.echo.ssm.domain.SysLog;

import java.util.List;

public interface ISysLogService {
    public void save(SysLog sysLog);

    public List<SysLog> findAll();
}
