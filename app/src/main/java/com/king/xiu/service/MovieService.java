package com.king.xiu.service;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by huxiubo on 17/7/6.
 */

public interface MovieService {
//    @GET("http://ip.taobao.com/service/getIpInfo.php")
//    Call<ResponseBody> getTopMovie(@Query("ip") String ip);

    @GET("top250")
    Call<ResponseBody> getTopMovie(@Query("start") int start, @Query("count") int count);
}
