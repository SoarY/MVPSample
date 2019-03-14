package com.soar.mvpsample.activity;

import android.os.Bundle;

import com.soar.mvpsample.R;
import com.soar.mvpsample.base.BaseActivity;
import com.soar.mvpsample.mvp.contract.ABCContract;
import com.soar.mvpsample.mvp.presenter.ABCPresenter;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

/**
 * 样板
 */
public class ABCActivity extends BaseActivity<ABCPresenter> implements ABCContract.IABCView {

    @Override
    public int getContentViewId() {
        return R.layout.activity_abc;
    }

    @Override
    protected ABCPresenter bindPresenter() {
        return new ABCPresenter(this);
    }

    @Override
    public LifecycleProvider<ActivityEvent> getNaviLifecycle() {
        return NaviLifecycle.createActivityLifecycleProvider(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
