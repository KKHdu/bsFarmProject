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
@RequestMapping("/farmer")
@AllArgsConstructor
public class FarmerController {

    private GoodsInfoMapper goodsInfoMapper;

    private ApproveInfoMapper approveInfoMapper;

    @Transactional
    @ApiOperation(value = "商品插入接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/goodsInsert")
//    @NewAnnotation
    public R goodsInsert(@RequestBody GoodsInfo params) {

        // 商品在售0、下架1、审批中2、审批失败3状态
        try{
            GoodsInfo goodsInfo = new GoodsInfo();
            int numOne = goodsInfoMapper.insert(params);
            int goodsId = goodsInfo.getGoodsId();
            ApproveInfo approveInfo = new ApproveInfo();
            approveInfo.setGoodsId(goodsId);
            int numTwo = approveInfoMapper.insert(approveInfo);
            if(numOne > 0 && numTwo > 0){
                return R.success("商品插入成功,已提交审批");
            }else{
                return R.error("商品插入失败");
            }

        }catch (Exception e){
            return R.error(e.toString());
        }
    }

    @ApiOperation(value = "农产品信息删除接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/goodsInfoDel")
    public R goodsInfoDel(@RequestBody int params) {
        int num = goodsInfoMapper.deleteById(params);
        if(num > 0){
            return R.success("农产品信息删除成功");
        }else{
            return R.error("农产品信息删除失败");
        }
    }

    @ApiOperation(value = "农产品信息更新接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/goodsInfoUpdate")
    public R goodsInfoUpdate(@RequestBody GoodsInfo params) {
        int num = goodsInfoMapper.updateById(params);
        if(num > 0){
            return R.success("农产品信息更新成功");
        }else{
            return R.error("农产品信息更新失败");
        }
    }
}
