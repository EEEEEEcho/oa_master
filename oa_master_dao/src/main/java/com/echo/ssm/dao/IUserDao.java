package com.echo.ssm.dao;

import com.echo.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

public interface IUserDao {

    @Select("select * from users where username = #{username}")
    public UserInfo findByUsername(String username) throws Exception;
}
