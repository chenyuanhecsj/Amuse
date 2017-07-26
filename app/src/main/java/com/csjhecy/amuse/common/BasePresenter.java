package com.csjhecy.amuse.common;

import io.reactivex.disposables.Disposable;

/**
 * Created by Admin on 2017/7/25.
 */

public interface BasePresenter {

    /**
     * 添加订阅
     */
    void addSubscribe(Disposable disposable);

    /**
     * 取消订阅
     */
    void unSubscribe();

    /**
     * 移除单个 disposable
     */
    boolean remove(Disposable disposable);

    /**
     * 获取数据
     */
    void getData();
}
