package com.dc.shopapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.Group;
import androidx.constraintlayout.widget.Guideline;
import androidx.recyclerview.widget.RecyclerView;

import com.dc.shopapp.R;
import com.dc.shopapp.bean.MainResultData;
import com.dc.shopapp.manager.InstallDownManager;
import com.dc.shopapp.model.InstallDownCallback;

import java.util.List;

/**
 * @author Zhangzhenguo
 * @create 2020/1/17
 * @Email 18311371235@163.com
 * @Describe
 */
public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopHolder> {

    private Context mContext;
    private List<MainResultData> listData;
    private OnItemClickListener onItemClickListener;

    public ShopAdapter(Context mContext, List<MainResultData> listData) {
        this.mContext = mContext;
        this.listData = listData;
    }

    @Override
    public ShopHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View contentView = LayoutInflater.from(mContext).inflate(R.layout.shop_item_layout, parent,false);
        return new ShopHolder(contentView);

    }

    @Override
    public void onBindViewHolder(@NonNull ShopHolder holder, int position) {
         MainResultData item=listData.get(position);
         int progressCount=10;
         holder.appListItemIvLogo.setImageResource(Integer.parseInt(item.getShopIcon().toString()));
         holder.appListItemTvTitle.setText(item.getShopName());
         holder.appListItemTvAreaType.setText(item.getShopIcon().toString());
         progressCount=progressCount++;
        holder.rating.setProgress(progressCount);
        if (item.isShopIsRead()){
            holder.appListItemIvIconOnline.setVisibility(View.VISIBLE);
        }else {
            holder.appListItemIvIconOnline.setVisibility(View.GONE);
        }

        if (position%2!=0){
            holder.appListItemTvInstall.setText(InstallDownManager.APP_INSTALL);
        }else {
            holder.appListItemTvInstall.setText(InstallDownManager.APP_OPEN);
        }

//        安装下载回调
        InstallDownCallback.setOnInstallDownListener listener=new InstallDownCallback.setOnInstallDownListener() {
            @Override
            public void onProgress() {

            }

            @Override
            public void onDrawFile() {

            }

            @Override
            public void onSuccess() {

            }

            @Override
            public void onError() {

            }
        };
    }

    @Override
    public int getItemCount() {
        if (listData==null){
            return 0;
        }
        return listData.size();
    }

    @Override
    public long getItemId(int position) {
        return super.getItemId(position);
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    class ShopHolder extends RecyclerView.ViewHolder {
        ImageView appListItemIvLogo;
        ImageView appListItemIvIconOnline;
        Guideline guideline2;
        TextView appListItemTvTitle;
        RatingBar rating;
        TextView appListItemTvType;
        TextView appListItemTvAreaType;
        TextView appListItemTvInstall;
        TextView appListItemTvOpen;
        TextView appListItemTvUpgrade;
        ProgressBar appListItemProgressBar;
        TextView appListItemTvProgress;
        Group appListItemProgressGroup;
        public ShopHolder(@NonNull View itemView) {
            super(itemView);
            appListItemIvLogo=itemView.findViewById(R.id.appListItemIvLogo);
            appListItemIvIconOnline=itemView.findViewById(R.id.appListItemIvIconOnline);
            guideline2=itemView.findViewById(R.id.guideline2);
            appListItemTvTitle=itemView.findViewById(R.id.appListItemTvTitle);
            rating=itemView.findViewById(R.id.rating);
            appListItemTvType=itemView.findViewById(R.id.appListItemTvType);
            appListItemTvAreaType=itemView.findViewById(R.id.appListItemTvAreaType);
            appListItemTvInstall=itemView.findViewById(R.id.appListItemTvInstall);
            appListItemTvOpen=itemView.findViewById(R.id.appListItemTvOpen);
            appListItemTvUpgrade=itemView.findViewById(R.id.appListItemTvUpgrade);
            appListItemProgressBar=itemView.findViewById(R.id.appListItemProgressBar);
            appListItemTvProgress=itemView.findViewById(R.id.appListItemTvProgress);
            appListItemProgressGroup=itemView.findViewById(R.id.appListItemProgressGroup);
        }
    }

    public interface OnItemClickListener{
        void onItemClick(MainResultData item,int position);
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }
}
