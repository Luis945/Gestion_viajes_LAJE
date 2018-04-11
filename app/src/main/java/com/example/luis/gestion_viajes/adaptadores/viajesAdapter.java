package com.example.luis.gestion_viajes.adaptadores;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.luis.gestion_viajes.R;
import com.example.luis.gestion_viajes.objetos.Viaje;

import java.util.ArrayList;

/**
 * Created by luis on 09/04/18.
 */

public class viajesAdapter extends BaseAdapter {

    ArrayList<Viaje> viajes;
    Context context;

    public viajesAdapter(ArrayList<Viaje> viajes, Context context) {
        this.viajes = viajes;
        this.context = context;
    }

    @Override
    public int getCount() {
        return viajes.size();
    }

    @Override
    public Object getItem(int position) {
        return viajes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return viajes.get(position).getOperadora();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Viaje viaje= viajes.get(position);
        View v= convertView;
        v= LayoutInflater.from(context).inflate(R.layout.viaje_mandado,null);

        TextView fecha, telefono, direccion, unidad,operadora;
        fecha= (TextView) v.findViewById(R.id.lbl_fecha);
        telefono=(TextView) v.findViewById(R.id.lbl_teléfono);
        direccion=(TextView) v.findViewById(R.id.lbl_direccion);
        unidad=(TextView) v.findViewById(R.id.lbl_unidad);
        operadora= (TextView) v.findViewById(R.id.lbl_operadora);
        fecha.setText(viaje.getFecha());
        telefono.setText(viaje.getTelefono());
        direccion.setText(viaje.getDireccion());
        unidad.setText(viaje.getUnidad());
        operadora.setText(viaje.getOperadora());



        return v;
    }
}
