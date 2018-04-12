package com.example.luis.gestion_viajes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class iniciarsesion extends AppCompatActivity implements View.OnClickListener {

    Button botoninicio;
    Intent ir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciarsesion);

        botoninicio = (Button)findViewById(R.id.btninisesion);
        botoninicio.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.btninisesion:{
                ir = new Intent(getApplicationContext(),ventana_principal.class);
                startActivity(ir);
            }
        }
    }
}
