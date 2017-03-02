package com.coolweather.xiaoranas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;

import com.coolweather.xiaoranas.R;
import com.coolweather.xiaoranas.fragment.InfoTabFragment;
import com.coolweather.xiaoranas.htmljs.JSWebViewClient;
import com.coolweather.xiaoranas.interfaces.JavaScriptInterface;

/**
 * Created by Administrator on 2017/2/28.
 */

public class DetailsNewActivity extends AppCompatActivity {


    /*
     * view
     */
    private WebView webView;
    private ImageView imageView;
    private Toolbar toolbar;

    private String url;
    private String image;
    private String title;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailsnew);
        getIntentTag();
        initUI();
        initWebView();
        //记载webview数据
        webView.loadUrl(url);
    }

    /**
     * 获取intent数据
     */
    private void getIntentTag(){
        Intent intent = getIntent();
        url = intent.getStringExtra(InfoTabFragment.URL);
        image = intent.getStringExtra(InfoTabFragment.IMAGE);
        title = intent.getStringExtra(InfoTabFragment.TITLE);
    }
    /**
     * 初始化视图
     */
    private void initUI(){
        webView = (WebView) findViewById(R.id.webview);
        toolbar = (Toolbar) findViewById(R.id.toolbar_detailsnew);

        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        toolbar.setTitle(title);

    }

    /**
     * 初始化webview数据
     */
    private void initWebView() {

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAppCacheEnabled(true);//自主缓存
        settings.setDomStorageEnabled(true);//设置适应Html5的一些方法
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new JSWebViewClient(this,webView,url));
        webView.addJavascriptInterface(new JavaScriptInterface(this), "imagelist");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    //设置回退
    //覆盖Activity类的onKeyDown(int keyCoder,KeyEvent event)方法
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK) && webView.canGoBack()) {
            webView.goBack(); //goBack()表示返回WebView的上一页面
            return true;
        }
        return false;
    }
}
