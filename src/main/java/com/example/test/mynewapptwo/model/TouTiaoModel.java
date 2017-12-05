package com.example.test.mynewapptwo.model;

import android.util.Log;

import com.example.test.mynewapptwo.MyApplication;
import com.example.test.mynewapptwo.bus.RxBus;
import com.example.test.mynewapptwo.utils.UrlConstant;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.Callback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.db.CacheManager;
import com.lzy.okgo.model.Progress;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import java.io.IOException;
import java.util.concurrent.ExecutorService;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;


/**
 * Created by 11942 on 2017/11/16.
 */

public class TouTiaoModel {

    TouTiaoBean touTiaoBean;
    public void loadTouTiao(){
        ExecutorService executor = MyApplication.getExecutor();
        executor.execute(new Runnable() {
            @Override
            public void run() {
                OkGo.<String>get(UrlConstant.URL_TOUTIAO)
                        .cacheKey("toutiao")
//                        .cacheTime(1800*1000)
                        .cacheMode(CacheMode.FIRST_CACHE_THEN_REQUEST)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(Response<String> response) {
                                Gson gson = new Gson();
                                TouTiaoBean touTiao = gson.fromJson(response.body().toString(), TouTiaoBean.class);
                                RxBus.getDefault().post(touTiao);
                            }

                            @Override
                            public String convertResponse(okhttp3.Response response) throws Throwable {
                                return response.body().string();
                            }

                            @Override
                            public void onCacheSuccess(Response<String> response) {
                                CacheEntity<?> toutiao = CacheManager.getInstance().get("toutiao");
                                Gson gson = new Gson();
                                TouTiaoBean touTiao = gson.fromJson(toutiao.getData().toString(), TouTiaoBean.class);
                                RxBus.getDefault().post(touTiao);
                                super.onCacheSuccess(response);
                            }

                            @Override
                            public void onError(Response<String> response) {
                                super.onError(response);
                            }
                        });
            }
        });
    }

}
