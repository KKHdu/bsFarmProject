package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.config.R;

import com.example.demo.entity.UserInfo;

import com.example.demo.mapper.UserInfoMapper;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private UserInfoMapper userInfoMapper;

    @ApiOperation(value = "获取顾客或农户的信息列表显示接口",notes = "参数为roleType，传2表示农户，3表示顾客",httpMethod = "POST")
    @RequestMapping(value = "/getList")
    public R geList(@RequestBody JSONObject params) {
        System.out.println("ss");
        int roleType = params.getInteger("roleType");
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_role",roleType);
        List<UserInfo> customList = userInfoMapper.selectList(wrapper);
        if(customList != null){
            return R.success("数据显示成功",customList);
        }else{
            return R.error("数据显示失败");
        }

    }
}