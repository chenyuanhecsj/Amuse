package com.csjhecy.amuse;

/**
 * Created by Admin on 2017/7/25.
 */

public class MainModel implements MainContract.Model {

    private static MainModel mainModel;

    public static MainModel getInstance(){
        if (mainModel == null){
            mainModel = new MainModel();
        }
        return mainModel;
    }




}
