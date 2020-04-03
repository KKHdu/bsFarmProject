package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.config.R;
import com.example.demo.entity.ApproveInfo;
import com.example.demo.entity.Collection;
import com.example.demo.entity.DealInfo;
import com.example.demo.mapper.ApproveInfoMapper;
import com.example.demo.mapper.CollectionMapper;
import com.example.demo.mapper.DealInfoMapper;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class CustomController {

    private DealInfoMapper dealInfoMapper;

    private ApproveInfoMapper approveInfoMapper;

    private CollectionMapper collectionMapper;

    @Transactional
    @ApiOperation(value = "订单新增接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/dealAdd")
    public R geList(@RequestBody DealInfo params) {
        if(params.getDealUserNameIn() == null){
            return R.error("顾客用户名为空，新增失败");
        }
        int numOne = dealInfoMapper.insert(params);
        DealInfo dealInfo = new DealInfo();
        int goodsId = dealInfo.getGoodsId();

        ApproveInfo approveInfo = new ApproveInfo();
        approveInfo.setGoodsId(goodsId);
        int numTwo = approveInfoMapper.insert(approveInfo);
        if(numOne >= 0 && numTwo >=0){
            return R.success("订单新增成功");
        }else{
            return R.error("订单新增失败");
        }

    }

//    @ApiOperation(value = "根据查询接口",notes = "注意参数",httpMethod = "POST")
//    @RequestMapping(value = "/getGoodsInfo")
//    public R getGoodsInfo(@RequestBody int params) {
//        GoodsInfo goodsInfo = goodsInfoMapper.selectById(params);
//        return R.success("农产品信息查询成功",goodsInfo);
//    }

    @ApiOperation(value = "根据用户ID查询订单列表信息",notes = "参数为userId和userRole，都为int类型",httpMethod = "POST")
    @RequestMapping(value = "/getDealList")
    public R geOrderList(@RequestBody JSONObject params) {
        int userId = params.getInteger("userId");
        int userRole = params.getInteger("userRole");
        QueryWrapper<DealInfo> wrapper = new QueryWrapper<>();
        if(userRole == 3){
            // 表示为顾客查询订单
            wrapper.eq("deal_user_id_in",userId);
        }else {
            wrapper.eq("deal_user_id_out",userId);
        }
        List<DealInfo> dealList = dealInfoMapper.selectList(wrapper);
        if(dealList != null){
            return R.success("数据显示成功",dealList);
        }else{
            return R.error("数据显示失败");
        }
    }

    @ApiOperation(value = "查询购物车列表",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/getCollectionInfo")
    public R getCollectionInfo(@RequestBody int params) {
        List<JSONObject> list = collectionMapper.selectListById(params);
        return R.success("农产品信息查询成功",list);
    }

    @ApiOperation(value = "购物车新增",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/collectionAdd")
    public R collectionAdd(@RequestBody Collection params) {
        int num = collectionMapper.insert(params);
        return R.success("成功加入购物车");
    }

}
