package com.example.api.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.api.Actualizar;
import com.example.api.R;
import com.example.api.Servicios.models.infoUsuarios;

import java.util.List;

public class ListaUsuariosAdapter extends RecyclerView.Adapter<ListaUsuariosAdapter.ViewHolder>{
    private List<infoUsuarios> listaUsuarios;
    public ListaUsuariosAdapter(List<infoUsuarios> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;

    }
    @NonNull
    @Override
    public ListaUsuariosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuario, null, false);
        return new ListaUsuariosAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaUsuariosAdapter.ViewHolder holder, int position) {
        infoUsuarios infoUsuarios = this.listaUsuarios.get(position);
        holder.viewNombre.setText(infoUsuarios.getNames());

    }

    @Override
    public int getItemCount() {
        return listaUsuarios.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView viewNombre;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.viewNombre = itemView.findViewById(R.id.viewNombre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, Actualizar.class);
                    intent.putExtra("ID", listaUsuarios.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }

    }
}
