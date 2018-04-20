package com.example.luis.gestion_viajes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.luis.gestion_viajes.adaptadores.verViajesAdapter;
import com.example.luis.gestion_viajes.objetos.Viaje;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link verViajes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link verViajes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class verViajes extends Fragment implements Response.Listener<String>,Response.ErrorListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public verViajes() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment verViajes.
     */
    // TODO: Rename and change types and number of parameters
    public static verViajes newInstance(String param1, String param2) {
        verViajes fragment = new verViajes();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    RadioButton radio_unidad,radio_telefono,radio_todos;
    TextView buscar;
    Button aceptar;
    RecyclerView recyclerView;
    String url="";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_ver_viajes, container, false);
        radio_telefono= view.findViewById(R.id.radio_telefono);
        radio_unidad= view.findViewById(R.id.radio_unidad);
        radio_todos=view.findViewById(R.id.radio_todos);
        buscar= view.findViewById(R.id.busqueda_txt);
        aceptar= view.findViewById(R.id.busqueda_btn);
        recyclerView=  view.findViewById(R.id.verViajesT);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (buscar.getText()==""){
                    Toast.makeText(getContext(), "Llene los campos pls", Toast.LENGTH_SHORT).show();
                }else{

                    cargarViajes();
                }
            }
        });
        cargarViajes();
        return view;
    }
    boolean cambio=false;
    JSONObject object= new JSONObject();
    private void cargarViajes() {
        if (radio_todos.isChecked()){
          url="http://rtaxis.uttsistemas.com/verviajeTotales";
          cambio=true;
      }else if (radio_unidad.isChecked()){

            url="http://rtaxis.uttsistemas.com/verviajeTelefono";
          cambio=true;
      }else if (radio_telefono.isChecked()){
          url="http://rtaxis.uttsistemas.com/verviajeTelefono";
          cambio=true;
      }

        if (cambio) {
            StringRequest stringRequest = new StringRequest(Request.Method.GET,url, this,this);
            stringRequest.setRetryPolicy(new DefaultRetryPolicy(6000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            //  queue.add(jsonObjectRequest);
            Volley.newRequestQueue(getContext()).add(stringRequest);

        }
    }
RequestQueue requestQueue;
    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }
    List<Viaje> viajes= new ArrayList<>();
    List<Viaje> viajeConsulta= new ArrayList<>();
    @Override
    public void onResponse(String response) {

        try{
            viajes.clear();
            viajeConsulta.clear();
            JSONArray array= new JSONArray(response);

            for (int i = 0; i < array.length(); i++) {

                JSONObject object= array.getJSONObject(i);

                viajes.add(new Viaje(
                        Integer.parseInt(object.getString("unidad")),
                        Integer.parseInt(object.getString("operadora")),
                        object.getString("colonia").toString(),
                        object.getString("direccion").toString(),
                        object.getString("fecha_viaje").toString(),
                        object.getString("telefono")
                ));
            }
            if (radio_todos.isChecked()){
                verViajesAdapter adapter= new verViajesAdapter(viajes,getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.refreshDrawableState();
                adapter.notifyDataSetChanged();
                Log.d("ejemplo", ""+response.toString());

                Toast.makeText(getContext(), "viajes cargados", Toast.LENGTH_SHORT).show();

            }
            else if (radio_telefono.isChecked()){
                for (int i = 0; i <viajes.size()-1 ; i++) {
                    if (buscar.getText().toString().equals(viajes.get(i).getTelefono())) {

                        viajeConsulta.add(viajes.get(i));
                    }
                }

                Log.d("hola", "onResponse: "+viajeConsulta);
                verViajesAdapter adapter= new verViajesAdapter(viajeConsulta,getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.refreshDrawableState();
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Viajes cargados por telefono", Toast.LENGTH_SHORT).show();
                buscar.setText("");

            }
            else if (radio_unidad.isChecked()){
                for (int i = 0; i <viajes.size()-1 ; i++) {
                    if (buscar.getText().toString().equals(String.valueOf(viajes.get(i).getUnidad()))) {

                        viajeConsulta.add(viajes.get(i));
                    }
                }

                Log.d("hola", "onResponse: "+viajeConsulta);
                verViajesAdapter adapter= new verViajesAdapter(viajeConsulta,getContext());
                recyclerView.setAdapter(adapter);
                recyclerView.refreshDrawableState();
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(), "Viajes cargados por unidad", Toast.LENGTH_SHORT).show();
                buscar.setText("");

            }

        }catch (JSONException e){
            Log.d("error del json", "onResponse: "+e);
        }


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
