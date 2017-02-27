package com.coolweather.xiaoranas.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.coolweather.xiaoranas.R;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Administrator on 2017/2/24.
 */

public class MainToolbar extends Toolbar implements View.OnClickListener{

    private View mView;
    private de.hdodenhof.circleimageview.CircleImageView circleImageView;
    private TextView mTitleText;

    public MainToolbar(Context context) {
        super(context);
    }

    public MainToolbar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public MainToolbar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    /**
     * 加载视图
     */
    private void init() {
        if (mView == null){
            mView = LayoutInflater.from(getContext()).inflate(R.layout.toolbar,null);
            circleImageView = (CircleImageView) mView.findViewById(R.id.toolbar_peopeliv);
            mTitleText = (TextView) mView.findViewById(R.id.toolbar_title);
            circleImageView.setOnClickListener(this);
            LayoutParams lp = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT,
                    Gravity.CENTER_HORIZONTAL);
            addView(mView,lp);
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolbar_peopeliv:
                Toast.makeText(getContext(),"click",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
