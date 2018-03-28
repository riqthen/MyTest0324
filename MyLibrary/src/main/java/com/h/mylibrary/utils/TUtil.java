package com.h.mylibrary.utils;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.support.v4.widget.TextViewCompat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.ref.WeakReference;

/**
 * author: Blankj
 * blog  : http://blankj.com
 * time  : 2016/09/29
 * update: H
 * uTime : 2017/11/14
 * desc  : 吐司相关工具类
 */
public final class TUtil {
    @SuppressLint("StaticFieldLeak")
    private static Application sApplication;

    private static final int COLOR_DEFAULT = 0xFEFFFFFF;    //默认背景颜色
    private static final Handler HANDLER = new Handler(Looper.getMainLooper());

    private static Toast sToast;
    private static WeakReference<View> sViewWeakReference;
    private static int sLayoutId = -1;
    private static int gravity = Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM;    //默认位置
    private static int xOffset = 0;     //默认x偏移
    private static int yOffset;  //默认y偏移
    private static int bgColor = COLOR_DEFAULT;
    private static int msgColor = COLOR_DEFAULT;
    private static int duration = Toast.LENGTH_SHORT;   //默认时长

    private TUtil() {
        throw new UnsupportedOperationException("you can't instantiate TUtil...");
    }

    public static void init(final Application app) {
        sApplication = app;
        yOffset = (int) (64 * sApplication.getResources().getDisplayMetrics().density + 0.5);
    }


    public static void setGravity(final int gravity, final int xOffset, final int yOffset) {
        TUtil.gravity = gravity;
        TUtil.xOffset = xOffset;
        TUtil.yOffset = yOffset;
    }

    public static void setBgColor(@ColorInt final int backgroundColor) {
        TUtil.bgColor = backgroundColor;
    }

    public static void setMsgColor(@ColorInt final int msgColor) {
        TUtil.msgColor = msgColor;
    }

    public static void setConfig(@ColorInt final int backgroundColor, @ColorInt final int msgColor, final int gravity, final int xOffset, final int yOffset, int duration) {
        TUtil.bgColor = backgroundColor;
        TUtil.msgColor = msgColor;
        TUtil.duration = duration;
        TUtil.gravity = gravity;
        TUtil.xOffset = xOffset;
        TUtil.yOffset = yOffset;
    }

    public static void show(final String format, final Object... args) {
        show(format, duration, args);
    }

    private static void show(final String format, final int duration, final Object... args) {
        show(String.format(format, args), duration);
    }

    public static void show(final CharSequence text) {
        HANDLER.post(new Runnable() {
            @Override
            public void run() {
                cancel();
                sToast = Toast.makeText(sApplication, text, duration);
                // solve the font of toast
                TextView tvMessage = (TextView) sToast.getView().findViewById(android.R.id.message);
                TextViewCompat.setTextAppearance(tvMessage, android.R.style.TextAppearance);
                tvMessage.setTextColor(msgColor);
                setBgAndGravity();
                sToast.show();
            }
        });
    }

    public static void show(final CharSequence text, final int duration) {
        HANDLER.post(new Runnable() {
            @Override
            public void run() {
                cancel();
                sToast = Toast.makeText(sApplication, text, duration);
                // solve the font of toast
                TextView tvMessage = (TextView) sToast.getView().findViewById(android.R.id.message);
                TextViewCompat.setTextAppearance(tvMessage, android.R.style.TextAppearance);
                tvMessage.setTextColor(msgColor);
                setBgAndGravity();
                sToast.show();
            }
        });
    }

    public static void show(@LayoutRes final int layoutId) {
        final View view = getView(layoutId);
        HANDLER.post(new Runnable() {
            @Override
            public void run() {
                cancel();
                sToast = new Toast(sApplication);
                sToast.setView(view);
                sToast.setDuration(duration);
                setBgAndGravity();
                sToast.show();
            }
        });
    }

    public static void show(@LayoutRes final int layoutId, final int duration) {
        final View view = getView(layoutId);
        HANDLER.post(new Runnable() {
            @Override
            public void run() {
                cancel();
                sToast = new Toast(sApplication);
                sToast.setView(view);
                sToast.setDuration(duration);
                setBgAndGravity();
                sToast.show();
            }
        });
    }

    public static void cancel() {
        if (sToast != null) {
            sToast.cancel();
            sToast = null;
        }
    }

    private static void setBgAndGravity() {
        View toastView = sToast.getView();
        if (bgColor != COLOR_DEFAULT) {
            Drawable background = toastView.getBackground();
            background.setColorFilter(new PorterDuffColorFilter(bgColor, PorterDuff.Mode.SRC_IN));
        }
        sToast.setGravity(gravity, xOffset, yOffset);
    }

    private static View getView(@LayoutRes final int layoutId) {
        if (sLayoutId == layoutId) {
            if (sViewWeakReference != null) {
                final View toastView = sViewWeakReference.get();
                if (toastView != null) {
                    return toastView;
                }
            }
        }
        LayoutInflater inflate = (LayoutInflater) sApplication.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View toastView = inflate.inflate(layoutId, null);
        sViewWeakReference = new WeakReference<>(toastView);
        sLayoutId = layoutId;
        return toastView;
    }
}