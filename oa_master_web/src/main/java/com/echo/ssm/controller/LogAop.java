package com.echo.ssm.controller;

import com.echo.ssm.domain.SysLog;
import com.echo.ssm.service.ISysLogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;


import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Component  //声明这是一个组件，一个javabean
@Aspect     //声明切面
public class LogAop {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private ISysLogService sysLogService;

    private Date visitTime; //开始时间
    private Class clazz;    //访问的类
    private Method method;  //访问的方法

    //声明前置通知,主要是获取开始时间，执行的类是哪一个，执行的是哪一个方法
    @Before("execution(* com.echo.ssm.controller.*.*(..))")   //拦截所有controller中所有类下的所有方法
    public void doBefore(JoinPoint jp) throws NoSuchMethodException {
        visitTime = new Date();     //访问时间就是i当前时间
        clazz = jp.getTarget().getClass();//获取具体要访问的类
        String methodName = jp.getSignature().getName();    //没办法直接获取到访问的方法对象，获取访问的方法名
        Object[] args = jp.getArgs();   //获取访问的方法的参数列表
        //通过反射构造访问的方法对象
        if(args == null || args.length == 0){
            method = clazz.getMethod(methodName);
        }
        else{
            Class[] methodArgs = new Class[args.length];
            for(int i=0;i < args.length;i ++){
                methodArgs[i] = args[i].getClass();
            }
            method = clazz.getMethod(methodName,methodArgs);
        }
    }

    //声明后置通知,
    @After("execution(* com.echo.ssm.controller.*.*(..))")   //拦截所有controller中所有类下的所有方法
    public void doAfter(JoinPoint jp){
        long time = new Date().getTime() - visitTime.getTime(); //获取访问时长

        //获取URL  通过反射获取注解
        String url = "";
        if(clazz!=null && method!= null && clazz!=LogAop.class){
            //如果拦截到的类和方法不为空，且该类不是controller包中的LogAop类。（因为其他类都有RequestMaping)
            //1.获取类上的@RequestMapping("/orders") 中的这个/orders位置的参数
            RequestMapping classAnnotation = (RequestMapping) clazz.getAnnotation(RequestMapping.class);
            if(classAnnotation!=null){
                //classAnnotation.value() 的第0个就是url的名字
                String[] classValue = classAnnotation.value();
                //2.获取方法上的@RequestMapping("/findAll.do") 中的这个/findAll.do位置的参数
                RequestMapping methodAnnotation = method.getAnnotation(RequestMapping.class);
                if(methodAnnotation != null){
                    //获取方法的注解
                    //methodAnnotation.value()的第0个就是url的名字
                    String[] methodValue = methodAnnotation.value();
                    url = classValue[0] + methodValue[0];
                }
            }
        }

        //获取ip
        String ip = request.getRemoteAddr();

        //可以通过SecurityContext获取用户
        SecurityContext context = SecurityContextHolder.getContext();
        User user = (User) context.getAuthentication().getPrincipal();
        String username = user.getUsername();

        //封装信息到SysLog中
        SysLog sysLog = new SysLog();
        sysLog.setExecutionTime(time);
        sysLog.setUrl(url);
        sysLog.setIp(ip);
        sysLog.setUsername(username);
        sysLog.setMethod("[类名]:" + clazz.getName() + "[方法名]:" + method.getName());
        sysLog.setVisitTime(visitTime);

        //调用Service完成操作
        sysLogService.save(sysLog);
    }
}
