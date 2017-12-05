package com.example.test.mynewapptwo.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.test.mynewapptwo.R;
import com.example.test.mynewapptwo.adapter.RecycleAdapter;
import com.example.test.mynewapptwo.bus.RxBus;
import com.example.test.mynewapptwo.model.TouTiaoBean;
import com.example.test.mynewapptwo.presenter.TouTiaoFragmentPresenter;
import com.example.test.mynewapptwo.viewinterface.TouTiaoFragmentInterface;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by 11942 on 2017/11/14.
 */

public class TouTiaoFragment extends Fragment implements TouTiaoFragmentInterface {

     RecyclerView recyclerView;
     Context context;
     TouTiaoFragmentPresenter touTiaoFragmentPresenter;
     SwipeRefreshLayout swipeRefreshLayout;
     Disposable disposable;
     RecycleAdapter  recycleAdapter;
     LinearLayoutManager linearLayoutManager;
     TouTiaoBean touTiaoBean;
     Boolean loadding = false;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        touTiaoFragmentPresenter = new TouTiaoFragmentPresenter(this);
        this.context = context;
        linearLayoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL,false);
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
        swipeRefreshLayout = view.findViewById(R.id.fragment_swip);
        swipeRefreshLayout.setColorSchemeResources(R.color.colorAccent,R.color.colorPrimary,R.color.colorPrimaryDark);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        showTouTiao().subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new Consumer<TouTiaoBean>() {
//                    @Override
//                    public void accept(TouTiaoBean Bean) throws Exception {
//                        recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));
//                        recyclerView.setAdapter(new RecycleAdapter(context, Bean));
//                    }
//                });
        showTouTiao();
        disposable = RxBus.getDefault().toObservable(TouTiaoBean.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<TouTiaoBean>() {
                    @Override
                    public void accept(TouTiaoBean touTiao) throws Exception {
                        touTiaoBean = touTiao;
                        recyclerView.setLayoutManager(linearLayoutManager);
                        recycleAdapter = new RecycleAdapter(context, touTiaoBean);
                        recyclerView.setAdapter(recycleAdapter);
                    }
                });
    }

    @Override
    public void onResume() {
        super.onResume();
       recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {

           @Override
           public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
               super.onScrollStateChanged(recyclerView, newState);
               if (recyclerView.SCROLL_STATE_DRAGGING == newState||recyclerView.SCROLL_STATE_SETTLING == newState){
                   loadding = false;
               }
           }

           @Override
           public void onScrolled(final RecyclerView recyclerView, int dx, int dy) {
               super.onScrolled(recyclerView, dx, dy);
               int childCount = linearLayoutManager.getChildCount();
               int itemCount = linearLayoutManager.getItemCount();
               int firstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();

               if (!loadding && firstVisibleItemPosition+childCount>=itemCount){
                   loadding = true;
                   showTouTiao();
                   RxBus.getDefault().toObservable(TouTiaoBean.class)
                           .observeOn(AndroidSchedulers.mainThread())
                           .subscribe(new Consumer<TouTiaoBean>() {
                               @Override
                               public void accept(TouTiaoBean touTiao) throws Exception {
                                  touTiaoBean = touTiao;
                                  touTiaoBean.getResult().getData().addAll(touTiao.getResult().getData());
                                  recycleAdapter.notifyDataSetChanged();
                               }
                           });
               }
           }
       });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                showTouTiao();
                RxBus.getDefault().toObservable(TouTiaoBean.class)
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<TouTiaoBean>() {
                            @Override
                            public void accept(TouTiaoBean touTiao) throws Exception {
                                touTiaoBean = touTiao;
                                recycleAdapter.notifyDataSetChanged();
                                swipeRefreshLayout.setRefreshing(false);
                            }
                        });
            }
        });
    }

    @Override
    public void showTouTiao() {
        touTiaoFragmentPresenter.loadTouTiao();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (!disposable.isDisposed()){
            disposable.dispose();
        }
    }
}
