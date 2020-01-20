package com.dc.shopapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dc.shopapp.R;

import java.util.List;

import butterknife.BindView;


public class SearchHistoryRvAdapter extends RecyclerView.Adapter<SearchHistoryRvAdapter.SearchHistoryHolder> {
    private Context mContext;
    private List<String> mList;

    public SearchHistoryRvAdapter(Context context, List<String> list) {
        mContext = context;
        mList = list;
    }

    public void setList(List<String> list) {
        mList = list;
        notifyDataSetChanged();
    }

    @Override
    public SearchHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.shop_search_history, parent, false);
        return new SearchHistoryHolder(view);
    }

    @Override
    public void onBindViewHolder(final SearchHistoryHolder holder, int position) {
//        根据需求制定事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
    }

    @Override
    public int getItemCount() {
        return null == mList ? 0 : mList.size();
    }

    class SearchHistoryHolder extends RecyclerView.ViewHolder {
        ImageView itemHistoryIvIcon;
        TextView itemHistoryTvText;
        ImageView itemHistoryIvDelete;
        View itemHistoryDivider;
        public SearchHistoryHolder(@NonNull View itemView) {
            super(itemView);
            itemHistoryIvIcon=itemView.findViewById(R.id.itemHistoryIvIcon);
            itemHistoryTvText=itemView.findViewById(R.id.itemHistoryTvText);
            itemHistoryIvDelete=itemView.findViewById(R.id.itemHistoryIvDelete);
            itemHistoryDivider=itemView.findViewById(R.id.itemHistoryDivider);
        }
    }
}
