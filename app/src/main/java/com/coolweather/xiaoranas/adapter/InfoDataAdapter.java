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
import com.coolweather.xiaoranas.interfaces.OnItemClickLitener;

import java.util.List;

/**
 * Created by Administrator on 2017/2/27.
 */

public class InfoDataAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    /*
     * 两种item布局
     */
    public static enum ITEM_TYPE{
        ITEM_TYPE_IAMGE,
        ITEM_TYPE_IAMGES;
    }

    /**
     *  开放点击接口
     */
    private OnItemClickLitener mOnItemClickLitener;

    public void setmOnItemClickLitener(OnItemClickLitener mOnItemClickLitener){
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

    private Context mContext;
    private List<InfoListData> mLists;

    public InfoDataAdapter( List<InfoListData> mLists){
        this.mLists = mLists;
    }

    /**
     * 一张图片的item
     */
    static class ImageViewHolder extends RecyclerView.ViewHolder{
        TextView item_infomation_title;
        TextView item_infomation_source;
        TextView item_infomation_edit_time;
        ImageView item_infomation_top_image;

        public ImageViewHolder(View view){
            super(view);
            item_infomation_title = (TextView) view.findViewById(R.id.item_infomation_title);
            item_infomation_source = (TextView) view.findViewById(R.id.item_infomation_source);
            item_infomation_edit_time = (TextView) view.findViewById(R.id.item_infomation_edit_time);
            item_infomation_top_image = (ImageView) view.findViewById(R.id.item_infomation_top_image);
        }
    }

    /**s
     * 三张图片的item
     */
    static class ImagesViewHolder extends RecyclerView.ViewHolder{
        TextView item_infomation_title;
        TextView item_infomation_source;
        TextView item_infomation_edit_time;
        ImageView item_infomation_image1;
        ImageView item_infomation_image2;
        ImageView item_infomation_image3;

        public ImagesViewHolder(View view){
            super(view);
            item_infomation_title = (TextView) view.findViewById(R.id.item_infomation_image3_title);
            item_infomation_source = (TextView) view.findViewById(R.id.item_infomation_image3_source);
            item_infomation_edit_time = (TextView) view.findViewById(R.id.item_infomation_image3_edit_time);
            item_infomation_image1 = (ImageView) view.findViewById(R.id.item_infomation_image3_01);
            item_infomation_image2 = (ImageView) view.findViewById(R.id.item_infomation_image3_02);
            item_infomation_image3 = (ImageView) view.findViewById(R.id.item_infomation_image3_03);
        }
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (mContext == null){
            mContext = parent.getContext();
        }
        if (viewType == ITEM_TYPE.ITEM_TYPE_IAMGE.ordinal()){
            View view =  LayoutInflater.from(mContext).inflate(R.layout.item_adapter_infomation,parent,false);
            return new ImageViewHolder(view);
        }else if (viewType == ITEM_TYPE.ITEM_TYPE_IAMGES.ordinal()){
            View view =  LayoutInflater.from(mContext).inflate(R.layout.item_adapter_infomation_image3,parent,false);
            return new ImagesViewHolder(view);
        }
        return null;
    }


    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        InfoListData data = mLists.get(position);
        if (holder instanceof ImageViewHolder){
            ((ImageViewHolder)holder).item_infomation_title.setText(data.getTitle());
            ((ImageViewHolder)holder).item_infomation_source.setText(data.getSource());
            ((ImageViewHolder)holder).item_infomation_edit_time.setText(data.getEdit_time());
            Glide.with(mContext).load(data.getTop_image()).into(((ImageViewHolder)holder).item_infomation_top_image);
        }else if (holder instanceof  ImagesViewHolder){
            ((ImagesViewHolder)holder).item_infomation_title.setText(data.getTitle());
            ((ImagesViewHolder)holder).item_infomation_source.setText(data.getSource());
            ((ImagesViewHolder)holder).item_infomation_edit_time.setText(data.getEdit_time());
            Glide.with(mContext).load(data.getTop_image()).into(((ImagesViewHolder)holder).item_infomation_image1);
            Glide.with(mContext).load(data.getImage_1()).into(((ImagesViewHolder)holder).item_infomation_image2);
            Glide.with(mContext).load(data.getImage_2()).into(((ImagesViewHolder)holder).item_infomation_image3);
        }

        if (mOnItemClickLitener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemClick(holder.itemView,pos);
                }
            });
            holder.itemView.setOnLongClickListener(new View.OnLongClickListener(){

                @Override
                public boolean onLongClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickLitener.onItemLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return mLists.size();
    }

    @Override
    public int getItemViewType(int position) {
        return null == mLists.get(position).getImage_2() ? ITEM_TYPE.ITEM_TYPE_IAMGE.ordinal() : ITEM_TYPE.ITEM_TYPE_IAMGES.ordinal();
    }
}
