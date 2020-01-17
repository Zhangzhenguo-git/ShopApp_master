package com.dc.shopapp.bean;

/**
 * @author Zhangzhenguo
 * @create 2020/1/16
 * @Email 18311371235@163.com
 * @Describe
 */
public class MainResultData{
    /**
     * id,图标，名称，描述，是否已读，时间
     */
    private int id;
    private Object shopIcon;
    private String shopName;
    private String shopDescribe;
    private boolean shopIsRead;
    private String shopTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Object getShopIcon() {
        return shopIcon;
    }

    public void setShopIcon(Object shopIcon) {
        this.shopIcon = shopIcon;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopDescribe() {
        return shopDescribe;
    }

    public void setShopDescribe(String shopDescribe) {
        this.shopDescribe = shopDescribe;
    }

    public boolean isShopIsRead() {
        return shopIsRead;
    }

    public void setShopIsRead(boolean shopIsRead) {
        this.shopIsRead = shopIsRead;
    }

    public String getShopTime() {
        return shopTime;
    }

    public void setShopTime(String shopTime) {
        this.shopTime = shopTime;
    }
}
