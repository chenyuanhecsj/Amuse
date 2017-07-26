package com.csjhecy.amuse.common.impl;

import com.csjhecy.amuse.common.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by Admin on 2017/7/25.
 */

public abstract class RxPresenter implements BasePresenter {
    protected CompositeDisposable mCompositeDisposable;

    @Override
    public void addSubscribe(Disposable disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = new CompositeDisposable();
        }

        if (!disposable.isDisposed()) {
            mCompositeDisposable.add(disposable);
        }

    }

    @Override
    public void unSubscribe() {

        if (mCompositeDisposable != null) {
            mCompositeDisposable.clear();
        }
    }

    @Override
    public boolean remove(Disposable disposable) {

        return mCompositeDisposable != null && mCompositeDisposable.remove(disposable);
    }

}
