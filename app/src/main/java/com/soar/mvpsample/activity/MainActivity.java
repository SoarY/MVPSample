package com.soar.mvpsample.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.soar.mvpsample.R;
import com.soar.mvpsample.adapter.BottomPagerAdapter;
import com.soar.mvpsample.base.BaseActivity;
import com.soar.mvpsample.base.BasePresenter;
import com.soar.mvpsample.constant.RouteConstants;
import com.soar.mvpsample.fragment.ABCFragment;
import com.soar.mvpsample.fragment.MusicFragment;
import com.soar.mvpsample.utils.ToastUtils;

import butterknife.BindView;
import butterknife.OnClick;

@Route(path = RouteConstants.Rule.MAIN_ACTIVITY)
public class MainActivity extends BaseActivity {

    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.navigation_tool)
    BottomNavigationViewEx navigationTool;

    @Override
    public int getContentViewId() {
        return R.layout.activity_main;
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
        navigationTool.setTextVisibility(false);
        navigationTool.enableAnimation(false);
        navigationTool.enableShiftingMode(false);
        navigationTool.enableItemShiftingMode(false);
        for (int i = 0; i < navigationTool.getItemCount(); i++)
            navigationTool.setIconTintList(i, getResources().getColorStateList(R.color.select_tool_main));

        BottomPagerAdapter adapter = new BottomPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(MusicFragment.newInstance());
        adapter.addFragment(ABCFragment.newInstance());
        adapter.addFragment(ABCFragment.newInstance());
        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(adapter);
        navigationTool.setupWithViewPager(viewPager);
    }

    @OnClick({R.id.fl_title_menu, R.id.iv_title_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fl_title_menu:
                ToastUtils.showToast("Menu");
                break;
            case R.id.iv_title_search:
                ToastUtils.showToast("Search");
                break;
        }
    }
}
