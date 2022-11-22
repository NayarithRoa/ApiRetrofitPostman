package com.example.api.Servicios.dataResponse;

import com.example.api.Servicios.models.infoUsuarios;
import com.google.gson.annotations.SerializedName;

public class infoResponsePut {
    @SerializedName("date") public Object date;
    @SerializedName("data") public infoUsuarios data;
}
