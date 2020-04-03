package com.example.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.GoodsInfo;
import org.apache.ibatis.annotations.Select;

public interface GoodsInfoMapper extends BaseMapper<GoodsInfo> {
    @Select("fghj")
    int xxx(String s, String o);

}
