package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("farm_role")
public class RoleInfo {

    @TableId
    @TableField("role_id")
    private int roleId;

    @TableField("role_code")
    private String roleCode;

    @TableField("role_label")
    private String roleLabel;

}
