package com.example.test.mynewapptwo.utils;

import android.content.Context;

import com.example.test.mynewapptwo.model.TouTiaoBean;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

import java.io.IOException;


/**
 * Created by 11942 on 2017/11/13.
 */

public class Net {

    private Context context;
    private TouTiaoBean touTiaoBean;

    public Net(Context context) {
        this.context = context;
    }

    public void getTouTiao(){
//        OkHttpClient okHttpClient = new OkHttpClient();
//        Request request = new Request.Builder()
//                .url(UrlConstant.URL_TOUTIAO)
//                .build();
//
//        Call call = okHttpClient.newCall(request);
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.v(Net.class.getSimpleName(),"失败");
//            }
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                Gson gson = new Gson();
//                touTiaoBean = gson.fromJson(response.body().string(), TouTiaoBean.class);
//            }
//        });
//        OkGo.<TouTiaoBean>get(UrlConstant.URL_TOUTIAO)
//                .tag(context)
//                .execute(new Callback<TouTiaoBean>() {
//                    @Override
//                    public TouTiaoBean convertResponse(okhttp3.Response response) throws Throwable {
//                        Gson gson = new Gson();
//                        touTiaoBean = gson.fromJson(response.body().string(), TouTiaoBean.class);
//
//                        Log.v("Net",response.body().string());
//                        return touTiaoBean;
//                    }
//
//                    @Override
//                    public void onStart(Request<TouTiaoBean, ? extends Request> request) {
//
//                    }
//
//                    @Override
//                    public void onSuccess(Response<TouTiaoBean> response) {
//                        touTiaoBean = response.body();
//                    }
//
//                    @Override
//                    public void onCacheSuccess(Response<TouTiaoBean> response) {
//
//                    }
//
//                    @Override
//                    public void onError(Response<TouTiaoBean> response) {
//                        Log.v("Net",response.message());
//                    }
//
//                    @Override
//                    public void onFinish() {
//
//                    }
//
//                    @Override
//                    public void uploadProgress(Progress progress) {
//
//                    }
//
//                    @Override
//                    public void downloadProgress(Progress progress) {
//
//                    }
//                });

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    okhttp3.Response response = OkGo.<TouTiaoBean>get(UrlConstant.URL_TOUTIAO)
                            .tag(context)
                            .execute();
                    Gson gson = new Gson();
                    touTiaoBean = gson.fromJson(response.body().string(), TouTiaoBean.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public TouTiaoBean getTouTiaoBean() {
        return touTiaoBean;
    }
}
