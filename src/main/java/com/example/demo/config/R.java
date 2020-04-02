package com.example.demo.config;

import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Builder
@ToString
@Accessors(chain = true)
@AllArgsConstructor
public class R<T> implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String SUCCESS_STR = "success";

    @Getter
    @Setter
    private int code = 0;

    @Getter
    @Setter
    private String msg = SUCCESS_STR;


    @Getter
    @Setter
    private transient T data;

    public R() {
        super();
    }

    public R(T data) {
        super();
        this.data = data;
    }

    public R(T data, String msg) {
        super();
        this.data = data;
        this.msg = msg;
    }

    public R(Throwable e) {
        super();
        this.msg = e.getMessage();
        this.code = 1;
    }
    public static R error(String msg){
        return R.builder().msg(msg).code(1).build();
    }
    public static R expire(String msg){
        return R.builder().msg(msg).code(-1).build();
    }

    public static R success(String msg,Object data){
        return R.builder().code(0).msg(msg).data(data).build();
    }
    public static R success(String msg){
        return R.builder().code(0).msg(msg).build();
    }

    public R(T data, String msg, int code) {
        super();
        this.data = data;
        this.msg = msg;
        this.code = code;
    }
}
