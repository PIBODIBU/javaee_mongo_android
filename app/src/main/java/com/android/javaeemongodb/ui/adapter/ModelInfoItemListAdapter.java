package com.android.javaeemongodb.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.model.InfoItemModel;
import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.ui.viewholder.InfoItemViewHolder;

import java.util.ArrayList;
import java.util.LinkedList;

public class ModelInfoItemListAdapter extends RecyclerView.Adapter<InfoItemViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private LinkedList<InfoItemModel> dataSet;
    private Context context;
    private OnItemClickListener onItemClickListener;

    public ModelInfoItemListAdapter(LinkedList<InfoItemModel> dataSet, Context context) {
        this.dataSet = dataSet;
        this.context = context;
    }

    @Override
    public InfoItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new InfoItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_model_info, parent, false));
    }

    @Override
    public void onBindViewHolder(InfoItemViewHolder holder, int position) {
        if (dataSet == null) {
            Log.e(TAG, "onBindViewHolder()-> data set is null");
            return;
        }

        final InfoItemModel model = dataSet.get(position);

        if (model == null) {
            Log.e(TAG, "onBindViewHolder()-> model is null");
            return;
        }

        if (holder.rootView != null) {
            holder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(model);
                    }
                }
            });

            holder.rootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemLongClick(model);
                        return true;
                    }
                    return false;
                }
            });
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

    public LinkedList<InfoItemModel> getDataSet() {
        return dataSet;
    }

    public interface OnItemClickListener {
        void onItemClick(InfoItemModel model);

        void onItemLongClick(InfoItemModel model);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }
}
