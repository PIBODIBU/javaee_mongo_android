package com.android.javaeemongodb.data.api;

import com.android.javaeemongodb.data.model.MedicineModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface IAPIService {
    @GET("documents")
    Call<List<MedicineModel>> getMedicineModels();

/*
    @FormUrlEncoded
    @POST("my/profile/update")
    Call<User> updateMyInfo(@Field("token") String token, @Field("name") String name, @Field("email") String email);
*/
}