package net.kehui.www.t_907_origin.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.wifi.WifiManager;
import android.os.Build;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.timmy.tdialog.TDialog;
import com.timmy.tdialog.base.BindViewHolder;
import com.timmy.tdialog.listener.OnBindViewListener;

import net.kehui.www.t_907_origin.ConnectService;
import net.kehui.www.t_907_origin.R;
import net.kehui.www.t_907_origin.application.Constant;
import net.kehui.www.t_907_origin.entity.ParamInfo;
import net.kehui.www.t_907_origin.util.MultiLanguageUtil;
import net.kehui.www.t_907_origin.util.StateUtils;
import net.kehui.www.t_907_origin.util.WifiUtil;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static java.lang.Thread.sleep;

/**
 * @author li.md
 * @date 19/06/27
 */
public class SplashActivity extends AppCompatActivity {

    private TDialog tDialog;
    private ProgressBar progressBar;
    private TextView tvProgress;
    private WifiManager WifiManager;
    //TODO 20191218申请动态权限
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            "android.permission.READ_EXTERNAL_STORAGE",
            "android.permission.WRITE_EXTERNAL_STORAGE"};

    //动态申请权限
    public static void verifyStoragePermissions(Activity activity) {

        try {
            //检测是否有写的权限
            int permission = ActivityCompat.checkSelfPermission(activity,
                    "android.permission.WRITE_EXTERNAL_STORAGE");
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // 没有写的权限，去申请写的权限，会弹出对话框
                ActivityCompat.requestPermissions(activity, PERMISSIONS_STORAGE, REQUEST_EXTERNAL_STORAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {
            //for new api versions.
            View decorView = getWindow().getDecorView();
            int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY | View.SYSTEM_UI_FLAG_FULLSCREEN;
            decorView.setSystemUiVisibility(uiOptions);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        verifyStoragePermissions(this);
        WifiUtil wifiUtil = new WifiUtil(this);
        String info = wifiUtil.getWifiInfo();
/*       if (wifiUtil.checkState() == 3)
            wifiUtil.closeWifi();*/
        wifiUtil.openWifi();
        try {
            wifiUtil.addNetwork(wifiUtil.createWifiInfo(Constant.SSID, "123456789", 3));
        } catch (Exception l_Ex) {
        }

        //隐藏状态栏
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        //开启线程
        ThreadFactory threadFactory = new ThreadFactoryBuilder()
                .setNameFormat("splash-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(1024), threadFactory,
                new ThreadPoolExecutor.AbortPolicy());

        singleThreadPool.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    //使程序休眠5秒
                    sleep(1000);
                    startService();
                    Intent intent = new Intent();
                    intent.setClass(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    //启动MainActivity
                    finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        singleThreadPool.shutdown();

        //将保存的脉宽设置为0
        InitPulseWidthInfo();
//        showProgress();


    }

    private void InitPulseWidthInfo() {
        ParamInfo paramInfo = (ParamInfo) StateUtils.getObject(SplashActivity.this, Constant.PULSE_WIDTH_INFO_KEY);
        if (paramInfo != null) {
            paramInfo.setPulseWidth(0);
        } else {
            paramInfo = new ParamInfo();
            paramInfo.setPulseWidth(0);
        }

        StateUtils.setObject(SplashActivity.this, paramInfo, Constant.PULSE_WIDTH_INFO_KEY);


    }

    //启动后台服务，用于数据通讯。
    public void startService() {
        Intent intent = new Intent(SplashActivity.this, ConnectService.class);
        startService(intent);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(MultiLanguageUtil.attachBaseContext(newBase));
    }

}