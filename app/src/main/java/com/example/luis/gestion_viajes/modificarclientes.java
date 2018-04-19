package com.example.luis.gestion_viajes;

import android.app.FragmentTransaction;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.luis.gestion_viajes.adaptadores.VerclientesAdapter;

import org.json.JSONException;
import org.json.JSONObject;

public class modificarclientes extends AppCompatActivity implements View.OnClickListener,
        Response.ErrorListener,
        Response.Listener<JSONObject> , fragment_verclientes.OnFragmentInteractionListener{

    EditText txtnombre,txttelefono,txtdireccion,txtentre1,txtentre2,txtnota;
    Button modcli;

    /*VARIABLES PARA ACTUALIZAR*/
    RequestQueue request;
    JSONObject jsonObject;
    int id;
    String url="http://rtaxis.uttsistemas.com/modificarcliente";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificarclientes);

        txtnombre=(EditText)findViewById(R.id.txtnommodcli);
        txttelefono=(EditText)findViewById(R.id.txttelmodcli);
        txtdireccion=(EditText)findViewById(R.id.txtdirmodcli);
        txtentre1=(EditText)findViewById(R.id.txtentre1modcli);
        txtentre2=(EditText)findViewById(R.id.txtentre2modcli);
        txtnota=(EditText)findViewById(R.id.txtnotamodcli);
        modcli=(Button)findViewById(R.id.btnmodcli);

        txtnombre.setText(VerclientesAdapter.cliente.getNombre().toString());
        txttelefono.setText(VerclientesAdapter.cliente.getTelefono().toString());
        txtdireccion.setText(VerclientesAdapter.cliente.getDireccion().toString());
        txtentre1.setText(VerclientesAdapter.cliente.getEntre_1().toString());
        txtentre2.setText(VerclientesAdapter.cliente.getEntre_2().toString());
        txtnota.setText(VerclientesAdapter.cliente.getNota().toString());

        request= Volley.newRequestQueue(this);
        modcli.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        id=VerclientesAdapter.cliente.getId();

        switch (view.getId()){
            case R.id.btnmodcli:{
                jsonObject = new JSONObject();
                try{
                    jsonObject.put("id",id);
                    jsonObject.put("nombre_cliente",txtnombre.getText().toString());
                    jsonObject.put("telefono",txttelefono.getText().toString());
                    jsonObject.put("direccion",txtdireccion.getText().toString());
                    jsonObject.put("entre_1",txtentre1.getText().toString());
                    jsonObject.put("entre_2",txtentre2.getText().toString());
                    jsonObject.put("nota",txtnota.getText().toString());
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


        Toast.makeText(this, "¡Registro actualizado!", Toast.LENGTH_SHORT).show();



    }
    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(this, "¡ERROR, No se pudo actualizar!", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
