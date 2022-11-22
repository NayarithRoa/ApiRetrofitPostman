package com.example.api.Servicios.dataResponse;

import com.example.api.Servicios.models.infoUsuarios;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class infoResponse {
    @SerializedName("date") public Object date;
    @SerializedName("data") public List<infoUsuarios> data;
}
