package com.dc.shopapp.model;

import com.alibaba.fastjson.JSON;
import com.dc.shopapp.bean.MessageResultData;
import com.dc.shopapp.utils.JavaFunctionUtil;
import com.dc.shopapp.view.MessageCallback;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Zhangzhenguo
 * @create 2020/1/20
 * @Email 18311371235@163.com
 * @Describe
 */
public class MessageModel implements MessageModelCallback {
    private List<MessageResultData> listData;

    @Override
    public void getMessage(MessageCallback callback) {
        if (listData!=null){
            listData.clear();
        }
        listData=new ArrayList<>();
        int index=0;
        for (int i=0;i<110;i++){
            MessageResultData item=new MessageResultData();
            item.setId(i);
            item.setSelected(false);
            item.setEdit(false);
            item.setAllSelected(false);
            item.setTitle("消息名称"+i);
            item.setDescription("11111111111111");
            item.setJsonContent("消息内容消息内容消息内容消息内容消息内容消息内容\"+i");
            item.setIntime("2020-01-20 10:10:0"+i);
            if (JavaFunctionUtil.isParityNumber(i)){
                item.setIsRead(0);
                item.setType(0);
                index++;
                item.setMsgType("000000");
            }else {
                item.setIsRead(1);
                item.setType(1);
                item.setMsgType("111111");
            }
            item.setNotReadCnt(index);
            item.setTotalCnt(110);
            listData.add(item);

        }
        if (listData.size()>0){
            callback.onSuccess(JSON.toJSONString(listData));
        }else {
            callback.onError("获取失败");
        }

    }

    public interface MessageCallback{
        void onSuccess(String json);
        void onError(String msg);

    }
}
