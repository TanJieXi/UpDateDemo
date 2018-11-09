package com.example.stu.updatedemo.update;

import android.app.Activity;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.stu.updatedemo.App;
import com.vector.update_app.UpdateAppBean;
import com.vector.update_app.UpdateAppManager;
import com.vector.update_app.UpdateCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * Created by TanJieXi on 2018/8/30.
 */
public class UpDateAppUtils {
    private static volatile UpDateAppUtils sUpDateAppUtils = null;

    private UpDateAppUtils() {

    }

    public static UpDateAppUtils getInstance() {
        if (sUpDateAppUtils == null) {
            synchronized (UpDateAppUtils.class) {
                if (sUpDateAppUtils == null) {
                    sUpDateAppUtils = new UpDateAppUtils();
                }
            }
        }
        return sUpDateAppUtils;
    }



    /**
     * 软件升级安装包的保存路径
     */
    public  final String UODATE_APP_SAVE_PATH = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "com.lianyi.app" + File.separator + "app";
    public  final String UPDATE_VERSION_CODE = "pad_code"; //版本号
    public static final String UPDATE_VERSION_NAME = "pad_name"; //版本名
    public static final String UPDATE_VERSION_MESSAGE = "pad_cont";//更新内容
    public static final String UPDATE_PATH_URL = "pad_url"; //更新的地址

    public void upDataApp(final Activity activity , String url) {
        if (NetworkUtils.Available()) {
            new UpdateAppManager
                    .Builder()
                    //当前Activity
                    .setActivity(activity)
                    //更新地址
                    .setUpdateUrl(url)
                    //实现httpManager接口的对象
                    .setHttpManager(new OkGoUpdateHttpUtil())
                    //设置点击升级后，消失对话框，默认点击升级后，对话框显示下载进度
                    //设置apk下砸路径，默认是在下载到sd卡下/Download/1.0.0/test.apk
                    .setTargetPath(UODATE_APP_SAVE_PATH)
                    .build()
                    .checkNewApp(new UpdateCallback() {
                        @Override
                        protected UpdateAppBean parseJson(String json) {
                            UpdateAppBean updateAppBean = new UpdateAppBean();
                            int vercode = 1;
                            try {
                                Log.i("dfdasfgsdfg",json);
                                JSONObject jsonObject = new JSONObject(json);
                                String update = "Yes";
                                /*if (jsonObject.optLong("pad_url") > vercode) {
                                    update = "Yes";
                                }*/
                                updateAppBean
                                        //（必须）是否更新,No
                                        .setUpdate(update)
                                        //（必须）新版本名 1.1.0
                                        .setNewVersion(jsonObject.optString(UPDATE_VERSION_NAME))
                                        //（必须）下载地址
                                        .setApkFileUrl(jsonObject.optString(UPDATE_PATH_URL))
                                        //（必须）更新内容
                                        .setUpdateLog("\n" + (jsonObject.optString(UPDATE_VERSION_MESSAGE)) + "\n\n")
                                        //大小，不设置不显示大小，可以不设置
//                                            .setTargetSize(jsonObject.optString("target_size"))
                                        //是否强制更新，可以不设置
                                        .setConstraint(false);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                            return updateAppBean;
                        }

                        @Override
                        protected void hasNewApp(UpdateAppBean updateApp, UpdateAppManager updateAppManager) {
                            updateAppManager.showDialogFragment();
                        }



                        @Override
                        public void onBefore() {
                            CProgressDialogUtils.showProgressDialog(activity);
                        }



                        @Override
                        public void onAfter() {
                            CProgressDialogUtils.cancelProgressDialog(activity);
                        }

                    });
        } else {
            Toast.makeText(App.getmContext(),"请连接网络后重试",Toast.LENGTH_SHORT).show();
        }
    }

}
