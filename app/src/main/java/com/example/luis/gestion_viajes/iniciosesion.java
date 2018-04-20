package com.example.luis.gestion_viajes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.luis.gestion_viajes.adaptadores.operadorasadaptador;
import com.example.luis.gestion_viajes.objetos.Colonia;
import com.example.luis.gestion_viajes.objetos.Operadora;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Map;

public class iniciosesion extends AppCompatActivity implements View.OnClickListener,
        Response.Listener<JSONObject>,
        Response.ErrorListener{

    Button botonir;
    Intent intentir;
    TextView txtuser,txtpass;
    String usuario,contrasena;
    public static ArrayList<Operadora> listaoperadora;


    /*VARIABLES PARA CONSULTAR*/
    RequestQueue request;
    JSONObject jsonObject;
    final String URL="http://rtaxis.uttsistemas.com/consultalogin";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciosesion);

        txtuser =(TextView) findViewById(R.id.txtuser);
        txtpass=(TextView)findViewById(R.id.txtpass);
        botonir = (Button) findViewById(R.id.btnini);
        botonir.setOnClickListener(this);
        listaoperadora=new ArrayList<>();

        request= Volley.newRequestQueue(this);
    }

    public void onClick(View view) {
        usuario=txtuser.getText().toString();
        contrasena=txtpass.getText().toString();

        if(usuario!=""||contrasena!=""){
            switch (view.getId()){
                case R.id.btnini:{

                    jsonObject = new JSONObject();
                    try{

                        jsonObject.put("id",Integer.parseInt(usuario));
                        jsonObject.put("contrasena",contrasena);
                    }
                    catch (JSONException e){
                        e.printStackTrace();
                    }
                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,URL,jsonObject,this,this);
                    jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    request.add(jsonObjectRequest);
                }break;
            }
        }else{
            Toast.makeText(this, "¡Completa los campos!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "¡Usuario o Contraseña Incorrectos!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onResponse(JSONObject response) {

        String idd="";
        boolean success=false;
        try {
            success = response.getBoolean("success");
            idd=response.getString("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        
        if(success==true) {
            Toast.makeText(this, ""+idd, Toast.LENGTH_SHORT).show();
            Toast.makeText(this, "" + response, Toast.LENGTH_SHORT).show();
            intentir = new Intent(getApplicationContext(), ventana_principal.class);
            startActivity(intentir);
        }else{
            Toast.makeText(this, "¡Usuario o Contraseña Incorrectos!", Toast.LENGTH_SHORT).show();
        }
    }
}
