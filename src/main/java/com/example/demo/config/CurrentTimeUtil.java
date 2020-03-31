package com.example.demo.config;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Slf4j
public class CurrentTimeUtil {

    public static void main(String[] args){
        System.out.println(stringToDate("2019-11-07 10:57:53"));
    }

    public static String getCurrentTime() {
        Date nowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(nowDate);
    }

    public static Long getCurrentTimeLong() {
        Date nowDate = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
//        return formatter.format(nowDate);
        Long timeInt = Long.parseLong(formatter.format(nowDate));
        return timeInt;
    }

    public static Date stringToDate(String string){
        if (Strings.isNullOrEmpty(string)){
            return null;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return formatter.parse(string);
        } catch (ParseException e) {
            log.error("日期解析失败");
            return null;
        }
    }
}

