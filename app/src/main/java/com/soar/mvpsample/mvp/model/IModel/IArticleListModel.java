package com.soar.mvpsample.mvp.model.IModel;


import com.soar.mvpsample.bean.DataBean;
import com.soar.mvpsample.impl.OnHttpCallBack;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * 共用模块 ArticleList列表
 */
public interface IArticleListModel {
    void getArticleList(LifecycleProvider<ActivityEvent> lifecycle,Integer pageIndex,Integer cid, OnHttpCallBack<DataBean> callBack);
}
