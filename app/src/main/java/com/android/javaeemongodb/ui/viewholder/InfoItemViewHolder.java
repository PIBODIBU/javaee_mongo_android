package com.android.javaeemongodb.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.javaeemongodb.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.image_view)
    public ImageView IVImage;

    @BindView(R.id.tv_title)
    public TextView TVTitle;

    @BindView(R.id.tv_description)
    public TextView TVDescription;


    public InfoItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
