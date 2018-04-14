package com.example.luis.gestion_viajes.adaptadores;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luis.gestion_viajes.R;
import com.example.luis.gestion_viajes.objetos.Unidad;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kari on 11/04/2018.
 */

public class unidadesAdapter extends RecyclerView.Adapter<unidadesAdapter.DatosViewHolder> {

  public  Context contexto;
   public List<Unidad>listaunidades;

    public unidadesAdapter(Context contexto,List<Unidad>listaunidades ) {
        this.contexto = contexto;
       this.listaunidades=listaunidades;
    }

    @Override
    public unidadesAdapter.DatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(contexto);
            View view = inflater.inflate(R.layout.ver_unidades_item,null);

            //return new DatosViewHolder(view);
        return  new unidadesAdapter.DatosViewHolder(view,contexto,listaunidades);
    }

    @Override
    public void onBindViewHolder(unidadesAdapter.DatosViewHolder holder, int position) {
            Unidad unidad = listaunidades.get(position);
            holder.num_unidad.setText(Integer.toString(unidad.getReg()));
            holder.estado.setText(unidad.getEstado());


    }

    @Override
    public int getItemCount() {
        return listaunidades.size();
    }

    public class DatosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView num_unidad,estado;
        List<Unidad>listaunidades = new ArrayList<>();
        Context context;


        public DatosViewHolder(View itemView,Context context,List<Unidad>listaunidades) {
            super(itemView);
            this.listaunidades=listaunidades;
            this.context=context;
            itemView.setOnClickListener(this);
            num_unidad=(TextView)itemView.findViewById(R.id.num_unidad);
            estado=(TextView)itemView.findViewById(R.id.estado);




        }

        @Override
        public void onClick(View view) {

        }
    }
}

