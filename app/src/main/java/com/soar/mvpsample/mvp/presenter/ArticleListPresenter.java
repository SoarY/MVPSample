package com.soar.mvpsample.mvp.presenter;


import com.soar.mvpsample.MyApplication;
import com.soar.mvpsample.R;
import com.soar.mvpsample.bean.ArticlesBean;
import com.soar.mvpsample.bean.DataBean;
import com.soar.mvpsample.constant.Constant;
import com.soar.mvpsample.impl.OnHttpCallBack;
import com.soar.mvpsample.mvp.contract.ArticleListContract;
import com.soar.mvpsample.mvp.model.IModel.ArticleListModel;
import com.soar.mvpsample.retrofit.APIException;
import com.soar.mvpsample.retrofit.ExceptionEngine;
import com.soar.mvpsample.view.LoadingView;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.disposables.Disposable;

public class ArticleListPresenter implements ArticleListContract.IArticleListPresenter {
    private ArticleListContract.IArticleListView mView;
    private ArticleListModel mArticleListModel;

    private int pageIndex = Constant.page;
    private int totalCount;
    public List<ArticlesBean> datas = new ArrayList<>();

    public ArticleListPresenter(ArticleListContract.IArticleListView mView) {
        this.mView = mView;
        mArticleListModel = new ArticleListModel();
    }

    @Override
    public void getArticleList(boolean isRefreshOrLoad) {
        mArticleListModel.getArticleList(mView.getNaviLifecycle(), pageIndex, mView.getCID(), new OnHttpCallBack<DataBean>() {

            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(DataBean data) {
                if (pretreatment(data, isRefreshOrLoad)) return;
                //数据操作
                totalCount = data.total;
                datas.addAll(data.datas);
                mView.notifyData(datas);//刷新条目
            }

            @Override
            public void onError(APIException ex) {
                if (ex.getCode() == ExceptionEngine.ERROR.ERROR_NET)
                    mView.showSnackbar(ex.getDisplayMessage());
                mView.viewState(1, ex.getCode() == ExceptionEngine.ERROR.ERROR_NET ? LoadingView.State.error : LoadingView.State.error);
            }

            @Override
            public void onComplete() {
            }
        });
    }

    /**
     * 界面预处理
     */
    private boolean pretreatment(DataBean data, boolean isRefreshOrLoad) {
        mView.stopRefresh();
        mView.viewState(0, LoadingView.State.done);//加载完成
        if (isRefreshOrLoad) {
            datas.clear();
            mView.notifyData(datas);//clear后需notify
        }
        if (data == null || data.datas == null || data.datas.size() == 0)
            return true;
        return false;
    }

    /**
     * 下拉刷新
     */
    @Override
    public void onRefresh() {
        pageIndex = Constant.page;
        getArticleList(true);
    }

    /**
     * 上拉加载
     */
    @Override
    public void onLoadMore() {
        if (datas.size() >= totalCount) {
            mView.showSnackbar(MyApplication.getContext().getResources().getString(R.string.to_loaded));
            mView.stopRefresh();
            return;
        }
        pageIndex++;
        getArticleList(false);
    }

    /**
     * 当页面销毁的时候,需要把View=null(避免内存泄漏)
     */
    @Override
    public void onDestroy() {
        mView = null;
    }
}
