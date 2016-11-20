package com.android.javaeemongodb.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.model.MedicineModel;
import com.android.javaeemongodb.ui.viewholder.DocListItemViewHolder;

import java.util.ArrayList;

public class DocumentListAdapter extends RecyclerView.Adapter<DocListItemViewHolder> {
    private final String TAG = getClass().getSimpleName();
    private final Integer MAX_LINES_COUNT = 3;

    private Boolean selectedModeActivated = false;
    private Context context;
    private ArrayList<MedicineModel> dataSet;
    private ArrayList<MedicineModel> selectedModels;

    private OnItemClickListener onItemClickListener;
    private OnItemOptionsClickListener onItemOptionsClickListener;
    private OnSelectionModeActivationListener onSelectionModeActivationListener;

    public DocumentListAdapter(Context context, @NonNull ArrayList<MedicineModel> dataSet) {
        this.context = context;
        this.dataSet = dataSet;
        selectedModels = new ArrayList<>();
    }

    @Override
    public DocListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DocListItemViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_document, parent, false));
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

        final PopupMenu popupMenu = new PopupMenu(context, holder.IBPopup);
        popupMenu.inflate(R.menu.menu_popup_card_medicine);

        if (holder.RLRootView != null) {
            holder.RLRootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (isSelectedModeActivated()) {
                        Boolean modelNewState = !model.isSelected();

                        if (!modelNewState) {
                            selectedModels.remove(model);
                        } else {
                            selectedModels.add(model);
                        }

                        model.setSelected(modelNewState);

                        Log.d(TAG, "onClick()-> " + selectedModels.size());

                        if (selectedModels.size() == 0) {
                            if (onSelectionModeActivationListener != null) {
                                onSelectionModeActivationListener.onDeactivated();
                            }

                            setSelectedModeActivated(false);
                        }

                        notifyDataSetChanged();
                    } else {
                        if (onItemClickListener != null) {
                            onItemClickListener.onItemClick(model);
                        }
                    }
                }
            });

            holder.RLRootView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    if (!model.isSelected()) {
                        if (onSelectionModeActivationListener != null) {
                            onSelectionModeActivationListener.onActivated();
                        }

                        setSelectedModeActivated(true);
                        model.setSelected(true);
                        selectedModels.add(model);
                        notifyDataSetChanged();
                    } else {
                      /*  if (onItemClickListener != null) {
                            onItemClickListener.onItemLongClick(model);
                            return true;
                        }*/
                    }

                    return true;
                }
            });

            holder.RLRootView.setBackground(ContextCompat.getDrawable(context,
                    model.isSelected() ? R.drawable.selector_card_bg_invert : R.drawable.selector_card_bg));
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
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.action_edit:
                                if (onItemOptionsClickListener != null) {
                                    onItemOptionsClickListener.onEdit(holder.getAdapterPosition(), model);
                                }
                                break;
                            case R.id.action_delete:
                                if (onItemOptionsClickListener != null) {
                                    onItemOptionsClickListener.onDelete(holder.getAdapterPosition(), model);
                                }
                                break;
                            default:
                                break;
                        }
                        return false;
                    }
                });
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

    public void setDataSet(ArrayList<MedicineModel> dataSet) {
        this.dataSet = dataSet;
    }

    public interface OnItemClickListener {
        void onItemClick(MedicineModel model);

        void onItemLongClick(MedicineModel model);
    }

    public interface OnItemOptionsClickListener {
        void onDelete(int position, MedicineModel model);

        void onEdit(int position, MedicineModel model);
    }

    public interface OnSelectionModeActivationListener {
        void onActivated();

        void onDeactivated();
    }

    public void setOnSelectionModeActivationListener(OnSelectionModeActivationListener onSelectionModeActivationListener) {
        this.onSelectionModeActivationListener = onSelectionModeActivationListener;
    }

    public void setOnItemOptionsClickListener(OnItemOptionsClickListener onItemOptionsClickListener) {
        this.onItemOptionsClickListener = onItemOptionsClickListener;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public void setSelectedModeActivated(Boolean selectedModeActivated) {
        this.selectedModeActivated = selectedModeActivated;
    }

    public Boolean isSelectedModeActivated() {
        return selectedModeActivated;
    }

    public ArrayList<MedicineModel> getSelectedModels() {
        return selectedModels;
    }

    public void deactivateSelectionMode() {
        /*for (MedicineModel model : dataSet) {
            model.setSelected(false);
        }*/

        setSelectedModeActivated(false);
        selectedModels.clear();
//        notifyDataSetChanged();
    }
}
