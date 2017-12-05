package com.example.test.mynewapptwo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.mynewapptwo.R;
import com.example.test.mynewapptwo.adapter.RecycleAdapter;
import com.example.test.mynewapptwo.model.TouTiaoBean;
import com.example.test.mynewapptwo.utils.UrlConstant;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

import java.io.IOException;

import okhttp3.Response;

/**
 * Created by 11942 on 2017/11/14.
 */

public class SocialFragment extends Fragment {

     RecyclerView recyclerView;
     TouTiaoBean touTiaoBean;
     Context context;
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            touTiaoBean = (TouTiaoBean) msg.obj;
            Log.v("jjjjjjjjjjjjjjj", touTiaoBean.getResult().getData().toString());
            recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
            recyclerView.setAdapter(new RecycleAdapter(context, touTiaoBean));
        }
    };

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_layout, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recycleView);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = OkGo.<TouTiaoBean>get(UrlConstant.URL_SOCIAL)
                            .tag(context)
                            .execute();
                    Gson gson = new Gson();
                    Message message = handler.obtainMessage();
                    message.obj = gson.fromJson(response.body().string(), TouTiaoBean.class);
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
