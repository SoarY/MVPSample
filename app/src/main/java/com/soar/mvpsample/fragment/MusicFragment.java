package com.soar.mvpsample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.soar.mvpsample.R;
import com.soar.mvpsample.adapter.MePagerAdapter;
import com.soar.mvpsample.base.BaseLazyFragment;
import com.soar.mvpsample.base.BasePresenter;

import butterknife.BindView;


/**
 * NAME：YONG_
 * Created at: 2018/12/11 16
 * Describe:
 */
public class MusicFragment extends BaseLazyFragment {

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;
    @BindView(R.id.view_pager)
    ViewPager viewPager;

    public static MusicFragment newInstance() {
        return new MusicFragment();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_music;
    }

    @Override
    protected BasePresenter bindPresenter() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return view;
    }

    private void initView() {
        MePagerAdapter pagerAdapter = new MePagerAdapter(getChildFragmentManager());
        pagerAdapter.addFragment(getString(R.string.android), AndroidPlayFragment.newInstance());
        pagerAdapter.addFragment(getString(R.string.everyday), ABCFragment.newInstance());
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void lazyData() {
    }
}
