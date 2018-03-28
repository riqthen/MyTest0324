package com.h.imtest.tim;

import com.tencent.TIMFriendGroup;
import com.tencent.TIMFriendshipProxyListener;
import com.tencent.TIMFriendshipProxyStatus;
import com.tencent.TIMManager;
import com.tencent.TIMSNSChangeInfo;
import com.tencent.TIMUserProfile;

import java.util.List;
import java.util.Observable;

/**
 * 文件名：
 * 创建者：HeR
 * 描  述：
 * 时  间：2018/3/28 16:36:39
 * 修改者：
 * 修改备注：
 * 修改时间：
 * 版  权：互动科技
 */
public class FriendshipEvent extends Observable implements TIMFriendshipProxyListener {
    private static FriendshipEvent sInstance = new FriendshipEvent();

    public static FriendshipEvent getInstance() {
        return sInstance;
    }

    public void init() {
        TIMManager.getInstance().enableFriendshipStorage(true);
        TIMManager.getInstance().initFriendshipSettings(0xff, null);
        TIMManager.getInstance().setFriendshipProxyListener(this);
    }


    @Override
    public void OnProxyStatusChange(TIMFriendshipProxyStatus timFriendshipProxyStatus) {
        if (timFriendshipProxyStatus == TIMFriendshipProxyStatus.TIM_FRIENDSHIP_STATUS_SYNCED) {
            setChanged();
            notifyObservers(new NotifyCommand(NotifyType.REFRESH, null));
        }
    }

    @Override
    public void OnAddFriends(List<TIMUserProfile> list) {
        setChanged();
        notifyObservers(new NotifyCommand(NotifyType.FRIEND_ADD, list));
    }

    @Override
    public void OnDelFriends(List<String> list) {
        setChanged();
        notifyObservers(new NotifyCommand(NotifyType.FRIEND_DELETE, list));
    }

    @Override
    public void OnFriendProfileUpdate(List<TIMUserProfile> list) {
        setChanged();
        notifyObservers(new NotifyCommand(NotifyType.UPDATE_PROFILE, list));
    }

    @Override
    public void OnAddFriendReqs(List<TIMSNSChangeInfo> list) {
        setChanged();
        notifyObservers(new NotifyCommand(NotifyType.REQUEST_ADD, list));
    }

    @Override
    public void OnAddFriendGroups(List<TIMFriendGroup> list) {
        setChanged();
        notifyObservers(new NotifyCommand(NotifyType.UPDATE_GROUP, list));
    }

    @Override
    public void OnDelFriendGroups(List<String> list) {
        setChanged();
        notifyObservers(new NotifyCommand(NotifyType.FRIEND_DELETE, list));
    }

    @Override
    public void OnFriendGroupUpdate(List<TIMFriendGroup> list) {
        setChanged();
        notifyObservers(new NotifyCommand(NotifyType.FRIEND_DELETE, list));
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
        REFRESH,        //刷新数据
        REQUEST_ADD,    //请求添加
        MSG_READ,       //消息已读
        FRIEND_ADD,     //添加好友
        FRIEND_DELETE,  //删除好友
        UPDATE_PROFILE, //更新个人资料
        UPDATE_GROUP    //变更分组
    }


}
