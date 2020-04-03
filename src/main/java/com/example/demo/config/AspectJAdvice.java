package com.example.demo.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.entity.User;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
@AllArgsConstructor
public class AspectJAdvice {

    private UserInfoMapper userInfoMapper;

//    @Pointcut("execution(* com.example.demo.controller.UserController.login(..)) && @annotation(com.example.demo.config.newAnnotation)")

    // 作用整个controller
    @Pointcut("execution(* com.example.demo.controller.*.*(..))")
    private void aspectJController(){};

    // 作用整个注解方法
    @Pointcut("@annotation(com.example.demo.config.NewAnnotation)")
    private void aspectJFunction(){};

//    @Before("@within(newAnnotation)")
    @Before("aspectJController()")
    public void doBefore(JoinPoint joinPoint) {
        String classNameFull = joinPoint.getTarget().getClass().getName();
        String className = classNameFull.substring((classNameFull.lastIndexOf(".")+1),classNameFull.length());
        String actionName = joinPoint.getSignature().getName();
//        System.out.println(className+"+++"+actionName);

        Object[] args = joinPoint.getArgs();
        JSONObject json = new JSONObject();
        if(args != null && args.length > 0) {
            json = (JSONObject) JSON.toJSON(args[0]);
//            for (Object arg : args) {
//                System.out.println(arg);
//            }
        }
        // 业务逻辑最好拿到service里面封装一下
        UserInfo userInfo = userInfoMapper.selectById(json.getString("userId"));
        if(userInfo != null){
            System.out.println(userInfo.getUserRole());
            if(userInfo.getUserRole() == 1 || "CommonController".equals(className)){
                // 管理员权限
            }else if(userInfo.getUserRole() == 2 && "BusinessController".equals(className) && "AdminController".equals(className)){
                // 农户权限
            }else if(userInfo.getUserRole() == 3 && "UserController".equals(className)){
                // 顾客权限
            }else{
                throw new AuthorityException("你的权限不足！");
            }
        }
    }

    @Before("aspectJFunction()")
    public void doBefore2(JoinPoint joinPoint) {
        String actionName = joinPoint.getSignature().getName();
        System.out.println("---"+actionName);
        Object[] args = joinPoint.getArgs();
        for (Object arg : args) {
            System.out.println(arg);
        }
        if(false){
            throw new AuthorityException("你的权限不足！");
        }
    }
}
