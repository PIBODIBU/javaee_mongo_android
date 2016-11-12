package com.android.javaeemongodb.data.api;

import com.android.javaeemongodb.data.model.InfoItemModel;
import com.android.javaeemongodb.data.model.MedicineModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface IAPIService {
    @GET("documents")
    Call<List<MedicineModel>> getMedicineModels();

    @GET("documents/{docId}")
    Call<MedicineModel> getMedicine(@Path("docId") String docId);

/*
    @FormUrlEncoded
    @POST("my/profile/update")
    Call<User> updateMyInfo(@Field("token") String token, @Field("name") String name, @Field("email") String email);
*/
}