package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("farm_deal_info")
public class DealInfo {

    @TableId
    @TableField("deal_id")
    private String dealId;

    @TableField("deal_user_id_in")
    private String dealUserIdIn;

    @TableField("deal_user_id_out")
    private String dealUserIdOut;

    @TableField("deal_user_name_in")
    private String dealUserNameIn;

    @TableField("deal_user_name_out")
    private String dealUserNameOut;

    @TableField("deal_price")
    private String dealPrice;

    @TableField("deal_status")
    private String dealStatus;

    @TableField(value="insert_time", fill= FieldFill.INSERT)
    private Long insertTime;

    @TableField(value="update_time", fill= FieldFill.INSERT_UPDATE)
    private Long updateTime;

}
