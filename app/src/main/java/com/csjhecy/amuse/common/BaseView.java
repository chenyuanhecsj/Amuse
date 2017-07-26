package com.csjhecy.amuse.common;

/**
 * Created by Admin on 2017/7/25.
 */

public interface BaseView<T extends BasePresenter> {

    /**
     * 设置 让View 持有Presenter
     * @param presenter
     */
    void setPresenter(T presenter);
}
