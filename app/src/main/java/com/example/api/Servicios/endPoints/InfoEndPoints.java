package com.example.api.Servicios.endPoints;

import com.example.api.Servicios.dataResponse.infoResponse;
import com.example.api.Servicios.dataResponse.infoResponsePost;
import com.example.api.Servicios.models.infoUsuarios;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InfoEndPoints {
    @Headers("code-app: 2022*01")
    @GET("api/users")
    Call<infoResponse> getInfo();

    @Headers("code-app: 2022*01")
    @GET("api/users/{id}")
    Call<infoResponsePost> getDetailUser(@Path("id") String id);

    @Headers("code-app: 2022*01")
    @POST("api/users")
    Call<infoResponsePost> getInfoPost(@Body infoUsuarios infoUsuarios);

    @Headers("code-app: 2022*01")
    @PUT("api/users/{id}")
    Call<infoResponsePost> updatedata(@Path("id") int id, @Body infoUsuarios infoUsuarios);

    @Headers("code-app: 2022*01")
    @DELETE("api/users/{id}")
    Call<infoResponsePost> deletedata(@Path("id") String id);


}
