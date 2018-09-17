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
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        PlatformConfig.setQQZone("100424468", "c7394704798a158208a74ab60104f0ba");


        MultiDex.install(this);

    }
}
