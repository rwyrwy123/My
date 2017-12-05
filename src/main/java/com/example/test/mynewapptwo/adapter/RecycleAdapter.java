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

import com.example.test.mynewapptwo.WebActivity;
import com.example.test.mynewapptwo.utils.TimeUtils;
import com.bumptech.glide.Glide;
import com.example.test.mynewapptwo.R;
import com.example.test.mynewapptwo.model.TouTiaoBean;

/**
 * Created by 11942 on 2017/11/14.
 */

public class RecycleAdapter extends RecyclerView.Adapter {

    private Context context;
    private TouTiaoBean touTiaoBean;

    public RecycleAdapter(Context context, TouTiaoBean touTiaoBean) {
        this.context = context;
        this.touTiaoBean = touTiaoBean;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.recycle_content_layout, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Glide.with(context).load(touTiaoBean.getResult().getData().get(position).getThumbnail_pic_s()).into(((Holder)holder).imageView);
        ((Holder)holder).title.setText(touTiaoBean.getResult().getData().get(position).getTitle());
        ((Holder)holder).author.setText(touTiaoBean.getResult().getData().get(position).getAuthor_name());
        ((Holder)holder).date.setText(TimeUtils.getFriendlyTimeSpanByNow(touTiaoBean.getResult().getData().get(position).getDate() + ":00"));

        final int current = position;
        ((Holder)holder).linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, WebActivity.class);
                intent.putExtra("urlContent", touTiaoBean.getResult().getData().get(current).getUrl());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return touTiaoBean.getResult().getData().size();
    }


    public static class Holder extends RecyclerView.ViewHolder{

        LinearLayout linearLayout;
        ImageView imageView;
        TextView title;
        TextView author;
        TextView date;

        public Holder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.recycle_item);
            imageView = (ImageView) itemView.findViewById(R.id.recycle_content_image);
            title = (TextView) itemView.findViewById(R.id.recycle_content_title);
            author = (TextView) itemView.findViewById(R.id.recycle_content_author);
            date = (TextView) itemView.findViewById(R.id.recycle_content_date);
        }
    }

}
