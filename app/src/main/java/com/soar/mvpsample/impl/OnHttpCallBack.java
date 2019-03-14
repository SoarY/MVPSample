package com.soar.mvpsample.impl;


import com.soar.mvpsample.retrofit.APIException;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 公共的请求回调监听器
 * Created by on 2017/7/29.
 */
public interface OnHttpCallBack<T> {

    void onSubscribe(@NonNull Disposable d);

    void onNext(T t);//成功了就回调这个方法,可以传递任何形式的数据给调用者

    void onError(APIException ex);//失败了就调用这个方法,可以传递错误的请求消息给调用者

    void onComplete();//这个方法在Model中复写后,大多用于释放资源或者关闭dialog
}
