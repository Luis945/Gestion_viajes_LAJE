package com.example.luis.gestion_viajes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button= findViewById(R.id.btnini);
        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnini:{
                intent= new Intent(getApplicationContext(),iniciosesion.class);
                startActivity(intent);
            }break;
            default:{
                Toast.makeText(this, "¡Selección Invalida!", Toast.LENGTH_SHORT).show();
            }break;
        }
    }
}
