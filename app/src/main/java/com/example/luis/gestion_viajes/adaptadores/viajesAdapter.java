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
 * Created by luis on 09/04/18.
 */

public class viajesAdapter extends RecyclerView.Adapter<viajesAdapter.datosHolder> {

    List<Viaje> viajes;
    Context context;

    public viajesAdapter(List<Viaje> viajes, Context context) {

        this.viajes = viajes;
        this.context = context;
    }

    @Override
    public viajesAdapter.datosHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(context);
        View vista= inflater.inflate(R.layout.viaje_mandado,null);
        return new datosHolder(vista);

    }

    @Override
    public void onBindViewHolder(datosHolder holder, int position) {
     Viaje viaje= viajes.get(position);
    holder.textView1.setText(viaje.getFecha());
    holder.textView2.setText(viaje.getDireccion());
    holder.textView3.setText(viaje.getColonia());
    holder.textView4.setText(String.valueOf(viaje.getUnidad()));
    holder.textView5.setText(String.valueOf(viaje.getOperadora()));

    }

    @Override
    public int getItemCount() {
        return viajes.size();
    }

    class datosHolder extends RecyclerView.ViewHolder{
        TextView textView1, textView2, textView3, textView4, textView5;

        public datosHolder(View view){
            super(view);
            textView1= view.findViewById(R.id.fecha_lbl);
            textView2= view.findViewById(R.id.direccion_lbl);
            textView3= view.findViewById(R.id.colonia_lbl);
            textView4= view.findViewById(R.id.unidad_lbl);
            textView5= view.findViewById(R.id.operadora_lbl);
        }

    }


}
