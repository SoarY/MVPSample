package com.soar.mvpsample.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ViewSwitcher;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.soar.mvpsample.R;
import com.soar.mvpsample.adapter.AndroidPlayAdapter;
import com.soar.mvpsample.base.BaseActivity;
import com.soar.mvpsample.bean.ArticlesBean;
import com.soar.mvpsample.constant.RouteConstants;
import com.soar.mvpsample.mvp.contract.ArticleListContract;
import com.soar.mvpsample.mvp.presenter.ArticleListPresenter;
import com.soar.mvpsample.utils.SnackbarUtil;
import com.soar.mvpsample.view.LoadingView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

import java.util.List;

import butterknife.BindView;

@Route(path = RouteConstants.Music.ARTICLE)
public class ArticleListActivity extends BaseActivity<ArticleListPresenter> implements ArticleListContract.IArticleListView, OnRefreshListener, OnLoadMoreListener {

    @BindView(R.id.view_switcher)
    ViewSwitcher viewSwitcher;
    @BindView(R.id.loading_view)
    LoadingView loadingView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.refresh_layout)
    SmartRefreshLayout refreshLayout;
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    public static final String CID = "cid";
    public static final String CHAPTER_NAME = "chapterName";

    @Autowired
    public int cid;
    @Autowired
    public String chapterName;

    private AndroidPlayAdapter adapter;

    @Override
    public int getContentViewId() {
        return R.layout.activity_article_list;
    }

    @Override
    protected ArticleListPresenter bindPresenter() {
        return new ArticleListPresenter(this);
    }

    @Override
    public LifecycleProvider<ActivityEvent> getNaviLifecycle() {
        return NaviLifecycle.createActivityLifecycleProvider(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ARouter.getInstance().inject(this);
        initView();
        initData();
    }

    private void initView() {
        toolbar.setTitle(chapterName);
        toolbar.setNavigationIcon(R.mipmap.icon_back);
        toolbar.setNavigationOnClickListener(v -> finish());

        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        recyclerView.setAdapter(adapter = new AndroidPlayAdapter());
        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);

        adapter.setItemClickListener(position ->showSnackbar(position+""));
    }

    private void initData() {
        presenter.getArticleList(true);
    }

    @Override
    public int getCID() {
        return cid;
    }

    @Override
    public void notifyData(List<ArticlesBean> datas) {
        adapter.setData(datas);
    }

    @Override
    public void showSnackbar(String msg) {
        SnackbarUtil.showSnackbar(viewSwitcher, msg, Snackbar.LENGTH_SHORT);
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
