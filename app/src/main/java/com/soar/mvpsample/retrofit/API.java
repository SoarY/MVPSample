package com.soar.mvpsample.retrofit;


import com.soar.mvpsample.bean.AndroidPlayBannerBean;
import com.soar.mvpsample.bean.DataBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * YONG_
 */
public interface API {

    /**
     * 玩安卓轮播图
     */
    @GET("banner/json")
    Observable<BaseResultBean<List<AndroidPlayBannerBean>>> getAndroidPlayBanner();

    /**
     * 玩安卓，文章列表、知识体系下的文章
     *
     * @param page 页码，从0开始
     * @param cid  体系id
     */
    @GET("article/list/{page}/json")
    Observable<BaseResultBean<DataBean>> getHomeList(@Path("page") int page, @Query("cid") Integer cid);
}
