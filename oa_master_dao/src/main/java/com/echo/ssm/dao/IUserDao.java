package com.echo.ssm.dao;

import com.echo.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.Many;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;

public interface IUserDao {

    @Select("select * from users where username = #{username}")
    @Results(
            {
                @Result(id = true,property = "id",column = "id"),
                @Result(property = "username",column = "username"),
                @Result(property = "email",column = "email"),
                @Result(property = "password",column = "password"),
                @Result(property = "phoneNum",column = "phoneNum"),
                @Result(property = "status",column = "status"),
                    //根据Userid去findRoleByUserId中去查
                @Result(property = "roles",column = "id",javaType = java.util.List.class,
                many = @Many(select = "com.echo.ssm.dao.IRoleDao.findRoleByUserId"))
            }
    )
    public UserInfo findByUsername(String username) throws Exception;
}
