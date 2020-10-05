package com.echo.ssm.controller;

import com.echo.ssm.domain.Order;
import com.echo.ssm.service.IOrderService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private IOrderService iOrderService;


    //查询全部订单，未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception{
//        ModelAndView modelAndView = new ModelAndView();
//        List<Order> orderList = iOrderService.findAll();
//        modelAndView.addObject("ordersList",orderList);
//        modelAndView.setViewName("orders-list");
//        return modelAndView;
//    }
    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(name = "page",required = true,defaultValue = "1")int page,
                                @RequestParam(name = "size",required = true,defaultValue = "4")int size) throws Exception{
        ModelAndView mv = new ModelAndView();
        List<Order> orderList = iOrderService.findAll(page,size);
        //pageinfo就是一个分页的bean
        PageInfo pageInfo = new PageInfo(orderList);
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");
        return mv;
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name = "id",required = true)String ordersId) throws Exception {
        ModelAndView mv = new ModelAndView();
        Order order = iOrderService.findById(ordersId);
        mv.addObject("orders",order);
        mv.setViewName("orders-show");
        return mv;

    }
}
