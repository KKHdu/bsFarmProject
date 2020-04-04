package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("farm_goods_status")
public class GoodsStatus {

    @TableId
    @TableField("goods_status_code")
    private int goodsStatusCode;

    @TableField("goods_status_label")
    private String goodsStatusLabel;

}
