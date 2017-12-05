package com.example.test.mynewapptwo.presenter;

import com.example.test.mynewapptwo.model.TouTiaoBean;
import com.example.test.mynewapptwo.model.TouTiaoModel;
import com.example.test.mynewapptwo.viewinterface.TouTiaoFragmentInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by 11942 on 2017/11/14.
 */

public class TouTiaoFragmentPresenter {

    private TouTiaoFragmentInterface tiaoFragment;
    private TouTiaoModel touTiaoModel;

    public TouTiaoFragmentPresenter(TouTiaoFragmentInterface tiaoFragment) {
        touTiaoModel = new TouTiaoModel();
        this.tiaoFragment = tiaoFragment;
    }

    public void loadTouTiao(){
       touTiaoModel.loadTouTiao();
    }
}
