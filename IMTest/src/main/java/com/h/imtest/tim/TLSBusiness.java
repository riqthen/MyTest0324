package com.h.imtest.tim;

import android.content.Context;

import com.h.imtest.config.Constant;

import tencent.tls.platform.TLSAccountHelper;
import tencent.tls.platform.TLSLoginHelper;
import tencent.tls.platform.TLSUserInfo;

/**
 * 文件名：
 * 创建者：HeR
 * 描  述：
 * 时  间：2018/3/28 14:49:04
 * 修改者：
 * 修改备注：
 * 修改时间：
 * 版  权：互动科技
 */
public class TLSBusiness {
    public static int lastErrorNumber = -10000;  //跳转到主界面的时候需要判断的处理


    private static TLSBusiness instance;
    private TLSLoginHelper mTlsLoginHelper;
    private TLSAccountHelper mTlsAccountHelper;

    public static TLSBusiness getInstance() {
        if (instance == null) {
            instance = new TLSBusiness();
        }
        return instance;
    }

    /**
     * 账号系统初始化
     *
     * @param context
     */
    public void init(Context context) {
        mTlsLoginHelper = TLSLoginHelper.getInstance().init(context, Constant.SDK_APPID, Constant.ACCOUNT_TYPE, Constant.APP_VERSION);
        mTlsLoginHelper.setTimeOut(Constant.TIMEOUT);
        mTlsLoginHelper.setLocalId(Constant.LANGUAGE_CODE);

        mTlsAccountHelper = TLSAccountHelper.getInstance().init(context, Constant.SDK_APPID, Constant.ACCOUNT_TYPE, Constant.APP_VERSION);
        mTlsAccountHelper.setCountry(Constant.COUNTRY_CODE);
        mTlsAccountHelper.setLocalId(Constant.LANGUAGE_CODE);
        mTlsAccountHelper.setTimeOut(Constant.TIMEOUT);
    }

    /**
     * 获取最近一位登录用户的id
     * @return
     */
    public String getLastUserId() {
        TLSUserInfo lastUserInfo = mTlsLoginHelper.getLastUserInfo();
        if (lastUserInfo != null) {
            return lastUserInfo.identifier;
        } else {
            return null;
        }
    }
    public String  getUserSig(String id){
        return mTlsLoginHelper.getUserSig(id);
    }



    /**
     * 清除账号信息
     *
     * @param id
     */
    public void clearUserInfo(String id) {
        mTlsLoginHelper.clearUserInfo(id);
        lastErrorNumber = -10000;
    }

    /**
     * 是否需要登录
     *
     * @param id
     * @return
     */
    public boolean needLogin(String id) {
        return id == null || mTlsLoginHelper.needLogin(id);
    }

}
