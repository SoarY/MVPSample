package com.soar.mvpsample.activity;

import android.os.Bundle;
import android.os.Handler;

import com.alibaba.android.arouter.launcher.ARouter;
import com.soar.mvpsample.R;
import com.soar.mvpsample.base.BaseActivity;
import com.soar.mvpsample.base.BasePresenter;
import com.soar.mvpsample.constant.RouteConstants;
import com.soar.mvpsample.view.StatusBarCompat;


public class SplashActivity extends BaseActivity {

    private final static long DELAY_MILLIS = 3000;


    @Override
    public int getContentViewId() {
        return R.layout.activity_splash;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
    }

    private void initView() {
        StatusBarCompat.setTransparentStatus(context);

        new Handler().postDelayed(() -> {
            ARouter.getInstance()
                    .build(RouteConstants.Rule.MAIN_ACTIVITY)
                    .withTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out)
                    .navigation(this);
            finish();
        }, DELAY_MILLIS);
    }

}
