package com.dc.shopapp.utils;

/**
 * @author Zhangzhenguo
 * @create 2020/1/16
 * @Email 18311371235@163.com
 * @Describe
 */
public class JavaFunctionUtil {

    public static boolean isParityNumber(int arg0){
        //余2不等于0是奇数
        if (arg0%2!=0){
            return true;
        }else {
//            等于0则是偶数
            return false;
        }
    }
}
