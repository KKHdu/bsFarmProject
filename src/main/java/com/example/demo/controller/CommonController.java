package com.example.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.demo.config.R;
import com.example.demo.entity.GoodsInfo;
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

import java.util.List;

@RestController
@RequestMapping("/common")
@AllArgsConstructor
public class CommonController {

    private UserInfoMapper userInfoMapper;
    private GoodsInfoMapper goodsInfoMapper;

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
    public R userDel(@RequestBody int params) {
        int num = userInfoMapper.deleteById(params);
        if(num >= 0){
            return R.success("用户删除成功");
        }else{
            return R.error("用户删除失败");
        }
    }

    @ApiOperation(value = "农产品列表查询接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/getGoodsList")
    public R getGoodsList(@RequestBody int params) {
        QueryWrapper<GoodsInfo> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id",params);
        List<GoodsInfo> list = goodsInfoMapper.selectList(wrapper);

        return R.success("商品列表查询成功",list);
    }

    @ApiOperation(value = "农产品信息查询接口",notes = "注意参数",httpMethod = "POST")
    @RequestMapping(value = "/getGoodsInfo")
    public R getGoodsInfo(@RequestBody int params) {
        GoodsInfo goodsInfo = goodsInfoMapper.selectById(params);
        return R.success("农产品信息查询成功",goodsInfo);
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
