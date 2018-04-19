package com.example.luis.gestion_viajes.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luis.gestion_viajes.R;
import com.example.luis.gestion_viajes.objetos.Viaje;

import java.util.List;

/**
 * Created by luis on 16/04/18.
 */

public class verViajesAdapter extends RecyclerView.Adapter<verViajesAdapter.holder> {

    List<Viaje> viajes;
    Context context;

    public verViajesAdapter(List<Viaje> viajes, Context context) {
        this.viajes = viajes;
        this.context = context;
    }

    @Override
    public verViajesAdapter.holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View vista= inflater.inflate(R.layout.viaje_total,null);
        return new holder(vista);


    }

    @Override
    public void onBindViewHolder(verViajesAdapter.holder holder, int position) {
        Viaje viaje= viajes.get(position);
        holder.textView1.setText(viaje.getDireccion());
        holder.textView2.setText(viaje.getColonia());
        holder.textView3.setText(viaje.getUnidad());
        holder.textView4.setText(viaje.getFecha());
    }

    @Override
    public int getItemCount() {
        return viajes.size();
    }

    class holder extends RecyclerView.ViewHolder{

        TextView textView1,textView2,textView3,textView4;

        public holder(View view){
            super(view);

        textView1= view.findViewById(R.id.texto_direccion);
        textView2= view.findViewById(R.id.texto_colonia);
        textView3= view.findViewById(R.id.texto_unidad);
        textView4= view.findViewById(R.id.texto_fecha);

        }

    }
}
