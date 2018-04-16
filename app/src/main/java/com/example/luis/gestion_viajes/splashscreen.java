package com.example.luis.gestion_viajes;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splashscreen extends Activity {

    private static int intervalo=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                //TODO Auto-generated method stub

                Intent intent = new Intent(splashscreen.this,iniciosesion.class);
                startActivity(intent);


                this.finish();
            }

            private void finish()
            {
                //TODO Auto-generated method stub

            }
        },intervalo);
    }
}
