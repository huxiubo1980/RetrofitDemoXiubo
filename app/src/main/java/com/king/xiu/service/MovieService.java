package com.king.xiu.service;

import com.king.xiu.data.MovieEntity;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by huxiubo on 17/7/6.
 */

public interface MovieService {
    /**请求方法*/
    @GET("top250")
    Call<MovieEntity> getTopMovie(@Query("start") int start, @Query("count") int count);
}
