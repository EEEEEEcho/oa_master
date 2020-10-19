package com.echo.ssm.dao;

import com.echo.ssm.domain.Role;
import com.echo.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

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

    @Select("select * from users")
    public List<UserInfo> findAll() throws Exception;

    @Insert("insert into users(id,email,username,password,phoneNum,status) values(#{id},#{email},#{username},#{password},#{phoneNum},#{status})")
    public void save(UserInfo userInfo);

    @Select("select * from users where id = #{id}")
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
    public UserInfo findByUserId(String id);

    @Select("select * from role where id not in(select roleId from users_role where userId=#{userId})")
    List<Role> findOtherRoles(String userId);

    @Insert("insert into users_role(userId,roleId) values (#{userId},#{roleId})")
    void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);
}
