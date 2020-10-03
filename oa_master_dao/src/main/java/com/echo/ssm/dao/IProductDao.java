package com.echo.ssm.dao;

import com.echo.ssm.domain.Product;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface IProductDao {
    @Select("Select * from product")
    public List<Product> findAll() throws Exception;
}
