package com.echo.ssm.service.impl;

import com.echo.ssm.dao.IProductDao;
import com.echo.ssm.domain.Product;
import com.echo.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    public List<Product> findAll() throws Exception {
        return productDao.findAll();
    }
}
