package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.demo.config.R;
import com.example.demo.entity.DealInfo;
import com.example.demo.entity.GoodsInfo;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.DealInfoMapper;
import com.example.demo.mapper.GoodsInfoMapper;
import com.example.demo.mapper.UserInfoMapper;
import io.swagger.annotations.ApiOperation;
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

    private GoodsInfoMapper goodsInfoMapper;

    private DealInfoMapper dealInfoMapper;

    @ApiOperation(value = "用户登陆接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/login")
    public R login(@RequestBody JSONObject params) {
        String userName = params.getString("userName");
        String userPass = params.getString("userPass");
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        wrapper.select(UserInfo.class,info -> info.getColumn().equals("user_id") || info.getColumn().equals("user_name") || info.getColumn().equals("user_role"))
                .eq("user_name",userName)
                .eq("user_pass",userPass);

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
        if(params.getUserName() == ""){
            return R.error("用户名为空，新增失败");
        }
        int num = userInfoMapper.insert(params);
        if(num >= 0){
            return R.success("用户新增成功");
        }else{
            return R.error("用户新增失败");
        }
    }

    @ApiOperation(value = "单个用户查询接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfoById")
    public R getUserInfoById(@RequestBody int params) {
        UserInfo userInfo = userInfoMapper.selectById(params);
        if(userInfo != null){
            return R.success("用户查询成功",userInfo);
        }else{
            return R.error("用户更新失败");
        }
    }

    @ApiOperation(value = "模糊查询用户接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/getUserInfoByName")
    public R getUserInfoByName(@RequestBody JSONObject params) {
        UserInfo userInfo = userInfoMapper.selectById(params.getString("userName"));
        if(userInfo != null){
            return R.success("用户查询成功",userInfo);
        }else{
            return R.error("用户更新失败");
        }
    }

    @ApiOperation(value = "用户更新接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/userUpdate")
    public R userUpdate(@RequestBody UserInfo params) {
        QueryWrapper<UserInfo> wrapper = new QueryWrapper<>();
        int num = userInfoMapper.updateById(params);
        if(num >= 0){
            return R.success("用户更新成功");
        }else{
            return R.error("用户更新失败");
        }
    }

    @ApiOperation(value = "用户删除接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/userDel")
    public R userDel(@RequestBody JSONObject params) {
        int num = userInfoMapper.deleteById(params.getString("userId"));
        if(num >= 0){
            return R.success("用户删除成功");
        }else{
            return R.error("用户删除失败");
        }
    }

    @ApiOperation(value = "农产品列表查询接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/getGoodsList")
    public R getGoodsList(@RequestBody JSONObject params) {
        String userId = params.getString("userId");
        String goodsName = params.getString("goodsName");
        int userRole = userInfoMapper.selectById(userId).getUserRole();
        QueryWrapper<GoodsInfo> wrapper = new QueryWrapper<>();
        if(userRole== 1){
            wrapper.like(StringUtils.isNotEmpty(goodsName),"goods_name", goodsName);
        }
        if(userRole== 2){
            wrapper.eq("user_id",userId).like(StringUtils.isNotEmpty(goodsName),"goods_name", goodsName);
        }
        if(userRole== 3){
            wrapper.eq("goods_sale",2).like(StringUtils.isNotEmpty(goodsName),"goods_name", goodsName);
        }

        List<GoodsInfo> list = goodsInfoMapper.selectList(wrapper);

        return R.success("商品列表查询成功",list);
    }

//    @ApiOperation(value = "农产品信息模糊查询接口",notes = "注意参数",httpMethod = "POST")
//    @RequestMapping(value = "/getGoodsInfoByName")
//    public R getGoodsInfoByName(@RequestBody String goodsName) {
//        QueryWrapper<GoodsInfo> wrapper = new QueryWrapper<>();
//        wrapper.like("goods_name", goodsName);
//        List<GoodsInfo> list = goodsInfoMapper.selectList(wrapper);
//        return R.success("农产品信息查询成功",list);
//    }
//
//    @ApiOperation(value = "农产品信息查询接口",notes = "注意参数",httpMethod = "POST")
//    @RequestMapping(value = "/getGoodsInfo")
//    public R getGoodsInfo(@RequestBody int params) {
//        GoodsInfo goodsInfo = goodsInfoMapper.selectById(params);
//        return R.success("农产品信息查询成功",goodsInfo);
//    }


    @ApiOperation(value = "根据用户ID查询订单列表信息",notes = "参数为userId和userRole，都为int类型",httpMethod = "POST")
    @RequestMapping(value = "/getDealList")
    public R geOrderList(@RequestBody JSONObject params) {
        int userId = params.getInteger("userId");
        int userRole = userInfoMapper.selectById(userId).getUserRole();
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

}
