package com.csjhecy.amuse;

/**
 * Created by Admin on 2017/7/25.
 */

public class MainPresenter extends MainContract.presenter {

    private MainContract.View mMainView;
    private MainContract.Model mModel;

    public MainPresenter(MainContract.View mainView, MainContract.Model model) {
        this.mMainView = mainView;
        this.mModel = model;
    }

    @Override
    public void getData() {



    }
}
