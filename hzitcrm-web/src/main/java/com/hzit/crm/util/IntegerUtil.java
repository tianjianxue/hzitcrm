package com.hzit.crm.util;

/**
 * Created by 冼耀基 on 2016/11/4.
 */
public class IntegerUtil {
    public static boolean isNotEmpty(Integer input){
        return (input== null)?true:false;
    }
    public static Integer isNotNull(Integer integer){
        return (isNotEmpty(integer))?0:integer;
    }
}
