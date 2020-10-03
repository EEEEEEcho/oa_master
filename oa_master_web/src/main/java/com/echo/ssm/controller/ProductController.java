package com.echo.ssm.controller;

import com.echo.ssm.domain.Product;
import com.echo.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        //对应product-list.jsp中的 productList变量
        mv.addObject("productList",products);
        //product-list.jsp
        mv.setViewName("product-list");
        return mv;
    }
}
