package com.coolweather.xiaoranas.Activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.coolweather.android.R;
import com.coolweather.xiaoranas.service.AutoUpdataService;

import java.util.List;

/**
 * Created by Administrator on 2017/2/24.
 */

public class SettingActivity extends AppCompatActivity{

    private RelativeLayout setting_layout;
    private Button nav_button;
    private TextView textTheme;
    private TextView textUpdateTime;
    private Switch switch_updatedata;
    private List<ActivityManager.RunningServiceInfo> service;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        init();
        setting_layout.setBackgroundResource(R.color.colorPrimary);
        nav_button.setVisibility(View.INVISIBLE);
        textTheme.setText("设置");
        textUpdateTime.setVisibility(View.INVISIBLE);
        if (isWeatherServiceRunning()){
            switch_updatedata.setChecked(true);
        }else{
            switch_updatedata.setChecked(false);
        }
        switch_updatedata.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Intent intent = new Intent(SettingActivity.this, AutoUpdataService.class);
                    startService(intent);
                    Toast.makeText(SettingActivity.this,"开启自动获取天气信息",Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(SettingActivity.this, AutoUpdataService.class);
                    stopService(intent);
                    Toast.makeText(SettingActivity.this,"关闭自动获取天气信息",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 初始化控件
     */
    private void init(){
        setting_layout = (RelativeLayout) findViewById(R.id.setting_layout);
        nav_button = (Button) findViewById(R.id.nav_button);
        textTheme = (TextView) findViewById(R.id.title_theme);
        textUpdateTime = (TextView) findViewById(R.id.title_update_time);
        switch_updatedata = (Switch) findViewById(R.id.switch_updatedata);
    }

    /**
     * 查看天气后台是否开启
     */
    private boolean isWeatherServiceRunning() {
        ActivityManager manager = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        for (ActivityManager.RunningServiceInfo service : manager.getRunningServices(Integer.MAX_VALUE)) {
            if (AutoUpdataService.ServiceUrl.equals(service.service.getClassName())) {
                Log.d("AutoUpdataService",AutoUpdataService.ServiceUrl);
                Log.d("AutoUpdataService1",service.service.getClassName() + "");
                return true;
            }
        }
        return false;
    }
}
