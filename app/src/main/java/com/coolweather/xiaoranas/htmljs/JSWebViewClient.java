package com.coolweather.xiaoranas.htmljs;

import android.content.Context;
import android.graphics.Bitmap;
import android.webkit.WebResourceResponse;
import android.webkit.WebView;
import android.webkit.WebViewClient;

/**
 * Created by Administrator on 2017/3/1.
 * 网络JavaScript监听
 */

public class JSWebViewClient extends WebViewClient {

    private Context mContext;
    private WebView mWebView;
    private String urlpath;

    public JSWebViewClient( Context context,WebView webView,String url) {
        this.mContext = context;
        this.mWebView =webView;
        this.urlpath = url;
    }

    @Override
    public boolean shouldOverrideUrlLoading(WebView view, String url) {
        view.loadUrl(url);
        return true;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
        url= url.toLowerCase();
        if(!url.contains(urlpath)){
            if(!ADFilterTool.hasAd(mContext,url)){
                return  super.shouldInterceptRequest(view,url);
            }else{
                return  new WebResourceResponse(null,null,null);
            }
        }else{
            return  super.shouldInterceptRequest(view,url);
        }
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        // TODO Auto-generated method stub
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {
        super.onPageFinished(view, url);
        addImageClickListner();
    }

    @Override
    public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {

        super.onReceivedError(view, errorCode, description, failingUrl);
    }

    private void addImageClickListner() {

        mWebView.loadUrl("javascript:(function(){" +
                "var objs = document.getElementsByClassName(\"img\"); " +
                "for(var i=0;i<objs.length;i++)  " +
                "{"
                + "    objs[i].onclick=function()  " +
                "    {  "
                + "        window.imagelist.openImage(this.src);  " +
                "    }  " +
                "}" +
                "})()");
    }
}
