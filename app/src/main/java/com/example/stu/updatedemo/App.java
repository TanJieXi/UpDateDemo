package com.example.stu.updatedemo;

import android.app.Application;
import android.support.multidex.MultiDex;

import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.PlatformConfig;

/**
 * Created by TanJieXi on 2018/9/13.
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        UMConfigure.init(
                this,"5a12384aa40fa3551f0001d1"
                ,"umeng",UMConfigure.DEVICE_TYPE_PHONE,"");//58edcfeb310c93091c000be2 5965ee00734be40b580001a0
        PlatformConfig.setWeixin("wx278ad6d565532a69",
                "b2a7506c082615681491e9e1525d67dd");
        // 新浪微博 appkey appsecret
        /*PlatformConfig.setSinaWeibo("1967641093",
                "4a06d7495c1a5e36823f54b88b178c49");*/
        // QQ和Qzone appid appkey
        PlatformConfig.setQQZone("1104930836", "7JstVZCT2YOv9enU");


        MultiDex.install(this);

    }
}
