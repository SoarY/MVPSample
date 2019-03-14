package com.soar.mvpsample.mvp.contract;


import com.soar.mvpsample.base.BaseNaviView;
import com.soar.mvpsample.base.BasePresenter;
import com.soar.mvpsample.bean.AndroidPlayBannerBean;
import com.soar.mvpsample.bean.ArticlesBean;
import com.soar.mvpsample.impl.OnHttpCallBack;
import com.soar.mvpsample.view.LoadingView;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;

public interface AndroidPlayContract {
    /**
     * V视图层
     */
    interface IAndroidPlayView extends BaseNaviView {

        void showSnackbar(String msg);

        void viewState(int i, LoadingView.State empty);//界面状态

        void notifyBanner(List<?> datas, List<String> titles);

        void notifyData(List<ArticlesBean> datas);//刷新条目

        void stopRefresh();//停止刷新
    }

    /**
     * P视图与逻辑处理的连接层
     */
    interface IAndroidPlayPresenter extends BasePresenter {
        void getBannerData();

        void getHomeList(boolean isRefreshOrLoad);

        void onRefresh();

        void onLoadMore();
    }

    /**
     * 逻辑处理层
     */
    interface IAndroidPlayModel {
        void getBannerData(LifecycleProvider<ActivityEvent> lifecycle, OnHttpCallBack<List<AndroidPlayBannerBean>> callBack);
    }
}
