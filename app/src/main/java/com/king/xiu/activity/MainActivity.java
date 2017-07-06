package com.king.xiu.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.king.xiu.R;
import com.king.xiu.service.MovieService;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity{

    private String TAG = "huxiubo";
    private String URL= "https://api.douban.com/v2/movie/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .build();

        Button btn = (Button)this.findViewById(R.id.btn_ok);
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
                 .build();
        MovieService movieService = retrofit.create(MovieService.class);
        Call<ResponseBody> call = movieService.getTopMovie(0, 2);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                Log.d(TAG, "respose = ");
//                try {
////                    Log.d(TAG, "respose = " + response.body().string());
//                    Log.d(TAG, "respose = ");
//
//                    Toast.makeText(MainActivity.this, "hello,world", Toast.LENGTH_LONG);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                 Log.d(TAG, "onFailure = " + t.getMessage());
            }
        });
    }
}
