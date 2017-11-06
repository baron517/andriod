package com.ezuikit.open;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.ezvizuikit.open.EZUIPlayer;

import static com.ezuikit.open.PlayActivity.APPKEY;
import static com.ezuikit.open.PlayActivity.AccessToekn;
import static com.ezuikit.open.PlayActivity.Global_AreanDomain;
import static com.ezuikit.open.PlayActivity.PLAY_URL;


public class DeviceListActivity extends AppCompatActivity {

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

        webView.loadUrl(serve_url+"deviceList.html");

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
            public void modifyPass() {
                Intent intent =new Intent(mContxt,ModifyPassActivity.class);
                //启动
                startActivity(intent);
            }


            @JavascriptInterface
            public void fun1FromAndroid(String url) {

                Context ctx = DeviceListActivity.this;
                SharedPreferences sp = ctx.getSharedPreferences("videoParam", MODE_PRIVATE);
                //存入数据
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("mUrl", url);
                editor.commit();


                Log.w("参数mAppKey", sp.getString("mAppKey", "none"));
                Log.w("参数mAccessToken", sp.getString("mAccessToken", "none"));
                Log.w("参数mUrl", sp.getString("mUrl", "none"));

                String mAppKey = sp.getString("mAppKey", "none");
                String mAccessToken = sp.getString("mAccessToken", "none");
                String mUrl = sp.getString("mUrl", "none");

                //String mGlobalAreaDomain = mGlobalAreanDoaminEditText.getText().toString().trim();


                //Log.w("参数1", mGlobalAreaDomain);

                if (TextUtils.isEmpty(mAppKey)){
                    Toast.makeText(ctx,"appkey can not be null",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(mAccessToken)){
                    Toast.makeText(ctx,"accesstoken can not be null",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(mUrl)){
                    Toast.makeText(ctx,"url can not be null",Toast.LENGTH_LONG).show();
                    return;
                }
              /*  if (isGlobal){
                    if (TextUtils.isEmpty(mGlobalAreaDomain)){
                        Toast.makeText(this,"AreaDomain can not be null",Toast.LENGTH_LONG).show();
                        return;
                    }
                }*/


                EZUIPlayer.EZUIKitPlayMode mode = null;
                mode = EZUIPlayer.getUrlPlayType(mUrl);
                if (mode == EZUIPlayer.EZUIKitPlayMode.EZUIKIT_PLAYMODE_LIVE) {
                  /*  //直播预览
                    if (isGlobal) {

                        //启动播放页面
                        PlayActivity.startPlayActivityGlobal(this, mAppKey, mAccessToken, mUrl, mGlobalAreaDomain);
                        //应用内只能初始化一次，当首次选择了国内或者海外版本，并点击进入预览回放，此时不能再进行国内海外切换
                        return;
                    }*/

                    //启动播放页面
                    PlayActivity.startPlayActivity(ctx, mAppKey, mAccessToken, mUrl);
                }









            }

            @JavascriptInterface
            public void saveAppData(String mAppKey,String mAccessToken) {


                //获取SharedPreferences对象
                Context ctx = DeviceListActivity.this;
                SharedPreferences sp = ctx.getSharedPreferences("videoParam", MODE_PRIVATE);
                //存入数据
                SharedPreferences.Editor editor = sp.edit();
                editor.putString("mAppKey", mAppKey);
                editor.putString("mAccessToken", mAccessToken);
                editor.commit();

                Log.w("参数", sp.getString("mAppKey", "none"));
                Log.w("参数", sp.getString("mAccessToken", "none"));



            }



            @JavascriptInterface
            public String getUserInfo()
            {
                Context ctx = DeviceListActivity.this;
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
