package com.soar.mvpsample.mvp.model;


import com.soar.mvpsample.bean.AndroidPlayBannerBean;
import com.soar.mvpsample.bean.DataBean;
import com.soar.mvpsample.impl.OnHttpCallBack;
import com.soar.mvpsample.mvp.contract.AndroidPlayContract;
import com.soar.mvpsample.retrofit.APIException;
import com.soar.mvpsample.retrofit.APIMain;
import com.soar.mvpsample.retrofit.HttpResultFunc;
import com.soar.mvpsample.retrofit.MyObserver;
import com.soar.mvpsample.retrofit.RetrofitClient;
import com.soar.mvpsample.retrofit.ServerResultFunc;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

public class AndroidPlayModel implements AndroidPlayContract.IAndroidPlayModel {

    @Override
    public void getBannerData(LifecycleProvider<ActivityEvent> lifecycle, OnHttpCallBack<List<AndroidPlayBannerBean>> callBack) {
        RetrofitClient instance = RetrofitClient.getInstance();
        Observable<List<AndroidPlayBannerBean>> observable = instance.getApi(APIMain.API_PLAY_ANDROID).getAndroidPlayBanner()
                .map(new ServerResultFunc<>())
                .onErrorResumeNext(new HttpResultFunc<>());
        instance.toSubscribe(lifecycle, observable, new MyObserver<List<AndroidPlayBannerBean>>() {

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                callBack.onSubscribe(d);
            }

            @Override
            public void onNext(List<AndroidPlayBannerBean> data) {
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
