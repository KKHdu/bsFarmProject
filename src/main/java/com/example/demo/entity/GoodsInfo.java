package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

@Data
@TableName("farm_goods_info")
//@KeySequence("goods_id")
public class GoodsInfo {
    @TableId
    @TableField("goods_id")
    private int goodsId;

    @TableField("goods_name")
    private String goodsName;

    @TableField("goods_desc")
    private String goodsDesc;

    @TableField("goods_price")
    private double goodsPrice;

    @TableField("goods_sale")
    private int goodsSale;

    @TableField("goods_quality")
    private String goodsQuality;

    @TableField("goods_area")
    private String goodsArea;

    @TableField("goods_image")
    private String goodsImage;

    @TableField("goods_owns")
    private String goodsOwns;

    @TableField(value="insert_time", fill=FieldFill.INSERT)
    private Long insertTime;

    @TableField(value="update_time", fill=FieldFill.INSERT_UPDATE)
    private Long updateTime;
}
