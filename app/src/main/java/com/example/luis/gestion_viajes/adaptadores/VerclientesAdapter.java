package com.example.luis.gestion_viajes.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.gestion_viajes.R;
import com.example.luis.gestion_viajes.fragment_verclientes;
import com.example.luis.gestion_viajes.moreClientesFragment;
import com.example.luis.gestion_viajes.moreclientesActivity;
import com.example.luis.gestion_viajes.objetos.Cliente;
import com.example.luis.gestion_viajes.ver_unidades;

import java.util.ArrayList;

public class VerclientesAdapter extends  RecyclerView.Adapter<VerclientesAdapter.DatosViewHolder> {

    public Context contexto;
    public ArrayList<Cliente> listaclientes;

    public static Cliente cliente;
    Fragment fragment;


    public VerclientesAdapter(Context contexto, ArrayList<Cliente> listaclientes) {
        this.contexto = contexto;
        this.listaclientes = listaclientes;
    }

    @Override
    public VerclientesAdapter.DatosViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(contexto);
        View view = inflater.inflate(R.layout.itemlist_verclientes,null);

        //return new DatosViewHolder(view);
        return  new VerclientesAdapter.DatosViewHolder(view,contexto,listaclientes);
    }

    @Override
    public void onBindViewHolder(VerclientesAdapter.DatosViewHolder holder, int position) {

        Cliente cliente=listaclientes.get(position);
        holder.nombre.setText(cliente.getNombre().toString());
        holder.telefono.setText("Telefono: "+cliente.getTelefono().toString());
    }

    @Override
    public int getItemCount() {
        return listaclientes.size();
    }


    public class DatosViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nombre,telefono;
        ArrayList<Cliente> listaclientes= new ArrayList<>();
        Context ctxt;
        ImageView more;
        LinearLayout linearLayout;

        public DatosViewHolder(View itemView, final Context contexto, final ArrayList<Cliente> listaclientes) {
            super(itemView);

            this.listaclientes=listaclientes;
            this.ctxt=contexto;
            itemView.setOnClickListener(this);

            more=(ImageView)itemView.findViewById(R.id.more);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linear1);

            more.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int position=getAdapterPosition();
                    cliente= listaclientes.get(position);

                    if (position!=RecyclerView.NO_POSITION)
                    {
                        Intent intent=new Intent(contexto,moreclientesActivity.class);
                        contexto.startActivity(intent);
                    }else {

                    }
                }
            });
            nombre=(TextView) itemView.findViewById(R.id.nombrecliente);
            telefono=(TextView) itemView.findViewById(R.id.telefonoclientes);
        }

        @Override
        public void onClick(View view) {

            int position=getAdapterPosition();
            cliente=listaclientes.get(position);

            if (position!=RecyclerView.NO_POSITION)
            {
                Intent intent=new Intent(contexto,moreclientesActivity.class);
                contexto.startActivity(intent);
            }else
            {

            }

        }
    }
}
