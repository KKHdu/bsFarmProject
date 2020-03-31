package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.config.NewAnnotation;
import com.example.demo.entity.GoodsInfo;
import com.example.demo.entity.User;
import com.example.demo.mapper.GoodsInfoMapper;
import com.example.demo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    private UserMapper userMapper;

    private GoodsInfoMapper goodsInfoMapper;

//    swagger
//    @ApiOperation(value = "接口的功能介绍",notes = "提示接口使用者注意事项",httpMethod = "GET")
//    @ApiImplicitParam(dataType = "string",name = "name",value = "姓名",required = true)
    @RequestMapping(value = "/login")
//    @NewAnnotation  // 自定义切面注解
    public JSONObject login(@RequestBody JSONObject params) {
        JSONObject jsonObject = new JSONObject();
        String userId = params.getString("userId");
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("user_Id",userId);
        User user = userMapper.selectOne(wrapper);
        jsonObject.put("返回", user.getCnDes());
        return jsonObject;
    }

    @RequestMapping(value = "/goodsInsert")
    @NewAnnotation
    public JSONObject goodsInsert(@RequestBody GoodsInfo params) {
        JSONObject jsonObject = new JSONObject();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        int res = goodsInfoMapper.insert(params);
        jsonObject.put("插入成功与否：", res);
        return jsonObject;
    }
}
