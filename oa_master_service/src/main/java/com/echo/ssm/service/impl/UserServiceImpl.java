package com.echo.ssm.service.impl;

import com.echo.ssm.dao.IUserDao;
import com.echo.ssm.domain.Role;
import com.echo.ssm.domain.UserInfo;
import com.echo.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao iUserDao;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        UserInfo userInfo = null;
        try {
            userInfo = iUserDao.findByUsername(s);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //处理自己的User对象，将其封装成Security自定义的实现了UserDetails接口的User
        //密码不做加密处理时，需要添加{noop}前缀
        //User user = new User(userInfo.getUsername(),"{noop}" + userInfo.getPassword(),getAuthority(userInfo.getRoles()));
        User user = new User(userInfo.getUsername(),userInfo.getPassword(),userInfo.getStatus() == 0 ? false : true,true,true,true,getAuthority(userInfo.getRoles()));
        return user;
    }

    //返回一个List集合，集合中装的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for(Role role : roles){
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll() throws Exception {
        List<UserInfo> userInfos = iUserDao.findAll();
        //System.out.println(userInfos);
        return userInfos;
    }

    @Override
    public void save(UserInfo userInfo) {
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        iUserDao.save(userInfo);
    }

    @Override
    public UserInfo findByUserId(String id) {
        return iUserDao.findByUserId(id);
    }

    @Override
    public List<Role> findOtherRoles(String userId) {
        return iUserDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(String userId, String[] roleIds) {
        for(String roleId:roleIds){
            iUserDao.addRoleToUser(userId,roleId);
        }
    }
}
