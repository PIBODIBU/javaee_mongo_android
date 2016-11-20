package com.android.javaeemongodb.ui.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.android.javaeemongodb.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class InfoViewHolder extends RecyclerView.ViewHolder {
    @BindView(R.id.tv_title)
    public TextView TVTitle;

    @BindView(R.id.tv_value)
    public TextView TVValue;

    public InfoViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
