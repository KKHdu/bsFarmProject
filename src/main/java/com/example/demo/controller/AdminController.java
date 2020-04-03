package com.example.demo.controller;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.config.R;

import com.example.demo.entity.ApproveInfo;
import com.example.demo.entity.GoodsInfo;
import com.example.demo.entity.UserInfo;

import com.example.demo.mapper.ApproveInfoMapper;
import com.example.demo.mapper.GoodsInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
@AllArgsConstructor
public class AdminController {

    private UserInfoMapper userInfoMapper;

    private ApproveInfoMapper approveInfoMapper;

    private GoodsInfoMapper goodsInfoMapper;

    @ApiOperation(value = "获取顾客或农户的信息列表显示接口",notes = "参数为roleType，传2表示农户，3表示顾客",httpMethod = "POST")
    @RequestMapping(value = "/getUserList")
    public R geList(@RequestBody JSONObject params) {
        int roleType = params.getInteger("roleType");
        String userName = params.getString("userName");
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.select(UserInfo.class,info -> !info.getColumn().equals("user_pass"))
                .eq("user_role",roleType);
        List<UserInfo> customList = userInfoMapper.selectList(wrapper);
        if(customList != null){
            return R.success("数据显示成功",customList);
        }else{
            return R.error("数据显示失败");
        }
    }

    @Transactional
    @ApiOperation(value = "管理员审批农产品接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/approveGoods")
    public R approveGood(@RequestBody ApproveInfo params) {
        // 审批结果 0未审批 1审批通过 2审批打回
        try{
            int num1 = approveInfoMapper.updateById(params);
            GoodsInfo goodsInfo = new GoodsInfo();
            goodsInfo.setGoodsId(params.getGoodsId());
            goodsInfo.setGoodsSale(params.getApproveResult());
            int num2 = goodsInfoMapper.updateById(goodsInfo);
            if(num1 > 0 && num2 > 0){
                return R.success("操作成功");
            }else{
                return R.error("操作失败");
            }
        }catch (Exception e){
            return R.error(e.getMessage());
        }
    }
}