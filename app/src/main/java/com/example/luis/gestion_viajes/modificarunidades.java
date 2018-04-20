package com.example.luis.gestion_viajes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.luis.gestion_viajes.adaptadores.operadorasadaptador;
import com.example.luis.gestion_viajes.adaptadores.unidadesAdapter;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

public class modificarunidades extends AppCompatActivity implements View.OnClickListener,
        Response.ErrorListener,Response.Listener<JSONObject> {


    String estado;
    EditText txtreg;
    Spinner spiner;
    Button btnactualizaruni;

    /*Arreglo para el spiner*/
    String[] arregloestado={"Activo","Inactivo"};

    /*VARIABLES PARA ACTUALIZAR*/
    RequestQueue request;
    JSONObject jsonObject;
    int id;
    String url="http://rtaxis.uttsistemas.com/modificarunidad";
    String estadoespiner="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificarunidades);

        txtreg=(EditText) findViewById(R.id.txtidmoduni);
        spiner=(Spinner)findViewById(R.id.spinerestadomoduni);
        btnactualizaruni=(Button)findViewById(R.id.btnmoduni);
        btnactualizaruni.setOnClickListener(this);


        /*Método para setear valores dentro de un spiner*/
        spiner.setAdapter(new ArrayAdapter<String >(this,R.layout.support_simple_spinner_dropdown_item,arregloestado));
        estado=unidadesAdapter.unidad.getEstado();

        txtreg.setText(Integer.toString(unidadesAdapter.unidad.getReg()));


        if(estado.equals("Activo")){
            spiner.setSelection(0);
        }else{
            spiner.setSelection(1);
        }

        /*Inicialización de variables para update*/
        request= Volley.newRequestQueue(this);
    }

    @Override
    public void onClick(View view) {
        int i=spiner.getSelectedItemPosition();
        if(i==0){estadoespiner="Activo";}else {estadoespiner="Inactivo";}

        switch (view.getId()){
            case R.id.btnmoduni:{
                jsonObject = new JSONObject();
                try{
                    jsonObject.put("reg",txtreg.getText());
                    jsonObject.put("estado",estadoespiner);
                }
                catch (JSONException e){
                    e.printStackTrace();
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,url,jsonObject,this,this);
                jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                request.add(jsonObjectRequest);
            }break;
        }
    }

    @Override
    public void onResponse(JSONObject response) {
        Toast.makeText(this, "¡Registro Actualizado!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "¡ERROR, No se pudo actualizar!", Toast.LENGTH_SHORT).show();
    }


}
