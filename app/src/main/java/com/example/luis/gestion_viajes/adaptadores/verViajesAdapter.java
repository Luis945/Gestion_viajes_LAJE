package com.example.luis.gestion_viajes.adaptadores;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by luis on 16/04/18.
 */

public class verViajesAdapter extends RecyclerView.Adapter<verViajesAdapter.holder> {
    @Override
    public verViajesAdapter.holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(verViajesAdapter.holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class holder extends RecyclerView.ViewHolder{

        public holder(View view){
            super(view);
        }

    }
}
