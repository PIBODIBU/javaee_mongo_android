package com.android.javaeemongodb.ui.activity;

import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.android.javaeemongodb.R;
import com.android.javaeemongodb.data.api.RetrofitAPI;
import com.android.javaeemongodb.data.model.ErrorModel;
import com.android.javaeemongodb.data.model.MedicineModel;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ModelAddActivity extends AppCompatActivity {
    private final String TAG = getClass().getSimpleName();

    @BindView(R.id.root_view)
    public CoordinatorLayout rootView;

    @BindView(R.id.til_model_name)
    public TextInputLayout TILName;

    @BindView(R.id.til_model_description)
    public TextInputLayout TILDescription;

    @BindView(R.id.til_model_indication)
    public TextInputLayout TILIndication;

    @BindView(R.id.til_model_contraindication)
    public TextInputLayout TILContraindication;

    @BindView(R.id.til_model_sales_form)
    public TextInputLayout TILSalesForm;

    private MedicineModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_model_add);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.fab_add)
    public void addModel() {
        setModel(new MedicineModel(null,
                TILName.getEditText().getText().toString(),
                TILDescription.getEditText().getText().toString(),
                TILIndication.getEditText().getText().toString(),
                TILContraindication.getEditText().getText().toString(),
                TILSalesForm.getEditText().getText().toString()));

        RetrofitAPI.getInstance(this).addModel(
                getModel().getName(),
                getModel().getDescription(),
                getModel().getIndication(),
                getModel().getContraindication(),
                getModel().getSalesForm()
        ).enqueue(new Callback<ErrorModel>() {
            @Override
            public void onResponse(Call<ErrorModel> call, Response<ErrorModel> response) {
                if (response != null && response.body() != null && !response.body().getError()) {
                    setResult(RESULT_OK);
                    finish();
                } else {
                    Snackbar.make(rootView, R.string.snack_bar_model_not_added, Snackbar.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<ErrorModel> call, Throwable t) {
                Log.e(TAG, "onFailure()-> ", t);
                Snackbar.make(rootView, R.string.snack_bar_model_not_added, Snackbar.LENGTH_LONG).show();
            }
        });
    }

    public void setModel(MedicineModel model) {
        this.model = model;
    }

    public MedicineModel getModel() {
        return model;
    }
}
