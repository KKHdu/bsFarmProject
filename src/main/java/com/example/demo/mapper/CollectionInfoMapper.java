package com.example.demo.mapper;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo.entity.CollectionInfo;
import org.apache.ibatis.annotations.Select;

import java.util.List;


public interface CollectionInfoMapper extends BaseMapper<CollectionInfo> {
    @Select("select a.goods_id,a.collection_id,a.insert_time,b.goods_name,b.goods_desc,b.goods_price\n" +
            "from farm_collection a\n" +
            "left join farm_goods_info b\n" +
            "on a.goods_id = b.goods_id\n" +
            "where a.user_id = #{id} and a.collection_status = '已收藏'")
    List<JSONObject> selectListById(int id);
}
