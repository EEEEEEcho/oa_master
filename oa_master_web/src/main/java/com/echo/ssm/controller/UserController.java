package com.echo.ssm.controller;

import com.echo.ssm.domain.Role;
import com.echo.ssm.domain.UserInfo;
import com.echo.ssm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService userService;


    @RequestMapping("/addRoleToUser.do")
    public String addRoleToUser(@RequestParam(name = "userId",required = true) String userId,@RequestParam(name = "ids",required = true) String[] roleIds){
        userService.addRoleToUser(userId,roleIds);
        return "redirect:findAll.do";
    }


    @RequestMapping("/findByUserId.do")
    public ModelAndView findByUserId(String id){
        ModelAndView mv = new ModelAndView();
        UserInfo userInfo = userService.findByUserId(id);
        mv.addObject("user",userInfo);
        mv.setViewName("user-show1");
        return mv;
    }
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        ModelAndView mv = new ModelAndView();
        List<UserInfo> userList = userService.findAll();
        mv.addObject("userList",userList);
        mv.setViewName("user-list");
        return mv;
    }
    @RequestMapping("/save.do")
    public String saveUser(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userId){
        /**
         * 查询用户以及用户可以添加的角色
         */
        ModelAndView mv = new ModelAndView();
        //1.根据用户ID查询用户
        UserInfo userInfo = userService.findByUserId(userId);
        //2.根据用户ID查询可以添加的角色
        List<Role> otherRoles = userService.findOtherRoles(userId);
        mv.addObject("user",userInfo);
        mv.addObject("roleList",otherRoles);
        mv.setViewName("user-role-add");
        return mv;
    }
}
