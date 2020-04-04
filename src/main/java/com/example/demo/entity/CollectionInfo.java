package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("farm_collection")
public class CollectionInfo {

    @TableId
    @TableField("collection_id")
    private int collectionId;

    @TableField("goods_id")
    private int goodsId;

    @TableField("goodsName")
    private String goodsName;

    @TableField("user_id")
    private int userId;

    @TableField("user_name")
    private int userName;

    @TableField("collection_status")
    private String collectionStatus;


    @TableField(value="insert_time", fill= FieldFill.INSERT)
    private long insertTime;


}
