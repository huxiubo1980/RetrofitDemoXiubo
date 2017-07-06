package com.king.xiu.activity;
/**
 * Created by huxiubo on 17/7/6.
 */

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.king.xiu.R;
import com.king.xiu.data.MovieEntity;
import com.king.xiu.service.MovieService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private String TAG = "huxiubo";
    private String URL = "https://api.douban.com/v2/movie/";
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .build();

        Button btn = (Button) this.findViewById(R.id.btn_ok);
        tv = (TextView) this.findViewById(R.id.tv);
        btn.setText("点击执行网络操作");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                execRetrofix();
            }
        });
    }

    void execRetrofix() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        /**这里是interface，so,需要创建一个代理*/
        MovieService movieService = retrofit.create(MovieService.class);
        /**拿到代理对象后就可以调用方法了*/
        Call<MovieEntity> call = movieService.getTopMovie(0, 2);
        call.enqueue(new Callback<MovieEntity>() {
            @Override
            public void onResponse(Call<MovieEntity> call, Response<MovieEntity> response) {
                Log.d(TAG, "respose = " + response.body().getTitle());
                tv.setText(response.body().getTitle());
            }

            @Override
            public void onFailure(Call<MovieEntity> call, Throwable t) {
                Log.d(TAG, "onFailure = " + t.getMessage());
            }
        });
    }
}
