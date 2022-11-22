package com.example.api;

import androidx.appcompat.app.AppCompatActivity;

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

public class Actualizar extends AppCompatActivity {
    EditText txt_nombre, txt_usuario, txt_pass, txt_rol;
    TextView txtrespuesta;
    Button btnActualizar, btnEliminar;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        txt_nombre = findViewById(R.id.txtnombre);
        txt_usuario = findViewById(R.id.txtusuario);
        txt_pass = findViewById(R.id.txtcontraseña);
        txt_rol = findViewById(R.id.txtrol);
        txt_rol = findViewById(R.id.txtrol);
        txtrespuesta = findViewById(R.id.txtrespuesta);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar); //PENDIENTE!
        txtrespuesta.setVisibility(View.GONE);

        if(savedInstanceState == null){
            Bundle extras = getIntent().getExtras();
            if(extras == null){
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("ID");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("ID");
        }
        detalleusuario(id);

        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txt_nombre.getText().toString().isEmpty() || txt_usuario.getText().toString().isEmpty() || txt_pass.getText().toString().isEmpty() || txt_rol.getText().toString().isEmpty()) {
                    Toast.makeText(Actualizar.this, "Por favor diligencie todos los datos", Toast.LENGTH_SHORT).show();
                    return;
                }
                // llamar el metodo put pasando los datos ingresados
                putData(txt_nombre.getText().toString(), txt_usuario.getText().toString(), txt_pass.getText().toString(), txt_rol.getText().toString());
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // llamar el metodo delete pasando el id del usuario
                deleteData(id);
            }
        });

    }

    private void detalleusuario(int id) {
        //SOLICITUD POSTMAN
        Call<infoResponsePost> call =(new infoservicios()).getDetailUser(String.valueOf(id));
        //RESPUESTA POSTMAN
        call.enqueue(new Callback<infoResponsePost>() {
            @Override
            public void onResponse(Call<infoResponsePost> call, Response<infoResponsePost> response) {
                if (response.isSuccessful()) {
                    infoResponsePost respuesta = response.body();
                    txt_nombre.setText(respuesta.data.getNames());
                    txt_usuario.setText(respuesta.data.getUsername());
                    txt_pass.setText(respuesta.data.getPassword());
                    txt_rol.setText(respuesta.data.getRol());
                }
                else{
                    String codigo=String.valueOf(response.code());
                    Toast.makeText(Actualizar.this,"Error: "+codigo,Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<infoResponsePost> call, Throwable t) {
                txtrespuesta.setVisibility(View.VISIBLE);
                txtrespuesta.setText("Error encontrado : " + t.getMessage());
            }
        });

    }

    private void deleteData(int id){
        //SOLICITUD POSTMAN
        Call<infoResponsePost> call = (new infoservicios()).deletedata(String.valueOf(id));
        //RESPUESTA POSTMAN
        call.enqueue(new Callback<infoResponsePost>() {
            @Override
            public void onResponse(Call<infoResponsePost> call, Response<infoResponsePost> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Actualizar.this, "Usuario eliminado", Toast.LENGTH_SHORT).show();

                    txt_nombre.setText("");
                    txt_usuario.setText("");
                    txt_pass.setText("");
                    txt_rol.setText("");
                }
                else{
                    String codigo=String.valueOf(response.code());
                    Toast.makeText(Actualizar.this,"Error: "+codigo,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<infoResponsePost> call, Throwable t) {
                txtrespuesta.setVisibility(View.VISIBLE);
                txtrespuesta.setText("Error encontrado : " + t.getMessage());
            }
        });
    }

    private void putData(String nombre, String usuario, String clave, String rol) {
        infoUsuarios datos = new infoUsuarios(nombre, usuario,  rol, clave);
        //SOLICITUD POSTMAN
        Call<infoResponsePost> call = (new infoservicios()).updatedata(id,datos);
        //RESPUESTA POSTMAN
        call.enqueue(new Callback<infoResponsePost>() {
            @Override
            public void onResponse(Call<infoResponsePost> call, Response<infoResponsePost> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(Actualizar.this, "Datos actualizados", Toast.LENGTH_SHORT).show();

                    txt_nombre.setText("");
                    txt_usuario.setText("");
                    txt_pass.setText("");
                    txt_rol.setText("");
                }
                else{
                    String codigo=String.valueOf(response.code());
                    Toast.makeText(Actualizar.this,"Error: "+codigo,Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<infoResponsePost> call, Throwable t) {
                txtrespuesta.setVisibility(View.VISIBLE);
                txtrespuesta.setText("Error encontrado : " + t.getMessage());
            }
        });
    }
}