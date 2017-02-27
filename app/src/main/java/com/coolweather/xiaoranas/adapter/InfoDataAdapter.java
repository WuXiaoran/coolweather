package com.coolweather.xiaoranas.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.coolweather.xiaoranas.R;
import com.coolweather.xiaoranas.entity.InfoListData;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */

public class InfoDataAdapter extends RecyclerView.Adapter<InfoDataAdapter.ViewHolder>{

    private Context mContext;
    private List<InfoListData> mLists;

    public InfoDataAdapter(Context mContext, List<InfoListData> mLists){
        this.mContext = mContext;
        this.mLists = mLists;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView item_infomation_title;
        TextView item_infomation_source;
        TextView item_infomation_reply_count;
        TextView item_infomation_edit_time;
        ImageView item_infomation_top_image;

        public ViewHolder(View view){
            super(view);
            item_infomation_title = (TextView) view.findViewById(R.id.item_infomation_title);
            item_infomation_source = (TextView) view.findViewById(R.id.item_infomation_source);
            item_infomation_reply_count = (TextView) view.findViewById(R.id.item_infomation_reply_count);
            item_infomation_edit_time = (TextView) view.findViewById(R.id.item_infomation_edit_time);
            item_infomation_top_image = (ImageView) view.findViewById(R.id.item_infomation_top_image);
        }
    }


    @Override
    public InfoDataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mContext == null){
            mContext = parent.getContext();
        }

        View view =  LayoutInflater.from(mContext).inflate(R.layout.item_adapter_infomation,parent,false);
        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        InfoListData data = mLists.get(position);
        holder.item_infomation_title.setText(data.getTitle());
        holder.item_infomation_source.setText(data.getSource());
        holder.item_infomation_reply_count.setText(data.getReply_count());
        holder.item_infomation_edit_time.setText(data.getEdit_time());
        Glide.with(mContext).load(data.getTop_image()).into(holder.item_infomation_top_image);
    }



    @Override
    public int getItemCount() {
        return mLists.size();
    }
}
