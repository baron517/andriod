package com.example.administrator.monitor;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import layout.page.DeviceListActivity;


public class LoginActivity extends AppCompatActivity {

    private WebView webView;
    private Button mBtn1;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.w("test","hahahaha");
        System.out.println("hahahaha");
        setContentView(R.layout.activity_main);
        init();
        Log.w("###","hahahaha");
        System.out.println("hahahaha");





    }

    @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    private void init(){

        webView = (WebView) findViewById(R.id.webview);
        /*mBtn1 = (Button) findViewById(R.id.btn_1);*/
        mContext = getApplicationContext();

        //设置编码
        webView.getSettings().setDefaultTextEncodingName("utf-8");
        //支持js
        webView.getSettings().setJavaScriptEnabled(true);
        //设置背景颜色 透明
        webView.setBackgroundColor(Color.argb(0, 0, 0, 0));
        //设置本地调用对象及其接口
        webView.addJavascriptInterface(new JavaScriptObject(mContext), "myObj");


        //WebView加载web资源

        //webView.loadUrl("file:///android_asset/index.html");
        webView.loadUrl("http://monitor.pro.youzewang.com/app/assets/deviceList.html");

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

        //点击调用js中方法
       /* mBtn1.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                webView.loadUrl("javascript:funFromjs()");
                Toast.makeText(mContext, "调用javascript:funFromjs()", Toast.LENGTH_LONG).show();

                System.out.println("hahahaha");

            }
        });*/



    }



    public class JavaScriptObject {
        Context mContxt;

        public JavaScriptObject(Context mContxt) {
            this.mContxt = mContxt;
        }

        @JavascriptInterface
        public void fun1FromAndroid(String name) {
            Toast.makeText(mContxt, name, Toast.LENGTH_LONG).show();

            // 给bnt1添加点击响应事件
            Intent intent =new Intent(mContxt,DeviceListActivity.class);
            //启动
            startActivity(intent);

        }

        public void fun2(String name) {
            Log.d("test","hahahaha");
            Toast.makeText(mContxt, "调用fun2:" + name, Toast.LENGTH_SHORT).show();
        }
    }


}
