package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.config.NewAnnotation;
import com.example.demo.config.R;
import com.example.demo.entity.ApproveInfo;
import com.example.demo.entity.GoodsInfo;
import com.example.demo.entity.User;
import com.example.demo.mapper.ApproveInfoMapper;
import com.example.demo.mapper.GoodsInfoMapper;
import com.example.demo.mapper.UserMapper;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/business")
@AllArgsConstructor
public class FarmerController {

    private GoodsInfoMapper goodsInfoMapper;

    private ApproveInfoMapper approveInfoMapper;

    @Transactional
    @ApiOperation(value = "商品插入接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/goodsInsert")
//    @NewAnnotation
    public R goodsInsert(@RequestBody GoodsInfo params) {

        try{
            int numOne = goodsInfoMapper.insert(params);
            GoodsInfo goodsInfo = new GoodsInfo();
            int goodsId = goodsInfo.getGoodsId();
            ApproveInfo approveInfo = new ApproveInfo();
            approveInfo.setGoodsId(goodsId);
            int numTwo = approveInfoMapper.insert(approveInfo);
            if(numOne > 0 && numTwo > 0){
                return R.success("商品插入成功");
            }else{
                return R.error("商品插入失败");
            }

        }catch (Exception e){
            return R.error(e.toString());
        }
    }

    @ApiOperation(value = "商品查询接口",notes = "注意参数",httpMethod = "POST")
    public R goodsSelect(@RequestBody JSONObject params){
        return R.success("");
    }
}
