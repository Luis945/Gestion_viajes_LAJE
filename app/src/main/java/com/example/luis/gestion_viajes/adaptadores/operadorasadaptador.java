package com.example.luis.gestion_viajes.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.graphics.Path;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.luis.gestion_viajes.R;
import com.example.luis.gestion_viajes.modificaroperadores;
import com.example.luis.gestion_viajes.objetos.Operadora;

import junit.framework.TestResult;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class operadorasadaptador extends RecyclerView.Adapter<operadorasadaptador.DatosViewHolder> {

    public Context contexto;
    public ArrayList<Operadora> listaoperadoras;
    public static Operadora operadora;

    public operadorasadaptador(Context contexto, ArrayList<Operadora>listaoperadoras){
        this.contexto=contexto;
        this.listaoperadoras=listaoperadoras;
    }

    @Override
    public DatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(contexto);
        View view = layoutInflater.inflate(R.layout.ver_operadoras_item,null);
        return new operadorasadaptador.DatosViewHolder(view,contexto,listaoperadoras);
    }

    @Override
    public void onBindViewHolder(DatosViewHolder holder, int position) {
        Operadora operadora = listaoperadoras.get(position);
        String id=Integer.toString(operadora.getId());
        holder.txtid.setText(id);
        holder.txtnombre.setText(operadora.getNombre()+"."+operadora.getApellidos().toUpperCase());
        holder.txtestado.setText(operadora.getEstado());
        holder.txttipo.setText(operadora.getTipo_operadora());
    }

    @Override
    public int getItemCount() {
        return listaoperadoras.size();
    }

    public class DatosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtid,txtnombre,txtapellido,txtestado,txttipo;
        List<Operadora> listaoperadoras = new ArrayList<>();
        Context contexto;
        int posicion;


        public DatosViewHolder(View itemView,final Context contexto,final ArrayList<Operadora> listaoperadoras) {
            super(itemView);

            this.contexto=contexto;
            this.listaoperadoras=listaoperadoras;
            itemView.setOnClickListener(this);

            txtid=(TextView)itemView.findViewById(R.id.txtid);
            txtnombre=(TextView)itemView.findViewById(R.id.txtnombre);

            txtestado=(TextView)itemView.findViewById(R.id.txtestado);
            txttipo=(TextView)itemView.findViewById(R.id.txttipo);
        }

        @Override
        public void onClick(View view) {

            int position=getAdapterPosition();
            operadora=listaoperadoras.get(position);

            if (position!=RecyclerView.NO_POSITION)
            {
                Intent intent=new Intent(contexto,modificaroperadores.class);
                contexto.startActivity(intent);
            }else
            {

            }
        }
    }

}
