package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.config.R;
import com.example.demo.entity.ApproveInfo;
import com.example.demo.entity.CollectionInfo;
import com.example.demo.entity.DealInfo;
import com.example.demo.mapper.ApproveInfoMapper;
import com.example.demo.mapper.CollectionInfoMapper;
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

    private CollectionInfoMapper collectionInfoMapper;

    @ApiOperation(value = "订单新增接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/dealAdd")
    public R geList(@RequestBody DealInfo params) {
        if(params.getDealUserNameIn() == null){
            return R.error("顾客用户名为空，新增失败");
        }
        int numOne = dealInfoMapper.insert(params);

        if(numOne >= 0){
            return R.success("订单新增成功");
        }else{
            return R.error("订单新增失败");
        }

    }

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
            // 表示为农户查询订单
            wrapper.eq("deal_user_id_out",userId);
        }
        List<DealInfo> dealList = dealInfoMapper.selectList(wrapper);
        if(dealList != null){
            return R.success("数据显示成功",dealList);
        }else{
            return R.error("数据显示失败");
        }

    }

    @ApiOperation(value = "修改购物车状态",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/updateCollectionStatus")
    public R updateCollectionStatus(@RequestBody CollectionInfo params) {

        int num = collectionInfoMapper.updateById(params);
        if(num >= 0){
            return R.success("购物车状态更新成功");
        }else{
            return R.error("购物车状态更新失败");
        }

    }

}
