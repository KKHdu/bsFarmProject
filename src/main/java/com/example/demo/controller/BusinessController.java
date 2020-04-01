package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.config.NewAnnotation;
import com.example.demo.config.R;
import com.example.demo.entity.GoodsInfo;
import com.example.demo.entity.User;
import com.example.demo.mapper.GoodsInfoMapper;
import com.example.demo.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
@AllArgsConstructor
public class BusinessController {

    private UserMapper userMapper;

    private GoodsInfoMapper goodsInfoMapper;

    @RequestMapping(value = "/goodsInsert")
//    @NewAnnotation
    public R goodsInsert(@RequestBody GoodsInfo params) {
        JSONObject jsonObject = new JSONObject();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        try{
            int res = goodsInfoMapper.insert(params);
            return R.success("商品插入成功");
        }catch (Exception e){
            return R.error(e.toString());
        }
    }
}
