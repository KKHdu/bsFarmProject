package com.example.demo.config;

import java.lang.annotation.*;

/*
* TYPE:用于描述类、接口(包括注解类型) 或enum声明
* METHOD:用于描述方法
* */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE,ElementType.METHOD})
public @interface NewAnnotation {
    String[] rules() default {};
}
