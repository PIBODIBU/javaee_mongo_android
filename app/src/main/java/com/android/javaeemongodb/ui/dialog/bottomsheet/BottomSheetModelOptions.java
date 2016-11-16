package com.android.javaeemongodb.ui.dialog.bottomsheet;

import android.app.Dialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

import com.android.javaeemongodb.R;

import butterknife.ButterKnife;

public class BottomSheetModelOptions extends BottomSheetDialogFragment {
    private OnItemClickedListener onItemClickedListener;

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        View contentView = View.inflate(getContext(), R.layout.fragment_dialog_model_options, null);

        ButterKnife.bind(this, contentView);
        dialog.setContentView(contentView);
    }

    public void setOnItemClickedListener(OnItemClickedListener onItemClickedListener) {
        this.onItemClickedListener = onItemClickedListener;
    }

    public interface OnItemClickedListener {
        void pickImageFromGallery();

        void takePhotoFromCamera();
    }
}
