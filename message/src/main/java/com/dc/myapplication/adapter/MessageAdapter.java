package com.dc.myapplication.adapter;

import android.content.Context;
import android.os.Handler;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.dc.myapplication.R;
import com.dc.myapplication.bean.MessageResultData;
import com.dc.myapplication.weight.IMGRigTopPointView;
import com.dc.myapplication.weight.IMGUsedRigTopPointView;

import java.util.List;


public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MessageHolder> {
    private Context mContext;
    private List<MessageResultData> messageData;
    private MessageOnItemClickListener mOnItemClickListener;
    public static int selectedCount=-1;

    //用来记录选中状态值，在填充数据时用来循环判断
    private SparseBooleanArray mCheck = new SparseBooleanArray();

    private String resourceName="";
    /**
     * 适配器有参构造
     * @param context
     * @param messageData
     */
    public MessageAdapter(Context context, List<MessageResultData> messageData, Handler handler){
        this.mContext=context;
        this.messageData=messageData;
        for (MessageResultData data:messageData){
            data.setSelected(false);
        }
    }

    @Override
    public MessageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View contentView= LayoutInflater.from(mContext).inflate(R.layout.msg_message_listitem,parent,false);
        return new MessageHolder(contentView);
    }

    @Override
    public void onBindViewHolder(final MessageHolder holder, final int position) {
        final MessageResultData item=messageData.get(position);

        if (messageData.get(position).isEdit()){
            holder.shop_Msg_RadioGroup.setVisibility(View.VISIBLE);
            if (messageData.get(position).isSelected()){
                holder.shop_Msg_RadioGroup.clearCheck();
            }
            holder.shop_Msg_RadioButton.setChecked(messageData.get(position).isSelected());
            holder.shop_Msg_RadioButton.setSelected(messageData.get(position).isSelected());
        }else {
            holder.shop_Msg_RadioGroup.setVisibility(View.GONE);
        }
        holder.shop_Msg_Title.setText(item.getTitle());
        holder.shop_Msg_Time.setText(item.getIntime());

        if (item.getMsgType()!=null){
            holder.shop_Msg_MsgType.setVisibility(View.VISIBLE);
            holder.shop_Msg_MsgType.setText("day");
            holder.shop_Msg_JsonContent.setText("[图文]");
        }else {
            holder.shop_Msg_MsgType.setVisibility(View.GONE);
            holder.shop_Msg_JsonContent.setText(item.getJsonContent());
        }

        //当前已读未读
        if (item.getIsRead()==0){
            holder.shop_Msg_Image.setPointMode(IMGRigTopPointView.ONLY_POINT);
            holder.shop_Msg_Image.setHaveMesage(true);
        }else {
            holder.shop_Msg_Image.setPointMode(IMGRigTopPointView.NO_POINT);
            holder.shop_Msg_Image.setHaveMesage(true);
        }

        holder.shop_Msg_msgArg0.setText(item.getMsgType());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!holder.shop_Msg_RadioButton.isSelected()){
                    holder.shop_Msg_RadioGroup.clearCheck();
                    holder.shop_Msg_RadioButton.setChecked(true);
                    holder.shop_Msg_RadioButton.setSelected(true);
                    messageData.get(position).setSelected(true);
                    selectedCount++;
//                    Log.d("fdsf ",holder.shop_Msg_RadioButton.isSelected()+"添加选中");
                }else {
                    holder.shop_Msg_RadioGroup.clearCheck();
                    holder.shop_Msg_RadioButton.setChecked(false);
                    holder.shop_Msg_RadioButton.setSelected(false);
                    messageData.get(position).setSelected(false);
                    selectedCount--;
//                    Log.d("fdsf ",holder.shop_Msg_RadioButton.isSelected()+"移除选中");
                }


                if (!messageData.get(position).isEdit()){
                    if(item.isRead==0) {
                        item.isRead=1;
                        messageData.get(position).setIsRead(1);
                    }
                }
                if (mOnItemClickListener!=null){

                    mOnItemClickListener.onItemClick(position,item,true,true);

                    if (selectedCount==messageData.size()-1){
                        mOnItemClickListener.onItemRadioButton(true,messageData);
                    }else {
                        mOnItemClickListener.onItemRadioButton(false,messageData);
                    }
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return messageData.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    class MessageHolder extends RecyclerView.ViewHolder{
        private RadioGroup shop_Msg_RadioGroup;
        private RadioButton shop_Msg_RadioButton;
//        private ImageView shop_Msg_Icon;
        private IMGRigTopPointView shop_Msg_Image;
        private TextView shop_Msg_Title;
        private TextView shop_Msg_msgArg0;
        private TextView shop_Msg_JsonContent;
        private TextView shop_Msg_MsgType;
        private TextView shop_Msg_Time;
        private ImageView shopMsgOnLine;
        private RelativeLayout shopMsgImageLayout;
        private IMGUsedRigTopPointView shop_Msg_Image1;
        public MessageHolder(View itemView) {
            super(itemView);
            shop_Msg_RadioGroup=itemView.findViewById(R.id.shop_Msg_RadioGroup);
            shop_Msg_RadioButton=itemView.findViewById(R.id.shop_Msg_RadioButton);
            shop_Msg_Image=itemView.findViewById(R.id.shop_Msg_Image);
            shop_Msg_Title=itemView.findViewById(R.id.shop_Msg_Title);
            shop_Msg_MsgType=itemView.findViewById(R.id.shop_Msg_MsgType);
            shop_Msg_msgArg0=itemView.findViewById(R.id.shop_Msg_msgArg0);
            shop_Msg_JsonContent=itemView.findViewById(R.id.shop_Msg_JsonContent);
            shop_Msg_Time=itemView.findViewById(R.id.shop_Msg_Time);
            shopMsgOnLine=itemView.findViewById(R.id.shopMsgOnLine);
            shopMsgImageLayout=itemView.findViewById(R.id.shopMsgImageLayout);
            shop_Msg_Image1=itemView.findViewById(R.id.shop_Msg_Image1);
        }
    }

    public interface MessageOnItemClickListener{
        void onItemClick(int mPosition, MessageResultData appItem, boolean isPlugin, boolean isRead);
        void onItemRadioButton(boolean isAllSelected, List<MessageResultData> mMessageData);
    }

    public void setMessageOnItemClickListener(MessageOnItemClickListener onItemClickListener){
        this.mOnItemClickListener=onItemClickListener;
    }
}
