package com.soar.mvpsample.mvp.presenter;


import com.soar.mvpsample.mvp.contract.ABCContract;
import com.soar.mvpsample.mvp.model.ABCModel;

public class ABCPresenter implements ABCContract.IABCPresenter {
    private ABCContract.IABCView mView;
    private ABCContract.IABCModel mABCModel;

    public ABCPresenter(ABCContract.IABCView mView) {
        this.mView = mView;
        mABCModel = new ABCModel();
    }

    /**
     * 当页面销毁的时候,需要把View=null(避免内存泄漏)
     */
    @Override
    public void onDestroy() {
        mView = null;
    }
}
