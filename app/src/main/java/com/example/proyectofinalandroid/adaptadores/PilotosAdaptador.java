package com.example.proyectofinalandroid.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.utils.Piloto;

import java.util.ArrayList;

public class PilotosAdaptador extends RecyclerView.Adapter<PilotosAdaptador.MyHolder> {

    ArrayList<Piloto> listaPilotos;
    private Context context;

    public PilotosAdaptador(Context context) {
        this.listaPilotos = new ArrayList<>();
        this.context = context;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item_piloto, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder, int position) {
        final Piloto piloto = listaPilotos.get(position);
        holder.getNombrePiloto().setText(piloto.getNombre());
    }

    @Override
    public int getItemCount() {
        return listaPilotos.size();
    }

    public void agregarPiloto(Piloto piloto) {
        listaPilotos.add(piloto);
        notifyDataSetChanged();
    }

    class MyHolder extends RecyclerView.ViewHolder {


        TextView nombrePiloto;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            nombrePiloto = itemView.findViewById(R.id.nombre_piloto);
        }

        public TextView getNombrePiloto() {
            return nombrePiloto;
        }
    }

}
