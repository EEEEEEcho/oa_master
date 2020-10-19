package com.echo.ssm.service;

import com.echo.ssm.domain.Role;
import com.echo.ssm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface IUserService extends UserDetailsService {
    public List<UserInfo> findAll() throws Exception;

    public void save(UserInfo userInfo);

    public UserInfo findByUserId(String id);

    List<Role> findOtherRoles(String userId);

    void addRoleToUser(String userId, String[] roleIds);
}
