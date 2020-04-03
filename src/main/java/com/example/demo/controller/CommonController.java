package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.config.R;
import com.example.demo.entity.User;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.GoodsInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import com.example.demo.mapper.UserMapper;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
@AllArgsConstructor
public class CommonController {

    private UserInfoMapper userInfoMapper;

    @ApiOperation(value = "用户登陆接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/login")
    public R login(@RequestBody JSONObject params) {
        String userName = params.getString("userName");
        String userPass = params.getString("userPass");
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.select(UserInfo.class,info -> info.getColumn().equals("user_id") || info.getColumn().equals("user_name"))
                .eq("user_Id",userName)
                .eq("userPass",userPass);

        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        if(userInfo != null){
            return R.success("认证成功",userInfo);
        }else{
            return R.error("认证失败");
        }
    }

    @ApiOperation(value = "用户新增接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/userAdd")
    public R userAdd(@RequestBody UserInfo params) {
        if(params.getUserName() == null){
            return R.error("用户名为空，新增失败");
        }
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        int num = userInfoMapper.insert(params);
        if(num >= 0){
            return R.success("用户新增成功");
        }else{
            return R.error("用户新增失败");
        }
    }

    @ApiOperation(value = "用户更新接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/userAdd")
    public R userUpdate(@RequestBody UserInfo params) {
        if(params.getUserId() <= 0){
            return R.error("用户名为空，新增失败");
        }
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        int num = userInfoMapper.updateById(params);
        if(num >= 0){
            return R.success("用户更新成功");
        }else{
            return R.error("用户更新失败");
        }
    }
}
