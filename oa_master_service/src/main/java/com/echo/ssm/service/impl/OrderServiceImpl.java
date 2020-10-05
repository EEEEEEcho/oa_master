package com.echo.ssm.service.impl;

import com.echo.ssm.dao.IOrderDao;
import com.echo.ssm.domain.Order;
import com.echo.ssm.service.IOrderService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements IOrderService {
    @Autowired
    private IOrderDao iOrderDao;

    @Override
    public List<Order> findAll(int page,int size) throws Exception {
        //pageNum是页码值，pageSize是每页显示条数
        PageHelper.startPage(page,size);
        //pageHelper在使用时，只需要引入依赖，配置插件，然后在真正查询时，执行上面那句代码
        return iOrderDao.findAll();
    }

    @Override
    public Order findById(String ordersId) throws Exception {
        return iOrderDao.findById(ordersId);
    }
}
