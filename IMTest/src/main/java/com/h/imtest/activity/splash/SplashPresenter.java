package com.h.imtest.activity.splash;

/**
 * 文件名：
 * 创建者：HeR
 * 描  述：
 * 时  间：2018/3/28 15:25:48
 * 修改者：
 * 修改备注：
 * 修改时间：
 * 版  权：互动科技
 */
public class SplashPresenter {
    private SplashView mSplashView;

    public SplashPresenter(SplashView splashView) {
        mSplashView = splashView;

    }

    /**
     * 跳转界面 登录/主界面
     */
    public void toActivity() {
        if (SplashActivity.sHasUserLogin) {
            mSplashView.toLoginActivity();
        } else {
            mSplashView.toMainActivity();
        }
    }

}
