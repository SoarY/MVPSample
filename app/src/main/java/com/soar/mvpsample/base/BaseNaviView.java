package com.soar.mvpsample.base;


import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

public interface BaseNaviView {
    LifecycleProvider<ActivityEvent> getNaviLifecycle();//LifecycleProvider对象，防止rxjava内存泄漏。
}
