package com.example.luis.gestion_viajes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.luis.gestion_viajes.adaptadores.viajesAdapter;
import com.example.luis.gestion_viajes.objetos.Viaje;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ventana.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ventana#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ventana extends Fragment implements Response.Listener<String>,Response.ErrorListener,SwipeRefreshLayout.OnRefreshListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public ventana() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ventana.
     */
    // TODO: Rename and change types and number of parameters
    public static ventana newInstance(String param1, String param2) {
        ventana fragment = new ventana();
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


    List<Viaje> viajes= new ArrayList<>();
    String url="http://rtaxis.uttsistemas.com/verviajes";
    SwipeRefreshLayout refreshLayout;
    RequestQueue queue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_ventana, container, false);
    recyclerView= view.findViewById(R.id.reciclado_viaje);
    refreshLayout=(SwipeRefreshLayout) view.findViewById(R.id.actualizar);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light,
                android.R.color.holo_green_dark,
                android.R.color.holo_blue_dark);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
        cargarViajes();


        return view;
    }
    RecyclerView recyclerView;
    private void cargarViajes(){
        StringRequest stringRequest = new StringRequest(Request.Method.GET,url, this,this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(6000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
      //  queue.add(jsonObjectRequest);
        Volley.newRequestQueue(getContext()).add(stringRequest);

    }

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
        Toast.makeText(getContext(), "no se pudo encontrar", Toast.LENGTH_SHORT).show();
        Log.e("error de conexión", "onErrorResponse: "+error.toString());
    }

    @Override
    public void onResponse(String response) {
        Log.d("ejemplo", ""+response.toString());

        try{
            viajes.clear();

            JSONArray array= new JSONArray(response);

            for (int i = 0; i < array.length(); i++) {

                JSONObject object= array.getJSONObject(i);

                viajes.add(new Viaje(
                        Integer.parseInt(object.getString("unidad")),
                        Integer.parseInt(object.getString("operadora")),
                        object.getString("colonia").toString(),
                        object.getString("direccion").toString(),
                        object.getString("fecha_viaje").toString()
                ));
            }
            viajesAdapter viajesAdapter= new viajesAdapter(viajes,getContext());

            recyclerView.setAdapter(viajesAdapter);
            recyclerView.refreshDrawableState();
            viajesAdapter.notifyDataSetChanged();

        }catch (JSONException e){
            Log.d("error del json", "onResponse: "+e);
        }

    }

    @Override
    public void onRefresh() {
        //Aqui ejecutamos el codigo necesario para refrescar nuestra interfaz grafica.
        //Antes de ejecutarlo, indicamos al swipe layout que muestre la barra indeterminada de progreso.
        refreshLayout.setRefreshing(true);

        cargarViajes();

        //Vamos a simular un refresco con un handle.
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                //Se supone que aqui hemos realizado las tareas necesarias de refresco, y que ya podemos ocultar la barra de progreso
                refreshLayout.setRefreshing(false);
                Toast.makeText(getContext(), "Actualizando...", Toast.LENGTH_SHORT).show();

            }
        }, 8000);

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
