package com.example.yuan.day_19.okhttps;

import android.os.Handler;

import com.example.yuan.day_19.callback.ICallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

public class OkHttp {
    //单例
    public static OkHttp instance;
    private final OkHttpClient client;

    public static OkHttp getInstance()
    {
        if (instance==null)
        {
            synchronized (OkHttp.class)
            {
                instance=new OkHttp();
            }
        }
        return instance;
    }
    //handlerd
    private Handler handler=new Handler();

    public OkHttp()
    {
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .connectTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .writeTimeout(10,TimeUnit.SECONDS)
                .readTimeout(10,TimeUnit.SECONDS)
                .build();
    }

    public void postEnqueue(String url, final Class clzz, Map<String,String> params, final ICallBack callBack)
    {
        FormBody.Builder builder=new FormBody.Builder();
        for (Map.Entry<String,String>entry :params.entrySet())
        {
            builder.add(entry.getKey(),entry.getValue());
        }

      RequestBody requestBody = builder.build();

        Request request=new Request.Builder()
                .post(requestBody)
                .url(url)
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String s = response.body().string();
                Gson gson=new Gson();
                final Object o = gson.fromJson(s, clzz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onSuccess(o);
                    }
                });
            }
        });

    }
}
