package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("farm_approve")
public class Approve {

    @TableId
    @TableField("approve_id")
    private int approveId;

    @TableField("approve_admin")
    private String approveAdmin;

    @TableField("goods_id")
    private int goodsId;

    @TableField("approve_result")
    private String approveResult;

    @TableField("approve_desc")
    private String approveDesc;

    @TableField("insert_time")
    private long insertTime;


}
