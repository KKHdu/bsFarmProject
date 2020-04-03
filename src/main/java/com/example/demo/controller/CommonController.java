package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.config.R;
import com.example.demo.entity.User;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.GoodsInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/common")
@AllArgsConstructor
public class CommonController {

    private UserInfoMapper userInfoMapper;

    @RequestMapping(value = "/login")
    public R login(@RequestBody JSONObject params) {
        String userId = params.getString("userId");
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_Id",userId);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        if(userInfo != null){
            return R.success("认证成功",userInfo);
        }else{
            return R.error("认证失败");
        }
    }
    @RequestMapping(value = "/lo")
    public R log(@RequestBody JSONObject params) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_role",2);
        List<UserInfo> customList = userInfoMapper.selectList(wrapper);
        if(customList != null){
            return R.success("数据显示成功",customList);
        }else{
            return R.error("数据显示失败");
        }
    }
}
