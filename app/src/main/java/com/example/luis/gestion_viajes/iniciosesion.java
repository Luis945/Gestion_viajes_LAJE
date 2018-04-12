package com.example.luis.gestion_viajes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class iniciosesion extends AppCompatActivity {

    Button botonir;
    Intent intentir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciosesion);

        botonir = (Button) findViewById(R.id.btnini);
        //botonir.setOnClickListener(this);
    }

    /*public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnini:{
                intentir = new Intent(getApplicationContext(),ventana_principal.class);
                startActivity(intentir);
            }break;
            default:{
                Toast.makeText(this, "¡Selección Invalida!", Toast.LENGTH_SHORT).show();
            }break;
        }
    }*/
}
