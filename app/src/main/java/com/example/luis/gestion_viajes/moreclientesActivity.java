package com.example.luis.gestion_viajes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.luis.gestion_viajes.adaptadores.VerclientesAdapter;

public class moreclientesActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txttel,txtdireccion,txtentre,txtcolonia,txtnota;
    ImageView update;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moreclientes);

        update=(ImageView) findViewById(R.id.update);
        txttel=(TextView)findViewById(R.id.telefono_settext);
        txtdireccion=(TextView)findViewById(R.id.direccion_settext);
        txtentre=(TextView)findViewById(R.id.entre1_settext);
        txtcolonia=(TextView)findViewById(R.id.colonia_settext);
        txtnota=(TextView)findViewById(R.id.nota_settext);
        update.setOnClickListener(this);


        txttel.setText(VerclientesAdapter.cliente.getTelefono());
        txtdireccion.setText(VerclientesAdapter.cliente.getDireccion().toUpperCase());
        txtentre.setText(VerclientesAdapter.cliente.getEntre_1().toUpperCase()+" y "+VerclientesAdapter.cliente.getEntre_2().toUpperCase());
        txtcolonia.setText(VerclientesAdapter.cliente.getColonia().toUpperCase());
        txtnota.setText(VerclientesAdapter.cliente.getNota().toUpperCase());
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.update:{
                Intent ir = new Intent(getApplicationContext(),modificarclientes.class);
                startActivity(ir);
            }
        }
    }
}
