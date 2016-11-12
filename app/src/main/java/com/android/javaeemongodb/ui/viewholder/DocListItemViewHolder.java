package com.android.javaeemongodb.ui.viewholder;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.javaeemongodb.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DocListItemViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.card_view)
    public CardView cardView;

    @BindView(R.id.rl_root)
    public RelativeLayout RLRootView;

    @BindView(R.id.tv_title)
    public TextView TVTitle;

    @BindView(R.id.tv_subtitle)
    public TextView TVSubTitle;

    @BindView(R.id.tv_description)
    public TextView TVDescription;

    @BindView(R.id.ib_popup_menu)
    public ImageButton IBPopup;

    public DocListItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
