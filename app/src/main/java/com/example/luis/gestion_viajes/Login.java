package com.example.luis.gestion_viajes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button button= findViewById(R.id.button);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btninisesion:{
                Intent t;
                t = new Intent(getApplicationContext(),ventana_principal.class); 
            }break;
            default:{
                Toast.makeText(this, "¡Selección Invalida!", Toast.LENGTH_SHORT).show();
            }break;
        }
    }
}
