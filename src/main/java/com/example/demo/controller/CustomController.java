package com.example.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.mapper.GoodsInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.config.R;
import com.example.demo.entity.CollectionInfo;
import com.example.demo.entity.DealInfo;
import com.example.demo.entity.GoodsInfo;
import com.example.demo.mapper.CollectionInfoMapper;
import com.example.demo.mapper.DealInfoMapper;

import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;


@RestController
@RequestMapping("/custom")
@AllArgsConstructor
public class CustomController {

    private DealInfoMapper dealInfoMapper;

    private UserInfoMapper userInfoMapper;

    private GoodsInfoMapper goodsInfoMapper;

    private CollectionInfoMapper collectionInfoMapper;

    @ApiOperation(value = "订单新增接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/dealAdd")
    public R geList(@RequestBody DealInfo params) {
    	System.out.println(params);
        int numOne = dealInfoMapper.insert(params);
        if(numOne >= 0){
            return R.success("订单新增成功");
        }else{
            return R.error("订单新增失败");
        }

    }

    @ApiOperation(value = "查询订单列表",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/getDealInfo")
    public R getDealInfo(@RequestBody JSONObject params) {
    	String userId = params.getString("userId");
        String goodsName = params.getString("goodsName");
        int userRole = userInfoMapper.selectById(userId).getUserRole();

        QueryWrapper<GoodsInfo> wrapperGos = new QueryWrapper<>();
        wrapperGos.like("goods_name",goodsName);
        List<GoodsInfo> goodsInfoList = goodsInfoMapper.selectList(wrapperGos);
        List<Integer> goodsIdList = goodsInfoList.stream().map(GoodsInfo::getGoodsId).collect(Collectors.toList());

    	QueryWrapper<DealInfo> wrapper = new QueryWrapper<>();
        if(userRole == 3){
            // 用户查已购
                wrapper.eq("deal_user_id_in", userId)
                        .in("goods_id",goodsIdList);
        }else if(userRole == 2){
//            商家查已售
            wrapper.eq("deal_user_id_out", userId)
                    .in("goods_id",goodsIdList);
//                    .like(StringUtils.isNotEmpty(goodsName),"goods_name", goodsName);
        }

        List<DealInfo> list = dealInfoMapper.selectList(wrapper);
        if(list != null) {
        	return R.success("订单信息查询成功",list);
        }else {
        	return R.success("订单信息查询失败");
        }
        
    }
    
    @ApiOperation(value = "查询购物车列表",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/getCollectionInfo")
    public R getCollectionInfo(@RequestBody CollectionInfo params) {
        List<JSONObject> list = collectionInfoMapper.selectListById(params.getUserId());
        return R.success("农产品信息查询成功",list);
    }

    @ApiOperation(value = "购物车新增",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/collectionAdd")
    public R collectionAdd(@RequestBody CollectionInfo params) {
    	System.out.println(params);
        int num = collectionInfoMapper.insert(params);
        if(num > 0) {
        	return R.success("成功加入购物车");
        }else {
        	return R.success("加入购物车失败");
        }
    }

    @Transactional
    @ApiOperation(value = "修改购物车状态",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/updateCollectionStatus")
    public R updateCollectionStatus(@RequestBody CollectionInfo params) {
        DealInfo dealInfo = new DealInfo();
        String collectionStatus = params.getCollectionStatus();

        int num = collectionInfoMapper.updateById(params);
        if(num >= 0){
            return R.success("购物车状态更新成功");
        }else{
            return R.error("购物车状态更新失败");
        }
    }

}
