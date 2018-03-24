package com.h.mytest;

import com.h.mytest.observable.Observable;
import com.h.mytest.observer.Observer;

import java.util.ArrayList;
import java.util.List;

/*
* 文件名：
* 创建者：HeR
* 描  述：
* 时  间：2018/3/24 18:22 33
* 修改者：
* 修改备注：
* 修改时间：
* 版  权：互动科技
*/
public class ObservableManager implements Observable {
    private static ObservableManager sObservableManager;
    private List<Observer> mObserverList = new ArrayList<>();

    public static ObservableManager getObservableManager() {
        if (sObservableManager == null) {
            synchronized (ObservableManager.class) {
                if (sObservableManager == null) {
                    sObservableManager = new ObservableManager();
                }
            }
        }
        return sObservableManager;
    }


    @Override
    public void add(Observer observer) {
        mObserverList.add(observer);
    }

    @Override
    public void remove(Observer observer) {
        if (mObserverList.contains(observer)) {
            mObserverList.remove(observer);
        }
    }

    @Override
    public void changeText(String s) {
        for (Observer observer : mObserverList) {
            observer.updateView(s);
        }
    }
}
