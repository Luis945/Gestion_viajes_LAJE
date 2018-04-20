package com.example.luis.gestion_viajes.adaptadores;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.gestion_viajes.R;
import com.example.luis.gestion_viajes.objetos.Colonia;

import java.util.ArrayList;
import java.util.List;

public class coloniasadaptador extends RecyclerView.Adapter<coloniasadaptador.DatosViewHolder> {


    //REQUERIMOS EL CONTEXTO DE LA APLICACIÓN Y LA LISTA QUE SE UTILIZARÁ PARA LLENAR
    public Context contexto;
    public ArrayList<Colonia> listacolonias;

    public coloniasadaptador(Context context, ArrayList<Colonia> listacolonias){
        this.contexto=context;
        this.listacolonias=listacolonias;
    }

   /*  implements modificarcolonias.OnFragmentInteractionListener
    @Override
    public void onFragmentInteraction(Uri uri) {
                PARA QUE JALE UN FRAGMENT HAY QUE PONER TODO ESTE CÓDIGO
    }*/

    //-----------------------------------AQUÍ ES OTRA CLASE----------//
    /*todo eso lo hice primero*/
    public  class DatosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView nomcolonia; //txtview del item
        List<Colonia> listacolonias = new ArrayList<Colonia>();
        Context context; //contexto de la app
        private int posicion;
        //android.support.v4.app.Fragment f = new modificarcolonias();


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

            int posicion=getAdapterPosition();
            Colonia col = this.listacolonias.get(posicion);
            /*ESTA COSA NO LA VOY A UTILIZAR PERO SIRVE PARA ABRIR OTRO FRAGMENT*/
            //((ventana_principal)context).getSupportFragmentManager().beginTransaction().replace(R.id.cambio,f).commit();
            Toast.makeText(context, ""+col.getNombre().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    /*----------------------------AQUÍ ACABA---------------------------*/


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
        holder.nomcolonia.setText(colonia.getNombre().toUpperCase());
    }

    @Override
    public int getItemCount() {
        return listacolonias.size();
    }
}


