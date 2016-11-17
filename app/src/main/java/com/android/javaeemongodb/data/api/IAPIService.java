package com.android.javaeemongodb.data.api;

import com.android.javaeemongodb.data.model.ErrorModel;
import com.android.javaeemongodb.data.model.MedicineModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface IAPIService {
    @GET("documents")
    Call<List<MedicineModel>> getModels();

    @GET("documents/{docId}")
    Call<MedicineModel> getModel(@Path("docId") String docId);

    @FormUrlEncoded
    @POST("documents/add")
    Call<ErrorModel> addModel(@Field("name") String name,
                              @Field("description") String description,
                              @Field("indication") String indication,
                              @Field("contraindication") String contraindication,
                              @Field("sales_form") String sales_form);

    @GET("documents/delete")
    Call<ErrorModel> deleteModel(@Query("ids") String docId);
}