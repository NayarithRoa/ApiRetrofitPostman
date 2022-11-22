package com.example.api;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.api.Adapter.ListaUsuariosAdapter;
import com.example.api.Servicios.dataResponse.infoResponse;
import com.example.api.Servicios.infoservicios;
import com.example.api.Servicios.models.infoUsuarios;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView superListView;
    TextView resultado;
    RecyclerView listaUsuarios;
    ListaUsuariosAdapter adapter;
    List<infoUsuarios> usuarios;
    FloatingActionButton fabAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getListadoUsuarios();

    }

    private void cargarAdapter() {
        listaUsuarios = findViewById(R.id.listaUsuarios);
        listaUsuarios.setLayoutManager(new LinearLayoutManager(this));
        adapter= new ListaUsuariosAdapter(this.usuarios);
        listaUsuarios.setAdapter(adapter);
        fabAgregar = findViewById(R.id.fabAgregar);

        fabAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Insertar.class);
                startActivity(intent);
            }
        });

    }


    private void getListadoUsuarios() {
        //SOLICITUD POSTMAN
        Call<infoResponse> call =(new infoservicios()).getInfoService();
        //RESPUESTA POSTMAN
        call.enqueue(new Callback<infoResponse>() {
            @Override
            public void onResponse(Call<infoResponse> call, Response<infoResponse> response) {
                if (response.isSuccessful()){
                    infoResponse infoResponse = response.body();
                    ArrayList<infoUsuarios> usuariosArrayList = new ArrayList<>();
                    String contenido="";

                    for (int i=0;i<infoResponse.data.size();i++){
                        usuariosArrayList.add(new infoUsuarios(
                                infoResponse.data.get(i).getId(),
                                infoResponse.data.get(i).getNames()));
                    }
                    usuarios = usuariosArrayList;
                    cargarAdapter();
                }
            }

            @Override
            public void onFailure(Call<infoResponse> call, Throwable t) {

            }
        });
    }


}