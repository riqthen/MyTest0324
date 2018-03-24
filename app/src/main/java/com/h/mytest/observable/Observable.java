package com.h.mytest.observable;

import com.h.mytest.observer.Observer;

/*
* 文件名：
* 创建者：HeR
* 描  述：
* 时  间：2018/3/24 18:21 23
* 修改者：
* 修改备注：
* 修改时间：
* 版  权：互动科技
*/
public interface Observable {
    void add(Observer observer);

    void remove(Observer observer);

    void changeText(String s);
}
