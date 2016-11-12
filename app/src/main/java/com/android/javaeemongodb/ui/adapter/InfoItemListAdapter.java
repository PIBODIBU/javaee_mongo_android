package com.android.javaeemongodb.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.model.InfoItemModel;
import com.android.javaeemongodb.ui.viewholder.InfoItemViewHolder;

import java.util.ArrayList;

public class InfoItemListAdapter extends RecyclerView.Adapter<InfoItemViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private ArrayList<InfoItemModel> dataSet;
    private Context context;

    public InfoItemListAdapter(ArrayList<InfoItemModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public InfoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InfoItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_info, parent, false));
    }

    @Override
    public void onBindViewHolder(InfoItemViewHolder holder, int position) {
        if (dataSet == null) {
            Log.e(TAG, "onBindViewHolder()-> data set is null");
            return;
        }

        InfoItemModel model = dataSet.get(position);

        if (model == null) {
            Log.e(TAG, "onBindViewHolder()-> model is null");
            return;
        }

        if (holder.IVImage != null) {
            holder.IVImage.setImageResource(model.getImageRes());
        }

        if (holder.TVTitle != null) {
            holder.TVTitle.setText(model.getTitle());
        }

        if (holder.TVDescription != null) {
            holder.TVDescription.setText(model.getDescription());
        }
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? 0 : dataSet.size();
    }

    public ArrayList<InfoItemModel> getDataSet() {
        return dataSet;
    }
}
