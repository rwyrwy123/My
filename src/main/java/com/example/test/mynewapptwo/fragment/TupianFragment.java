package com.example.test.mynewapptwo.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.mynewapptwo.R;
import com.example.test.mynewapptwo.adapter.RecycleTupianAdapter;
import com.example.test.mynewapptwo.model.TouTiaoBean;
import com.example.test.mynewapptwo.model.TupianBean;
import com.example.test.mynewapptwo.utils.UrlConstant;
import com.google.gson.Gson;
import com.lzy.okgo.OkGo;

import java.io.IOException;
import java.util.List;

import okhttp3.Response;

/**
 * Created by 11942 on 2017/11/15.
 */

public class TupianFragment extends Fragment {

    private List<TupianBean> tupianBeanList;
    private Context context;
    private RecyclerView recyclerView;
    private TupianBean tupianBean;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecycleTupianAdapter recycleTupianAdapter;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what){
                case 1:
                    tupianBean = (TupianBean) msg.obj;
                    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
                    recycleTupianAdapter = new RecycleTupianAdapter(context, tupianBean);
                    recyclerView.setAdapter(recycleTupianAdapter);
                case 2:
                    List<TupianBean.ShowapiResBodyBean.NewslistBean> newslist = ((TupianBean) msg.obj).getShowapi_res_body().getNewslist();
                    tupianBean.getShowapi_res_body().getNewslist().addAll(newslist);
                    recycleTupianAdapter.notifyDataSetChanged();
                    swipeRefreshLayout.setRefreshing(false);
            }
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
        View view = inflater.inflate(R.layout.fragment_tupian_layout, null, false);
        swipeRefreshLayout = view.findViewById(R.id.tupian_swipfresh);
        recyclerView = view.findViewById(R.id.tupian_recycle);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = OkGo.<TouTiaoBean>get(UrlConstant.URL_TUPIAN)
                            .tag(context)
                            .execute();
                    Gson gson = new Gson();
                    Message message = handler.obtainMessage();
                    message.what = 1;
                    message.obj = gson.fromJson(response.body().string(), TupianBean.class);
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void onResume() {
        super.onResume();
        swipeRefreshLayout.setColorSchemeColors(context.getColor(R.color.colorAccent),context.getColor(R.color.colorPrimary),context.getColor(R.color.colorTitle));
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        swipeRefreshLayout.setRefreshing(false);
//                    }
//                },3000);
                addData();
            }
        });
    }

    private void addData() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Response response = OkGo.<TouTiaoBean>get(UrlConstant.URL_TUPIAN2)
                            .tag(context)
                            .execute();
                    Gson gson = new Gson();
                    Message message = handler.obtainMessage();
                    message.what = 2;
                    message.obj = gson.fromJson(response.body().string(), TupianBean.class);
                    handler.sendMessage(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
