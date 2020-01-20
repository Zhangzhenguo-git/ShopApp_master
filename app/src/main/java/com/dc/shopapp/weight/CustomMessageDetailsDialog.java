package com.dc.shopapp.weight;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dc.shopapp.R;

/**
 * Description TODO
 * Author chu
 * Date 2019/11/22 16:05
 */
public class CustomMessageDetailsDialog extends Dialog {

    private static Context mContext;
    private View view;

    private TextView mTvTitle;
    private TextView mTvContent;
    private TextView mTvType;
    private TextView mTvInTime;
    private TextView mTvOpen;
    private TextView mTvConfirm;
    private View mVLine;

    public CustomMessageDetailsDialog(Context context){
        super(context, R.style.dialog);
        this.mContext = context;
        view = LayoutInflater.from(context).inflate(R.layout.shop_dialog_custom_messagedetails, null);
        mTvTitle = view.findViewById(R.id.dialog_messagedetails_tvTitle);
        mTvContent = view.findViewById(R.id.dialog_messagedetails_tvContent);
        mTvType = view.findViewById(R.id.dialog_messagedetails_tvType);
        mTvInTime = view.findViewById(R.id.dialog_messagedetails_tvInTime);
        mTvOpen = view.findViewById(R.id.dialog_messagedetails_tvCancel);
        mTvConfirm = view.findViewById(R.id.dialog_messagedetails_tvConfirm);
        mVLine = view.findViewById(R.id.dialog_messagedetails_vLine);

        mTvConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setWindow();
    }

    private void setWindow() {
        Window window = this.getWindow();
        window.setContentView(view);
        window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        window.setGravity(Gravity.CENTER);
    }

    public TextView getTvTitle(){
        return mTvTitle;
    }

    public TextView getTvConfirm(){
        return mTvConfirm;
    }

    /**
     * 设置内容
     * @return
     */
    public TextView getTvContent(){
        return mTvContent;
    }
    /**
     * 设置小程序名称
     * @return
     */
    public TextView getTvType(){
        return mTvType;
    }
    /**
     * 打开插件按钮
     * @return
     */
    public TextView getmTvOpen(String plugsIdAndIntent){
        if (TextUtils.isEmpty(plugsIdAndIntent)){
            mTvOpen.setVisibility(View.GONE);
            mVLine.setVisibility(View.GONE);
        }else {
            mTvOpen.setVisibility(View.VISIBLE);
            mVLine.setVisibility(View.VISIBLE);
        }
        return mTvOpen;
    }

    /**
     * 设置
     * @return
     */
    public TextView getTvCancel(boolean type){
        if (type){
            mTvType.setVisibility(View.VISIBLE);
            mTvInTime.setVisibility(View.VISIBLE);
        }else {
            mTvType.setVisibility(View.GONE);
            mTvInTime.setVisibility(View.GONE);
        }

        return mTvOpen;
    }
    /**
     * 设置
     * @return
     */
    public TextView getTvCancel(){
        return mTvOpen;
    }

    /**
     * 设置时间
     * @return
     */
    public void setTvInTime(String inTime){
        mTvInTime.setText(inTime);
    }

}
