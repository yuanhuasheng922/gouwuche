package com.example.yuan.day20.utils;

import android.os.Handler;

import com.example.yuan.day20.callback.ICallBack;
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

public class OkHttps {
    //单例
    public static OkHttps instance;
    private final OkHttpClient client;

    public static OkHttps getInstance()
    {
        if (instance==null)
        {
            synchronized (OkHttps.class)
            {
                instance=new OkHttps();
            }
        }
        return instance;
    }
    //handler
    private Handler handler=new Handler();

    public OkHttps()
    {
        HttpLoggingInterceptor interceptor=new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        client = new OkHttpClient.Builder()
                .readTimeout(10,TimeUnit.SECONDS)
                .writeTimeout(10,TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .connectTimeout(10,TimeUnit.SECONDS)
                .build();
    }

    public void postEnqueue(String url, final Class clazz, Map<String,String> params, final ICallBack callBack)
    {
        FormBody.Builder builder =new FormBody.Builder();
        for (Map.Entry<String,String> entry :params.entrySet())
        {
            builder.add(entry.getKey(),entry.getValue());
        }

       RequestBody body = builder.build();
        Request request=new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onfail(e);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                try {


                String s = response.body().string();
                Gson gson=new Gson();
                final Object o = gson.fromJson(s, clazz);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onsuccess(o);
                    }
                });
                }catch (Exception e)
                {
                    callBack.onfail(e);
                }
            }
        });
    }
}
