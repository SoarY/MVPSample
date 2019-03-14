package com.soar.mvpsample.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ViewSwitcher;

import com.bumptech.glide.Glide;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.soar.mvpsample.R;
import com.soar.mvpsample.adapter.AndroidPlayAdapter;
import com.soar.mvpsample.adapter.HeaderFooterAdapter;
import com.soar.mvpsample.base.BaseLazyFragment;
import com.soar.mvpsample.bean.ArticlesBean;
import com.soar.mvpsample.mvp.contract.AndroidPlayContract;
import com.soar.mvpsample.mvp.presenter.AndroidPlayPresenter;
import com.soar.mvpsample.utils.SnackbarUtil;
import com.soar.mvpsample.utils.ToastUtils;
import com.soar.mvpsample.view.GlideImageLoader;
import com.soar.mvpsample.view.LoadingView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;

import java.util.List;

import butterknife.BindView;

/**
 * NAME：YONG_
 * Created at: 2019/1/9
 * Describe:
 */
public class AndroidPlayFragment extends BaseLazyFragment<AndroidPlayPresenter> implements AndroidPlayContract.IAndroidPlayView, OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.material_header_view)
    MaterialHeader materialHeaderView;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;
    @BindView(R.id.view_switcher)
    ViewSwitcher viewSwitcher;
    @BindView(R.id.loading_view)
    LoadingView loadingView;

    private static final int DELAY_TIME = 3000;

    private Banner banner;
    private ImageView ivBannerOne;
    private ImageView ivBannerTwo;
    private AndroidPlayAdapter adapter;

    public static AndroidPlayFragment newInstance() {
        return new AndroidPlayFragment();
    }

    @Override
    public int getContentViewId() {
        return R.layout.fragment_android_play;
    }

    @Override
    protected AndroidPlayPresenter bindPresenter() {
        return new AndroidPlayPresenter(this);
    }

    @Override
    public LifecycleProvider<ActivityEvent> getNaviLifecycle() {
        return NaviLifecycle.createActivityLifecycleProvider(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        banner.startAutoPlay();
    }

    @Override
    public void onStop() {
        super.onStop();
        banner.stopAutoPlay();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        initView();
        return view;
    }

    private void initView() {
        materialHeaderView.setColorSchemeResources(R.color.colorAccent);

        View view = View.inflate(getContext(), R.layout.header_android_paly, null);
        banner = view.findViewById(R.id.banner);
        ivBannerOne = view.findViewById(R.id.iv_banner_one);
        ivBannerTwo = view.findViewById(R.id.iv_banner_two);
        banner.setImageLoader(new GlideImageLoader())
                .setBannerStyle(BannerConfig.NUM_INDICATOR_TITLE)
                .setDelayTime(DELAY_TIME);
        banner.setOnBannerListener(position -> ToastUtils.showToast(position + ""));

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        HeaderFooterAdapter headerAdapter = new HeaderFooterAdapter(adapter = new AndroidPlayAdapter());
        headerAdapter.addHeaderView(view);
        recyclerView.setAdapter(headerAdapter);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        adapter.setItemClickListener(position -> ToastUtils.showToast(position + ""));
    }

    @Override
    protected void lazyData() {
        viewState(1, LoadingView.State.ing);
        presenter.getBannerData();
        presenter.getHomeList(true);
    }

    @Override
    public void notifyBanner(List<?> datas, List<String> titles) {
        if (titles != null)
            banner.setBannerTitles(titles);
        if (datas != null && datas.size() > 0)
            banner.setImages(datas).start();
        Glide.with(context)
                .load(datas.size()>=1?datas.get(1):null)
                .into(ivBannerOne);
        Glide.with(context)
                .load(datas.size()>=2?datas.get(2):null)
                .into(ivBannerTwo);
    }

    @Override
    public void notifyData(List<ArticlesBean> datas) {
        adapter.setData(datas);
    }


    @Override
    public void showSnackbar(String msg) {
        SnackbarUtil.showSnackbar(getView(), msg, Snackbar.LENGTH_SHORT);
    }

    /**
     * 界面状态
     */
    @Override
    public void viewState(int i, LoadingView.State empty) {
        viewSwitcher.setDisplayedChild(i);
        loadingView.notifyDataChanged(empty);
    }

    @Override
    public void stopRefresh() {
        refreshLayout.finishRefresh();
        refreshLayout.finishLoadMore();
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        presenter.onRefresh();
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        presenter.onLoadMore();
    }
}
