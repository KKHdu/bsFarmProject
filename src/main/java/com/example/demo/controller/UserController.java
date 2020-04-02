package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.config.newAnnotation;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.imp.UserSeiviceImp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserMapper userMapper;

//    swagger
//    @ApiOperation(value = "接口的功能介绍",notes = "提示接口使用者注意事项",httpMethod = "GET")
//    @ApiImplicitParam(dataType = "string",name = "name",value = "姓名",required = true)
    @RequestMapping(value = "/login")
    @newAnnotation  // 自定义切面注解
    public JSONObject login(@RequestBody JSONObject params) {
        JSONObject jsonObject = new JSONObject();
        String userId = params.getString("userId");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_Id",userId);
        User user = userMapper.selectOne(wrapper);
        jsonObject.put("返回", user.getCnDes());
        return jsonObject;


    }
}
