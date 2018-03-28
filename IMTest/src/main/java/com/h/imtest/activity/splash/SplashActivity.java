package com.h.imtest.activity.splash;

import android.Manifest;
import android.app.AlertDialog;
import android.app.NotificationManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;

import com.h.imtest.R;
import com.h.imtest.activity.MainActivity;
import com.h.imtest.activity.login.LoginActivity;
import com.h.imtest.tim.FriendshipEvent;
import com.h.imtest.tim.GroupEvent;
import com.h.imtest.tim.InitBusiness;
import com.h.imtest.tim.LoginBusiness;
import com.h.imtest.tim.TLSBusiness;
import com.h.mylibrary.utils.TUtil;
import com.tencent.TIMCallBack;

import java.util.ArrayList;
import java.util.List;

import static android.os.Build.MANUFACTURER;

/**
 * @author H
 */
public class SplashActivity extends AppCompatActivity implements SplashView, TIMCallBack {
    private static final int REQUEST_PERMS = 0x645;
    public static String sId;
    private static String sUserSig;

    public static boolean sHasUserLogin = true;  //是否已有用户登录
    private SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        //去通知栏
        clearNotification();
        //检查权限
        checkPermissions();
    }

    private List<String> permList = new ArrayList<>();

    private void checkPermissions() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED) {
                permList.add(Manifest.permission.READ_PHONE_STATE);
            }
            if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED)) {
                permList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            }
            if (permList.size() <= 0) {
                init();
            } else {
                requestPermissions(permList.toArray(new String[permList.size()]), REQUEST_PERMS);
            }
        } else {
            init();
        }
    }

    private void clearNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (notificationManager != null) {
            notificationManager.cancelAll();
        }
    }

    private void init() {
        InitBusiness.init(this);
        sId = TLSBusiness.getInstance().getLastUserId();
        sUserSig = TLSBusiness.getInstance().getUserSig(sId);
        sHasUserLogin = sId != null && TLSBusiness.getInstance().needLogin(sId);
        mSplashPresenter = new SplashPresenter(this);
        mSplashPresenter.toActivity();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case REQUEST_PERMS:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    init();
                } else {
                    TUtil.show("请开启权限！");
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void toLoginActivity() {
        Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    @Override
    public void toMainActivity() {
        FriendshipEvent.getInstance().init();
        GroupEvent.getInstance().init();
        LoginBusiness.loginIM(sId, sUserSig, this);
    }

    /**
     * 登录im错误
     *
     * @param i
     * @param s
     */
    @Override
    public void onError(int i, String s) {
        switch (i) {
            case 6208:
                new AlertDialog.Builder(this)
                        .setTitle("警告")
                        .setMessage("你的账号已在其他终端登录,重新登录")
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                toMainActivity();
                            }
                        })
                        .create()
                        .show();
                break;
            default:
                TUtil.show("错误码：" + i + "  " + s);
                break;
        }
    }

    /**
     * 登录im成功
     */
    @Override
    public void onSuccess() {
        registerXiaomiAndHuaweiPush();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void registerXiaomiAndHuaweiPush() {
        String deviceManufacturer = MANUFACTURER;
        if (deviceManufacturer.equals("Xiaomi")) {

        } else if (deviceManufacturer.equals("HUAWEI")) {

        }
    }
}
