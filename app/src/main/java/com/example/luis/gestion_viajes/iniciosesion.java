package com.example.luis.gestion_viajes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.luis.gestion_viajes.objetos.Colonia;
import com.example.luis.gestion_viajes.objetos.Operadora;

import java.util.ArrayList;

public class iniciosesion extends AppCompatActivity implements View.OnClickListener{

    Button botonir;
    Intent intentir;
    TextView txtuser,txtpass;

    //LA LISTA AQUÍ SE LLENA
    public static ArrayList<Operadora> listaoperadora ;
    RecyclerView reciclador;
    final String URL="http://rtaxis.uttsistemas.com/iniciasesione";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciosesion);

        txtuser =(TextView) findViewById(R.id.txtuser);
        txtpass=(TextView)findViewById(R.id.txtpass);
        botonir = (Button) findViewById(R.id.btnini);
        botonir.setOnClickListener(this);
    }

    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnini:{
                intentir = new Intent(getApplicationContext(),ventana_principal.class);
                startActivity(intentir);
            }break;
            default:{
                Toast.makeText(this, "¡Selección Invalida!", Toast.LENGTH_SHORT).show();
            }break;
        }
    }
}
