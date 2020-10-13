package com.echo.ssm.dao;

import com.echo.ssm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IRoleDao {

    //根据用户ID查询出所有对应的角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results(
            {
                    @Result(id = true,property = "id",column = "id"),
                    @Result(property = "roleName",column = "roleName"),
                    @Result(property = "roleDesc",column = "roleDesc"),
                    @Result(property = "permissions",column = "id",javaType = java.util.List.class,
                        many = @Many(select = "com.echo.ssm.dao.IPermissionDao.findPermissionByRoleId")
                    )
            }
    )
    public List<Role> findRoleByUserId(String userId) throws Exception;

    @Select("select * from role")
    public List<Role> findAll();

    @Insert("insert into role(id,roleName,roleDesc) values (#{id},#{roleName},#{roleDesc})")
    void save(Role role);
}
