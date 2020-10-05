package com.echo.ssm.dao;

import com.echo.ssm.domain.Member;
import com.echo.ssm.domain.Order;
import com.echo.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrderDao {

    @Select("select * from orders")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum" ,column = "orderNum"),
            @Result(property = "orderTime" ,column = "orderTime"),
            @Result(property = "orderStatus" ,column = "orderStatus"),
            @Result(property = "peopleCount" ,column = "peopleCount"),
            @Result(property = "payType" ,column = "payType"),
            @Result(property = "orderDesc" ,column = "orderDesc"),
            @Result(property = "product" ,column = "productId",javaType = Product.class,one = @One(
                select = "com.echo.ssm.dao.IProductDao.findById"
            )),
    })
    public List<Order> findAll() throws Exception;


    //涉及多表操作
    @Select("select * from orders where id = #{ordersId}")
    @Results({
            @Result(id = true,property = "id",column = "id"),
            @Result(property = "orderNum" ,column = "orderNum"),
            @Result(property = "orderTime" ,column = "orderTime"),
            @Result(property = "orderStatus" ,column = "orderStatus"),
            @Result(property = "peopleCount" ,column = "peopleCount"),
            @Result(property = "payType" ,column = "payType"),
            @Result(property = "orderDesc" ,column = "orderDesc"),
            @Result(property = "product" ,column = "productId",javaType = Product.class,one = @One(
                    select = "com.echo.ssm.dao.IProductDao.findById"
            )),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(
                    select = "com.echo.ssm.dao.IMemberDao.findById"
            )),
            //此处的id是订单id，因为要从中间表去查
            @Result(property = "travellers",column = "id",javaType = java.util.List.class,many = @Many(
                    select = "com.echo.ssm.dao.ITravellerDao.findByOrdersId"
            ))
    })
    public Order findById(String ordersId) throws Exception;
}
