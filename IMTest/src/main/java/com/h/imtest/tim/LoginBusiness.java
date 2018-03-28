package com.h.imtest.tim;

import com.h.imtest.config.Constant;
import com.tencent.TIMCallBack;
import com.tencent.TIMManager;
import com.tencent.TIMUser;

/**
 * 创建者：HeR
 * 时  间：2018/3/28 17:34:50
 * 描  述：
 * 修改者：
 * 修改时间：
 * 修改备注：
 * 版  权：互动科技
 */
public class LoginBusiness {

    /**
     * 登录IM
     * @param id
     * @param userSig
     * @param timCallBack
     */
    public static void loginIM(String id, String userSig, TIMCallBack timCallBack) {
        if (id == null || userSig == null) {
            return;
        }
        TIMUser timUser = new TIMUser();
        timUser.setIdentifier(id);
        timUser.setAccountType(String.valueOf(Constant.ACCOUNT_TYPE));
        timUser.setAppIdAt3rd(String.valueOf(Constant.SDK_APPID));
        TIMManager.getInstance().login((int) Constant.SDK_APPID, timUser, userSig, timCallBack);
    }

    /**
     * 登出IM
     * @param timCallBack
     */
    public static void logoutIM(TIMCallBack timCallBack) {
        TIMManager.getInstance().logout(timCallBack);
    }
}
