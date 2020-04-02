package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("farm_user_info")
public class UserInfo {
    @TableId
    @TableField("user_id")
    private int userId;

    @TableField("user_name")
    private String userName;

    @TableField("user_age")
    private int userAge;

    @TableField("user_sex")
    private int userSex;

    @TableField("user_phone")
    private String userPhone;

    @TableField("user_address")
    private String userAddress;

    @TableField("user_pass")
    private String userPass;

    @TableField("user_role")
    private int userRole;

    @TableField(value="insert_time", fill=FieldFill.INSERT)
    private long insertTime;

    @TableField("user_bus_license")
    private String userBusLicense;
}
