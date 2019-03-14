package com.soar.mvpsample.base;

/**
 * 公共Presenter类
 * Created by on 2017/10/17.
 *
 * @author
 */

public interface BasePresenter {
    /**
     * 当页面销毁的时候,需要把View=null(避免内存泄漏)
     */
    void onDestroy();
}
