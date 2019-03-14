package com.soar.mvpsample.base;

import android.app.Activity;
import android.os.Bundle;

import com.trello.navi2.component.support.NaviAppCompatActivity;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * NAME：YONG_
 * Created at: 2017/7/25
 * Describe:  基类
 */
public abstract class BaseActivity<T extends BasePresenter> extends NaviAppCompatActivity {

    public String TAG = this.getClass().getSimpleName();
    public Activity context;
    private Unbinder bind;
    public T presenter;

    public abstract int getContentViewId();

    protected abstract T bindPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentViewId());
        init();
    }

    private void init() {
        context = this;
        bind = ButterKnife.bind(this);
        presenter = bindPresenter();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bind.unbind();  //释放所有绑定的view
        if (presenter != null)
            presenter.onDestroy();
    }
}
