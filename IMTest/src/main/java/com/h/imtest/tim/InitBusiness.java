package com.h.imtest.tim;


import android.content.Context;

import com.tencent.TIMLogLevel;
import com.tencent.TIMManager;

/*
 * 文件名：
 * 创建者：HeR
 * 描  述：
 * 时  间：2018/3/28 14:19:10
 * 修改者：
 * 修改备注：
 * 修改时间：
 * 版  权：互动科技
 */
public class InitBusiness {


    /**
     * 初始化TIMManager
     *
     * @param context
     */
    public static void init(Context context) {
        //TIMManager初始化
        TIMManager.getInstance().setLogLevel(TIMLogLevel.ERROR);
        TIMManager.getInstance().init(context);
        TIMManager.getInstance().disableAutoReport();
        //账号系统初始化
        TLSBusiness.getInstance().init(context);
    }

    /**
     * 退出账号，清除用户信息
     *
     * @param id identifier
     */
    public static void logoutTIM(String id) {
        TLSBusiness.getInstance().clearUserInfo(id);
    }

}



















