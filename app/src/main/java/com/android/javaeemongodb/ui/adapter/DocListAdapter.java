package com.android.javaeemongodb.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.ui.viewholder.DocListItemViewHolder;

import java.util.ArrayList;

public class DocListAdapter extends RecyclerView.Adapter<DocListItemViewHolder> {
    private final String TAG = getClass().getSimpleName();

    private Context context;
    private ArrayList<MedicineModel> dataSet;

    private OnClickListener onClickListener;

    public DocListAdapter(Context context, @NonNull ArrayList<MedicineModel> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
    }

    @Override
    public DocListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DocListItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medicine, parent, false));
    }

    @Override
    public void onBindViewHolder(final DocListItemViewHolder holder, int position) {
        if (dataSet == null) {
            Log.e(TAG, "onBindViewHolder()-> data set is null");
            return;
        }

        final MedicineModel model = dataSet.get(position);

        if (model == null) {
            Log.e(TAG, "onBindViewHolder()-> model is null");
            return;
        }

        if (holder.RLRootView != null) {
            holder.RLRootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onClickListener != null) {
                        onClickListener.onItemClickListener(model);
                    }
                }
            });
        }

        if (holder.TVTitle != null) {
            holder.TVTitle.setText(model.getName());
        }

        if (holder.TVSubTitle != null) {
            holder.TVSubTitle.setText(model.getId());
        }

        if (holder.TVDescription != null) {
            holder.TVDescription.setText(model.getIndication());
        }

        holder.IBPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupMenu popupMenu = new PopupMenu(context, holder.IBPopup);
                popupMenu.inflate(R.menu.menu_popup_card_medicine);
                popupMenu.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataSet == null ? -1 : dataSet.size();
    }

    public ArrayList<MedicineModel> getDataSet() {
        return dataSet;
    }

    public interface OnClickListener {
        void onItemClickListener(MedicineModel model);
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }
}
