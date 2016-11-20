package com.android.javaeemongodb.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.model.InfoModel;
import com.android.javaeemongodb.ui.viewholder.InfoViewHolder;

import java.util.ArrayList;

public class InfoAdapter extends RecyclerView.Adapter<InfoViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private ArrayList<InfoModel> dataSet;

    public InfoAdapter(ArrayList<InfoModel> dataSet) {
        this.dataSet = dataSet;
    }

    @Override
    public InfoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InfoViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false));
    }

    @Override
    public void onBindViewHolder(InfoViewHolder holder, int position) {
        InfoModel model = dataSet.get(position);

        if (model == null) {
            Log.e(TAG, "onBindViewHolder()-> model is null");
            return;
        }

        holder.TVTitle.setText(model.getTitle());
        holder.TVValue.setText(model.getValue());
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }
}
