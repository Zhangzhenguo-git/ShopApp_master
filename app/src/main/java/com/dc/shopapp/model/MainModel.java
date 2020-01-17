package com.dc.shopapp.model;

import com.alibaba.fastjson.JSON;
import com.dc.shopapp.R;
import com.dc.shopapp.bean.MainResultData;
import com.dc.shopapp.utils.JavaFunctionUtil;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Handler;

/**
 * @author Zhangzhenguo
 * @create 2020/1/16
 * @Email 18311371235@163.com
 * @Describe
 */
public class MainModel implements MainModelCallback{
    private onReqResult onReqResult;
    private Handler mHandler;
    private List<MainResultData> listData;

    @Override
    public void reqShopAppData(Map<String, Object> map, MainModel.onReqResult reqResult) {
        try {
            Thread.sleep(3000);
            listData=new ArrayList<>();
            for (int i=0;i<20;i++){
                MainResultData mainResultData=new MainResultData();
                mainResultData.setId(i);
                mainResultData.setShopIcon(R.mipmap.ic_launcher);
                mainResultData.setShopName("应用"+i);
                mainResultData.setShopDescribe("应用"+i+"描述***************************");
                if (JavaFunctionUtil.isParityNumber(i)){
                    mainResultData.setShopIsRead(true);
                }else {
                    mainResultData.setShopIsRead(false);
                }
                mainResultData.setShopTime(new SimpleDateFormat().format(new Date()));
                listData.add(mainResultData);
            }
            String resultJson=JSON.toJSONString(listData);
            //如果成功
            if (resultJson!=null){
                reqResult.onSuccess(resultJson);
            }else {
                reqResult.onError("获取失败");
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //    内置回调返回P层数据
    public interface onReqResult{
        void onSuccess(String result);
        void onError(String msg);
    }
}
