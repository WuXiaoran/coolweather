package com.coolweather.xiaoranas.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.coolweather.xiaoranas.R;
import com.coolweather.xiaoranas.adapter.InfoDataAdapter;
import com.coolweather.xiaoranas.entity.InfoListData;
import com.coolweather.xiaoranas.gson.Get_NEWS;
import com.coolweather.xiaoranas.utils.HttpUtil;
import com.coolweather.xiaoranas.utils.ToastUtils;
import com.coolweather.xiaoranas.utils.Utility;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

/**
 * Created by Administrator on 2017/2/24.
 */

public class MainActivity extends AppCompatActivity {


    public static final String Single_NewsUrl = "http://api.dagoogle.cn/news/get-news?tableNum=1&pagesize=8";
    public Toast toast = null;

    /*
     * view
     */
    private Toolbar toolbar;
    private RecyclerView recycler_view;
    private TabLayout tabLayout;



    private List<InfoListData> lists = new ArrayList<>();
    private InfoDataAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();
        initInfomation();
    }

    /**
     * 初始化控件
     */
    private void initUI() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(linearLayoutManager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.addTab(tabLayout.newTab().setText("dd"));
        tabLayout.addTab(tabLayout.newTab().setText("dd"));
        tabLayout.addTab(tabLayout.newTab().setText("dd"));
        tabLayout.addTab(tabLayout.newTab().setText("dd"));
        tabLayout.addTab(tabLayout.newTab().setText("dd"));
        tabLayout.addTab(tabLayout.newTab().setText("dd"));
        tabLayout.addTab(tabLayout.newTab().setText("dd"));
        tabLayout.addTab(tabLayout.newTab().setText("dd"));
        tabLayout.addTab(tabLayout.newTab().setText("dd"));
        tabLayout.addTab(tabLayout.newTab().setText("dd"));
        tabLayout.addTab(tabLayout.newTab().setText("dd"));
    }

    /**
     * 初始化资讯数据
     */
    private void initInfomation() {

        HttpUtil.sendOkHttpRequest(Single_NewsUrl, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.ShowToast(MainActivity.this,toast,"获取数据失败！");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String data = response.body().string();
                final Get_NEWS newData = Utility.handleGetNEWSResponse(data);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newData != null && 200 == newData.status){
                            for (int i = 0; i < newData.data.size();i++){
                                String title = newData.data.get(i).title;
                                String image = newData.data.get(i).top_image;
                                String source = newData.data.get(i).source;
                                String reply_count = newData.data.get(i).reply_count;
                                String edit_time = newData.data.get(i).edit_time;
                                InfoListData d = new InfoListData(title,image,source,
                                        reply_count,edit_time);
                                lists.add(d);
                            }
                            adapter = new InfoDataAdapter(MainActivity.this,lists);
                            recycler_view.setAdapter(adapter);

                        }else{
                            ToastUtils.ShowToast(MainActivity.this,toast,"获取数据失败！");
                        }
                    }
                });

            }
        });


    }
}
