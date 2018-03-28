package com.h.imtest.tim;

import com.tencent.TIMGroupAssistantListener;
import com.tencent.TIMGroupCacheInfo;
import com.tencent.TIMGroupMemberInfo;
import com.tencent.TIMGroupSettings;
import com.tencent.TIMManager;

import java.util.List;
import java.util.Observable;

/**
 * 创建者：HeR
 * 时  间：2018/3/28 16:36:54
 * 描  述：
 * 修改者：
 * 修改时间：
 * 修改备注：
 * 版  权：互动科技
 */
public class GroupEvent extends Observable implements TIMGroupAssistantListener {
    private static GroupEvent sInstance = new GroupEvent();

    public static GroupEvent getInstance() {
        return sInstance;
    }

    public void init() {
        TIMManager.getInstance().enableGroupInfoStorage(true);
        TIMGroupSettings timGroupSettings = new TIMGroupSettings();
        TIMManager.getInstance().initGroupSettings(timGroupSettings);
        TIMManager.getInstance().setGroupAssistantListener(this);
    }

    @Override
    public void onMemberJoin(String s, List<TIMGroupMemberInfo> list) {
        setChanged();
        notifyObservers(new NotifyCommand(NotifyType.REFRESH, list));
    }

    @Override
    public void onMemberQuit(String s, List<String> list) {

    }

    @Override
    public void onMemberUpdate(String s, List<TIMGroupMemberInfo> list) {

    }

    @Override
    public void onGroupAdd(TIMGroupCacheInfo timGroupCacheInfo) {
        setChanged();
        notifyObservers(new NotifyCommand(NotifyType.ADD, timGroupCacheInfo));
    }

    @Override
    public void onGroupDelete(String s) {
        setChanged();
        notifyObservers(new NotifyCommand(NotifyType.DELETE, s));
    }

    @Override
    public void onGroupUpdate(TIMGroupCacheInfo timGroupCacheInfo) {
        setChanged();
        notifyObservers(new NotifyCommand(NotifyType.UPDATE, timGroupCacheInfo));
    }

    public class NotifyCommand {
        public final NotifyType mNotifyType;
        public final Object mData;

        public NotifyCommand(NotifyType notifyType, Object data) {
            mNotifyType = notifyType;
            mData = data;
        }
    }

    public enum NotifyType {
        REFRESH,    //刷新
        ADD,        //添加群
        DELETE,     //删除群
        UPDATE      //更新群
    }
}
