package com.soar.mvpsample.mvp.contract;


import com.soar.mvpsample.base.BaseNaviView;
import com.soar.mvpsample.base.BasePresenter;

public interface ABCContract {
    /**
     * V视图层
     */
    interface IABCView extends BaseNaviView {
    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface IABCPresenter extends BasePresenter {
    }

    /**
     * 逻辑处理层
     */
    interface IABCModel{
    }
}
