package com.example.luis.gestion_viajes.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.gestion_viajes.R;
import com.example.luis.gestion_viajes.objetos.Colonia;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class coloniasadaptador extends RecyclerView.Adapter<coloniasadaptador.DatosViewHolder> {


    //REQUERIMOS EL CONTEXTO DE LA APLICACIÓN Y LA LISTA QUE SE UTILIZARÁ PARA LLENAR
    public Context contexto;
    public List<Colonia> listacolonias;

    public coloniasadaptador(Context context, List<Colonia> listacolonias){
        this.contexto=context;
        this.listacolonias=listacolonias;
    }

    //-----------------------------------AQUÍ ES OTRA CLASE----------//
    /*todo eso lo hice primero*/
    public static class DatosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nomcolonia; //txtview del item
        List<Colonia> listacolonias = new ArrayList<Colonia>();
        Context context; //contexto de la app


        public DatosViewHolder(View itemView,Context context,List<Colonia> listacolonias) {
            super(itemView);


            //setear valores
            this.listacolonias=listacolonias;
            this.context=context;
            itemView.setOnClickListener(this);

            nomcolonia= (TextView)itemView.findViewById(R.id.txtnomcolonia);
        }

        @Override
        public void onClick(View view) {
            Toast.makeText(context, "¡Elemento Seleccionado!", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public DatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*AUI SE JUNTAN LAS VISTAS*/
        LayoutInflater inflater = LayoutInflater.from(contexto);
        View view = inflater.inflate(R.layout.ver_colonias_item,null);

        return new coloniasadaptador.DatosViewHolder(view,contexto,listacolonias);
    }

    @Override
    public void onBindViewHolder(coloniasadaptador.DatosViewHolder holder, int position) {
        Colonia colonia = listacolonias.get(position);
        holder.nomcolonia.setText("Nombre: "+colonia.getNombre());

    }

    @Override
    public int getItemCount() {
        return listacolonias.size();
    }
}


