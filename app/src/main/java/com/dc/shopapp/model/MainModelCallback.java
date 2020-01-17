package com.dc.shopapp.model;

import java.util.Map;

/**
 * @author Zhangzhenguo
 * @create 2020/1/16
 * @Email 18311371235@163.com
 * @Describe
 */
public interface MainModelCallback {
    void reqShopAppData(Map<String,Object> map, MainModel.onReqResult reqResult);
}
