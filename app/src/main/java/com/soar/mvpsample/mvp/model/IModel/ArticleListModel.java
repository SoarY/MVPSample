package com.soar.mvpsample.mvp.model.IModel;


import com.soar.mvpsample.bean.DataBean;
import com.soar.mvpsample.impl.OnHttpCallBack;
import com.soar.mvpsample.retrofit.APIException;
import com.soar.mvpsample.retrofit.APIMain;
import com.soar.mvpsample.retrofit.HttpResultFunc;
import com.soar.mvpsample.retrofit.MyObserver;
import com.soar.mvpsample.retrofit.RetrofitClient;
import com.soar.mvpsample.retrofit.ServerResultFunc;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class ArticleListModel implements IArticleListModel {

    @Override
    public void getArticleList(LifecycleProvider<ActivityEvent> lifecycle, Integer pageIndex,Integer cid, OnHttpCallBack<DataBean> callBack) {
        RetrofitClient instance = RetrofitClient.getInstance();
        Observable<DataBean> observable = instance.getApi(APIMain.API_PLAY_ANDROID).getHomeList(pageIndex, cid)
                .map(new ServerResultFunc<>())
                .onErrorResumeNext(new HttpResultFunc<>());
        instance.toSubscribe(lifecycle, observable, new MyObserver<DataBean>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                callBack.onSubscribe(d);
            }

            @Override
            public void onNext(DataBean data) {
                callBack.onNext(data);
            }

            @Override
            protected void onError(APIException ex) {
                callBack.onError(ex);
                onComplete();
            }

            /**
             * onError调用onComplete后,此方法大多用于释放资源或者关闭dialog.
             */
            @Override
            public void onComplete() {
                callBack.onComplete();
            }
        });
    }
}
