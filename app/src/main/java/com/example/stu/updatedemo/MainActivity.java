package com.example.stu.updatedemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btn_QQ, btn_weixin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_QQ = findViewById(R.id.btn_QQ);
        btn_weixin = findViewById(R.id.btn_weixin);
        btn_QQ.setOnClickListener(this);
        btn_weixin.setOnClickListener(this);
    }

    //QQ分享加的
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);
    }

    public void btnShare(View view) {
        UMImage image = new UMImage(MainActivity.this, R.mipmap.ic_launcher);//网络图片

        UMWeb web = new UMWeb("http://www.baidu.com");
        web.setTitle("This is music title");//标题
        web.setThumb(image);  //缩略图
        web.setDescription("my description");//描述
        new ShareAction(MainActivity.this)
                .withMedia(web)
                .withText("hello")
                .setDisplayList(SHARE_MEDIA.QQ, SHARE_MEDIA.QZONE, SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Log.i("dsfdsfgds", "onStart");
                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Log.i("dsfdsfgds", "onResult");
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Log.i("dsfdsfgds", "onError" + throwable.toString());
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        Log.i("dsfdsfgds", "onCancel");
                    }
                }).open();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_QQ:
                authSan(SHARE_MEDIA.QQ);
                break;
            case R.id.btn_weixin:
                authSan(SHARE_MEDIA.WEIXIN);
                break;
            default:
                break;
        }
    }

    public void authSan(SHARE_MEDIA a){
        UMShareAPI.get(this).deleteOauth(this, a, new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {

            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {

            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {

            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {

            }
        });
      UMShareAPI.get(this).getPlatformInfo(this,a , new UMAuthListener() {
            @Override
            public void onStart(SHARE_MEDIA share_media) {
                Log.i("dsfdsfgds","onStart");
            }

            @Override
            public void onComplete(SHARE_MEDIA share_media, int i, Map<String, String> map) {
                Log.i("dsfdsfgds","onComplete" + map.toString()) ;
            }

            @Override
            public void onError(SHARE_MEDIA share_media, int i, Throwable throwable) {
                Log.i("dsfdsfgds","onError" + throwable.getMessage());
            }

            @Override
            public void onCancel(SHARE_MEDIA share_media, int i) {
                Log.i("dsfdsfgds","onCancel");
            }
        });
    }
}
