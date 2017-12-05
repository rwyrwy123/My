package com.example.test.mynewapptwo.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.test.mynewapptwo.PhotoViewActivity;
import com.example.test.mynewapptwo.R;
import com.example.test.mynewapptwo.WebActivity;
import com.example.test.mynewapptwo.model.TupianBean;

/**
 * Created by 11942 on 2017/11/15.
 */

public class RecycleTupianAdapter extends RecyclerView.Adapter {
    private TupianBean tupianBean;
    private Context context;
    public RecycleTupianAdapter(Context context,TupianBean tupianBean) {
        this.tupianBean = tupianBean;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_tupian_layout, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(tupianBean.getShowapi_res_body().getNewslist().get(position).getPicUrl()).into(((Holder)holder).imageView);
        ((Holder)holder).title.setText(tupianBean.getShowapi_res_body().getNewslist().get(position).getTitle());
        final int current = position;
        ((Holder)holder).linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("urlContent", tupianBean.getShowapi_res_body().getNewslist().get(current).getUrl());
                context.startActivity(intent);
            }
        });
        ((Holder)holder).imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PhotoViewActivity.class);
                intent.putExtra("urlImage", tupianBean.getShowapi_res_body().getNewslist().get(current).getPicUrl());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return tupianBean.getShowapi_res_body().getNewslist().size();
    }

    public static class Holder extends RecyclerView.ViewHolder{
        LinearLayout linearLayout;
        ImageView imageView;
        TextView title;
        public Holder(View itemView) {
            super(itemView);
            linearLayout = itemView.findViewById(R.id.recycle_tupian_item);
            imageView = itemView.findViewById(R.id.tupian_image);
            title = itemView.findViewById(R.id.tupian_title);
        }
    }

}
