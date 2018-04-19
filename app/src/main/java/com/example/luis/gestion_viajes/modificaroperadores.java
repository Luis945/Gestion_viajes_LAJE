package com.example.luis.gestion_viajes;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class modificaroperadores extends AppCompatActivity implements View.OnClickListener,
        Response.ErrorListener,
        Response.Listener<JSONObject> {

    String estado;
    TextView txtid,txtnombre,txtapellido;
    Spinner spiner;
    Button btnactualizarop;

    /*Arreglo para el spiner*/
    String[] arregloestado={"Activo","Inactivo"};

    /*VARIABLES PARA ACTUALIZAR*/
    RequestQueue request;
    JSONObject jsonObject;
    int id;
    String url="http://rtaxis.uttsistemas.com/modificaroperadora";
    String estadoespiner="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificaroperadores);


        txtid=(TextView)findViewById(R.id.txtidmod);
        txtnombre=(TextView)findViewById(R.id.txtnommod);
        txtapellido=(TextView)findViewById(R.id.txtapellidomod);
        spiner=(Spinner)findViewById(R.id.spinerestado);
        btnactualizarop = (Button)findViewById(R.id.btnactualizarop);
        btnactualizarop.setOnClickListener(this);


        /*Método para setear valores dentro de un spiner*/
        spiner.setAdapter(new ArrayAdapter<String >(this,R.layout.support_simple_spinner_dropdown_item,arregloestado));

        /*Seteo los valores a los EditText con el objeto Operadora global que proviene del adaptador*/
        txtid.setText(Integer.toString(operadorasadaptador.operadora.getId()));
        txtnombre.setText(operadorasadaptador.operadora.getNombre().toString());
        txtapellido.setText(operadorasadaptador.operadora.getApellidos().toString());
        estado=operadorasadaptador.operadora.getEstado().toString();

        if(estado.equals("Activo")){
            spiner.setSelection(0);
        }else{
            spiner.setSelection(1);
        }

        /*Inicialización de variables para update*/
        id=operadorasadaptador.operadora.getId();
        request= Volley.newRequestQueue(this);

    }

    @Override
    public void onClick(View view) {
        int i=spiner.getSelectedItemPosition();
        if(i==0){estadoespiner="Activo";}else {estadoespiner="Inactivo";}

        switch (view.getId()){
            case R.id.btnactualizarop:{

                jsonObject = new JSONObject();
                try{
                    jsonObject.put("id",id);
                    jsonObject.put("nombre",txtnombre.getText().toString());
                    jsonObject.put("apellidos",txtapellido.getText().toString());
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
        Fragment f = new veroperadoras();
        ((ventana_principal)getApplicationContext()).getSupportFragmentManager().beginTransaction().replace(R.id.cambio,f).commit();
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "¡ERROR, No se pudo actualizar!", Toast.LENGTH_SHORT).show();
    }


}
