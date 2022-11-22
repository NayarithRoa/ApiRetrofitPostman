package com.example.api.Servicios;

import com.example.api.Servicios.dataResponse.infoResponse;
import com.example.api.Servicios.dataResponse.infoResponsePost;
import com.example.api.Servicios.endPoints.InfoEndPoints;
import com.example.api.Servicios.models.infoUsuarios;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class infoservicios {

    private Retrofit getRetrofit(){
        return  new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
    }

    public Call<infoResponse> getInfoService(){
        Call<infoResponse> call = this.getRetrofit().create(InfoEndPoints.class).getInfo();
        return call;
    }

    public Call<infoResponsePost> getDetailUser(String id){
        Call<infoResponsePost> call = this.getRetrofit().create(InfoEndPoints.class).getDetailUser(id);
        return call;
    }

    public Call<infoResponsePost> getInfoPost(infoUsuarios infoUsuarios){
        Call<infoResponsePost> call = this.getRetrofit().create(InfoEndPoints.class).getInfoPost(infoUsuarios);
        return call;
    }

    public Call<infoResponsePost> updatedata(int id, infoUsuarios infoUsuarios){
        Call<infoResponsePost> call = this.getRetrofit().create(InfoEndPoints.class).updatedata(id, infoUsuarios);
        return call;
    }

    public Call<infoResponsePost> deletedata(String id){
        Call<infoResponsePost> call = this.getRetrofit().create(InfoEndPoints.class).deletedata(id);
        return call;
    }

}
