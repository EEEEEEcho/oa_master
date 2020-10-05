package com.echo.ssm.service;

import com.echo.ssm.domain.Order;

import java.util.List;

public interface IOrderService {
    List<Order> findAll(int page,int size) throws Exception;

    Order findById(String ordersId) throws Exception;
}
