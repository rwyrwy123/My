package com.example.test.mynewapptwo;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;

import com.lzy.okgo.OkGo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by 11942 on 2017/11/13.
 */

public class MyApplication extends Application {

    private static ExecutorService executorService;

    @Override
    public void onCreate() {
        super.onCreate();
        OkGo.getInstance().init(this);

        AppCompatDelegate.setDefaultNightMode(
                AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static ExecutorService getExecutor(){
        if (executorService == null){
            executorService = Executors.newFixedThreadPool(5);
        }
        return executorService;
    }

}
