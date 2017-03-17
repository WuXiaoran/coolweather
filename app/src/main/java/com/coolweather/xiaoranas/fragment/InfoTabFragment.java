package com.coolweather.xiaoranas.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.coolweather.xiaoranas.R;
import com.coolweather.xiaoranas.activity.DetailsNewActivity;
import com.coolweather.xiaoranas.adapter.InfoDataAdapter;
import com.coolweather.xiaoranas.entity.InfoListData;
import com.coolweather.xiaoranas.gson.News;
import com.coolweather.xiaoranas.interfaces.OnItemClickLitener;
import com.coolweather.xiaoranas.url.URLAdress;
import com.coolweather.xiaoranas.utils.HttpUtil;
import com.coolweather.xiaoranas.utils.ToastUtils;
import com.coolweather.xiaoranas.utils.Utility;
import com.coolweather.xiaoranas.view.DividerItemDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;


/**
 * Created by Administrator on 2017/2/27.
 */

public class InfoTabFragment extends Fragment {

    public static final String ARGUMENT = "argument";
    public static final String DATAKEY = "datakey";
    public static final String URL = "url";
    public static final String IMAGE = "image";
    public static final String TITLE = "title";
    private News newData;
    public Toast toast = null;
    private RecyclerView recyclerView;
    private List<InfoListData> lists = new ArrayList<>();
    private InfoDataAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private int mArgument;
    private String tabDataKey;

    /**
     * 传入需要的参数，设置给argument
     *
     * @param argument
     * @return
     */
    public static InfoTabFragment getInstance(int argument,String dataKey) {
        Bundle bundle = new Bundle();
        bundle.putInt(ARGUMENT, argument);
        bundle.putString(DATAKEY,dataKey);
        InfoTabFragment infoTabFragment = new InfoTabFragment();
        infoTabFragment.setArguments(bundle);
        return infoTabFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getArguments();
        if (bundle != null) {
            mArgument = bundle.getInt(ARGUMENT);
            tabDataKey = bundle.getString(DATAKEY);
            Log.e("key",tabDataKey);
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.view_fragment_recycler, null);
        initUI(view);
        adapter = new InfoDataAdapter(lists);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,R.color.colorAccent,R.color.colorPrimaryDark,R.color.refresh,R.color.cardview_shadow_end_color);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                initInfomation();
            }
        });
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setAdapter(adapter);
        swipeRefreshLayout.setRefreshing(true);
        initInfomation();
        adapter.setmOnItemClickLitener(new OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent intent = new Intent(getActivity(), DetailsNewActivity.class);
                intent.putExtra(URL,newData.result.data.get(position).url);
                intent.putExtra(IMAGE,newData.result.data.get(position).thumbnail_pic_s);
                intent.putExtra(TITLE,newData.result.data.get(position).author_name);
                startActivity(intent);
            }

            @Override
            public void onItemLongClick(View view, int position) {
                Toast.makeText(getActivity(),"长按",Toast.LENGTH_SHORT).show();
            }
        });
        return view;
    }

    /**
     * 初始化视图
     */
    private void initUI(View view){
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
    }

    /**
     * 初始化资讯数据
     */
    public void initInfomation() {
        String url = URLAdress.NewsUrl + tabDataKey + URLAdress.NewsKey;
        HttpUtil.sendOkHttpRequest(url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtils.ShowToast(getActivity(), toast, "获取数据失败！");
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();
                newData = Utility.handleGetNEWSResponse(data);
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if (newData != null && "成功的返回".equals(newData.reason)) {
                            lists.clear();
                            for (int i = 0; i < newData.result.data.size(); i++) {
                                if (null == newData.result.data.get(i).thumbnail_pic_s03){
                                    String title = newData.result.data.get(i).title;
                                    String image = newData.result.data.get(i).thumbnail_pic_s;
                                    String source = newData.result.data.get(i).author_name;
                                    String edit_time = newData.result.data.get(i).date;
                                    InfoListData d = new InfoListData(title, image, source,
                                            edit_time);
                                    lists.add(d);
                                }else{
                                    String title = newData.result.data.get(i).title;
                                    String image = newData.result.data.get(i).thumbnail_pic_s;
                                    String image2 = newData.result.data.get(i).thumbnail_pic_s02;
                                    String image3 = newData.result.data.get(i).thumbnail_pic_s03;
                                    String source = newData.result.data.get(i).author_name;
                                    String edit_time = newData.result.data.get(i).date;
                                    InfoListData d = new InfoListData(title, image, source,
                                            edit_time,image2,image3);
                                    lists.add(d);
                                }
                                if (adapter != null){
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        } else {
                            ToastUtils.ShowToast(getActivity(), toast, "获取数据失败！");
                        }
                        swipeRefreshLayout.setRefreshing(false);
                    }
                });

            }
        });
    }

    @Override
    public void setMenuVisibility(boolean menuVisible) {
        super.setMenuVisibility(menuVisible);
        if (this.getView() != null){
            this.getView().setVisibility(menuVisible ? View.VISIBLE : View.GONE);
        }
    }
}

