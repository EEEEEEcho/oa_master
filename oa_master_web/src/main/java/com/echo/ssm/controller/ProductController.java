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

    //查询全部产品
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception {
        ModelAndView mv = new ModelAndView();
        List<Product> products = productService.findAll();
        //对应product-list.jsp中的 productList变量
        mv.addObject("productList",products);
        //product-list.jsp
        mv.setViewName("product-list1");
        return mv;
    }

    //添加产品
    @RequestMapping("/save.do")
    public String save(Product product) throws Exception{
        productService.save(product);
        //添加操作完成后，刷新列表
        return "redirect:findAll.do";
    }
}
