package com.example.demo.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class AutoFillHandler  implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        this.setFieldValByName("insertTime", CurrentTimeUtil.getCurrentTimeLong(), metaObject);
        this.setFieldValByName("updateTime", CurrentTimeUtil.getCurrentTimeLong(), metaObject);

        // 对 租户/用户的密码 进行MD5加密
//        Optional.ofNullable(getFieldValByName("tenantPass",metaObject))
//                .ifPresent(t -> {
//                    this.setFieldValByName("tenantPass", Md5Util.textToMd5(t.toString()), metaObject);
//                });
//        Optional.ofNullable(getFieldValByName("userPass",metaObject))
//                .ifPresent(u -> {
//                    this.setFieldValByName("userPass", Md5Util.textToMd5(u.toString()), metaObject);
//                });

    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.setFieldValByName("updateTime", CurrentTimeUtil.getCurrentTimeLong(), metaObject);
    }

}

