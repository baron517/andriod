package com.ezuikit.open;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;


public class ModifyPassActivity extends AppCompatActivity {

    private WebView webView;
    private Button mBtn1;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        Log.w("test","hahahaha");
        System.out.println("hahahaha");
        setContentView(R.layout.activity_device_list);
        init();


    }

    @SuppressLint({ "JavascriptInterface", "SetJavaScriptEnabled" })
    private void init() {
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
        String serve_url=this.getString(R.string.serve_url);

        webView.loadUrl(serve_url+"modifyPass.html");

        //覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                // TODO Auto-generated method stub
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });

    }


        public class JavaScriptObject {
            Context mContxt;

            public JavaScriptObject(Context mContxt) {
                this.mContxt = mContxt;
            }

            @JavascriptInterface
            public void fun1FromAndroid() {


                // 给bnt1添加点击响应事件
                Intent intent =new Intent(mContxt,DeviceListActivity.class);
                //启动
                startActivity(intent);

            }

            @JavascriptInterface
            public String getUserInfo()
            {
                Context ctx = ModifyPassActivity.this;
                SharedPreferences sp = ctx.getSharedPreferences("taidun", MODE_PRIVATE);
                String userInfo=sp.getString("STRING_KEY", "none");
                Log.w("test", sp.getString("STRING_KEY", "none"));
                return userInfo;
            }


            @JavascriptInterface
            public void toast(String name) {
                Toast.makeText(mContxt, name, Toast.LENGTH_SHORT).show();
            }
        }




}
