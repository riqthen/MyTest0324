package com.h.imtest.config; /*
 * 文件名：
 * 创建者：HeR
 * 描  述：
 * 时  间：2018/3/28 12:04:52
 * 修改者：
 * 修改备注：
 * 修改时间：
 * 版  权：互动科技
 */

import android.app.Application;

import com.h.mylibrary.utils.SPUtils;
import com.h.mylibrary.utils.TUtil;

public class IMApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        TUtil.init(this);
        SPUtils.getInstance().init(this, MODE_PRIVATE);
    }
}
