package com.soar.mvpsample.mvp.contract;


import com.soar.mvpsample.base.BaseNaviView;
import com.soar.mvpsample.base.BasePresenter;
import com.soar.mvpsample.bean.ArticlesBean;
import com.soar.mvpsample.view.LoadingView;

import java.util.List;

public interface ArticleListContract {
    /**
     * V视图层
     */
    interface IArticleListView extends BaseNaviView {

        int getCID();

        void showSnackbar(String msg);

        void viewState(int i, LoadingView.State empty);//界面状态

        void notifyData(List<ArticlesBean> datas);//刷新条目

        void stopRefresh();//停止刷新
    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface IArticleListPresenter extends BasePresenter {
        void getArticleList(boolean isRefreshOrLoad);

        void onRefresh();

        void onLoadMore();
    }
}
