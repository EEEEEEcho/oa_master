package com.echo.ssm.dao;

import com.echo.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {
    @Select("Select * from product")
    public List<Product> findAll() throws Exception;

    @Insert("insert into product(id,productNum,productName,cityName,departureTime,productPrice," +
            "productDesc,productStatus)values(#{productNum},#{productNum},#{productName},#{cityName},#{departureTime}," +
            "#{productPrice},#{productDesc},#{productStatus})")
    public void save(Product product) throws Exception;

    //根据ID查询产品
    @Select("select * from product where id=#{id}")
    public Product findById(String id) throws Exception;
}
