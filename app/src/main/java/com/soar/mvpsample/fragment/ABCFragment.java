package com.soar.mvpsample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soar.mvpsample.R;
import com.soar.mvpsample.base.BaseLazyFragment;
import com.soar.mvpsample.mvp.contract.ABCContract;
import com.soar.mvpsample.mvp.presenter.ABCPresenter;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

/**
 * 样板
 */
public class ABCFragment extends BaseLazyFragment<ABCPresenter> implements ABCContract.IABCView {

    public static ABCFragment newInstance() {
        return new ABCFragment();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_abc;
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
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View mView = super.onCreateView(inflater, container, savedInstanceState);
        return mView;
    }

    /**
     * Fragment懒加载
     */
    @Override
    protected void lazyData() {

    }
}
