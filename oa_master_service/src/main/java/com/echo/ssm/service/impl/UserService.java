package com.echo.ssm.service.impl;

import com.echo.ssm.dao.IUserDao;
import com.echo.ssm.domain.UserInfo;
import com.echo.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserService implements IUserService {

    @Autowired
    private IUserDao iUserDao;

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
        User user = new User(userInfo.getUsername(),"{noop}" + userInfo.getPassword(),getAuthority());
        return user;
    }

    //返回一个List集合，集合中装的是角色描述
    public List<SimpleGrantedAuthority> getAuthority(){
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        list.add(new SimpleGrantedAuthority("ROLE_USER"));

        return list;
    }
}
