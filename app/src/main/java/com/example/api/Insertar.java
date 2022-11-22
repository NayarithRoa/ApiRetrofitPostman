package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.api.Servicios.dataResponse.infoResponse;
import com.example.api.Servicios.dataResponse.infoResponsePost;
import com.example.api.Servicios.infoservicios;
import com.example.api.Servicios.models.infoUsuarios;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Insertar extends AppCompatActivity {

    EditText txt_nombre, txt_usuario, txt_pass, txt_rol;
    TextView txtrespuesta;
    Button btninsertar;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar);

        txt_nombre = findViewById(R.id.txtnombre);
        txt_usuario = findViewById(R.id.txtusuario);
        txt_pass = findViewById(R.id.txtcontraseña);
        txt_rol = findViewById(R.id.txtrol);
        btninsertar = findViewById(R.id.btninsertar);

        btninsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // validating if the text field is empty or not.
                if (txt_nombre.getText().toString().isEmpty() || txt_usuario.getText().toString().isEmpty() || txt_pass.getText().toString().isEmpty() || txt_rol.getText().toString().isEmpty()) {
                    Toast.makeText(Insertar.this, "Por favor diligencie todos los datos", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to post the data and passing our name and job.
                postData(txt_nombre.getText().toString(), txt_usuario.getText().toString(), txt_pass.getText().toString(), txt_rol.getText().toString());
            }
        });

    }

    private void postData(String nombre, String usuario, String clave, String rol) {

        infoUsuarios modal = new infoUsuarios(nombre, usuario,  rol, clave);
        //SOLICITUD POSTMAN
        Call<infoResponsePost> call = (new infoservicios()).getInfoPost(modal);
        //RESPUESTA POSTMAN
        call.enqueue(new Callback<infoResponsePost>() {
            @Override
            public void onResponse(Call<infoResponsePost> call, Response<infoResponsePost> response) {
                if (response.isSuccessful()){
                Toast.makeText(Insertar.this, "Registro creado", Toast.LENGTH_SHORT).show();
                    txt_nombre.setText("");
                    txt_usuario.setText("");
                    txt_pass.setText("");
                    txt_rol.setText("");
                }
            }

            @Override
            public void onFailure(Call<infoResponsePost> call, Throwable t) {
                txtrespuesta.setText("Error found is : " + t.getMessage());
            }
        });


    }
}