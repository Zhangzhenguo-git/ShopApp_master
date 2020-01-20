package com.dc.shopapp.bean;

import java.io.Serializable;

/**
 * @author Zhangzhenguo
 * @create 2020/1/20
 * @Email 18311371235@163.com
 * @Describe
 */
public class MessageResultData implements Serializable {

    public int id;
    public boolean isSelected;
    public boolean isEdit;
    public boolean isAllSelected;

    private static final long serialVersionUID = 1L;
    public String title;
    public String description;
    public String pluginId;
    public String intime;
    public int isRead;//0未读 ;1已读
    public String userid;
    public String jsonContent;
    public Integer type; //0 INNER; 1 DMZ
    public String params;
    private String msgType;

    //未读
    private int notReadCnt;
//    总数
    private int totalCnt;

    public int getNotReadCnt() {
        return notReadCnt;
    }

    public void setNotReadCnt(int notReadCnt) {
        this.notReadCnt = notReadCnt;
    }

    public int getTotalCnt() {
        return totalCnt;
    }

    public void setTotalCnt(int totalCnt) {
        this.totalCnt = totalCnt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public boolean isEdit() {
        return isEdit;
    }

    public void setEdit(boolean edit) {
        isEdit = edit;
    }

    public boolean isAllSelected() {
        return isAllSelected;
    }

    public void setAllSelected(boolean allSelected) {
        isAllSelected = allSelected;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPluginId() {
        return pluginId;
    }

    public void setPluginId(String pluginId) {
        this.pluginId = pluginId;
    }

    public String getIntime() {
        return intime;
    }

    public void setIntime(String intime) {
        this.intime = intime;
    }

    public int getIsRead() {
        return isRead;
    }

    public void setIsRead(int isRead) {
        this.isRead = isRead;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getJsonContent() {
        return jsonContent;
    }

    public void setJsonContent(String jsonContent) {
        this.jsonContent = jsonContent;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
}
