package com.csjhecy.amuse;

import com.csjhecy.amuse.common.impl.RxPresenter;

/**
 * Created by Admin on 2017/7/25.
 */

public interface MainContract {
    /**
     * View层需要操作的放这里
     */
    interface View{

    }

    /**
     * P层需要展示的操作放这里 操作Model
     */

    abstract class presenter extends RxPresenter{

    }


    /**
     * model层做一些处理数据的事情
     */
    interface Model{

    }
}
