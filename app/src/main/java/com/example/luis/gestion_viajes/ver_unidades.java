package com.example.luis.gestion_viajes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.luis.gestion_viajes.adaptadores.unidadesAdapter;
import com.example.luis.gestion_viajes.objetos.Unidad;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ver_unidades.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ver_unidades#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ver_unidades extends Fragment implements Response.ErrorListener,Response.Listener<String>{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public static ArrayList<Unidad> listaunidades;
    ProgressBar cargando;
    Handler handler = new Handler();
    int contadorcargando=0;
    RecyclerView reciclador;

    final String URL_GET = "http://rtaxis.uttsistemas.com/verunidades";
    public ver_unidades() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ver_unidades.
     */
    // TODO: Rename and change types and number of parameters
    public static ver_unidades newInstance(String param1, String param2) {
        ver_unidades fragment = new ver_unidades();
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
       View v= inflater.inflate(R.layout.fragment_ver_unidades, container, false);

       listaunidades= new ArrayList<>();
       reciclador=(RecyclerView) v.findViewById(R.id.reciclador);
       cargando=(ProgressBar)v.findViewById(R.id.barrawait);



        reciclador.setLayoutManager(new GridLayoutManager(getContext(),2));

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL_GET, this, this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(6000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getContext()).add(stringRequest);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while(contadorcargando<100)
                {
                    contadorcargando++;
                    android.os.SystemClock.sleep(120);
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                        cargando.setProgress(contadorcargando);

                        }
                    });
                }
            }
        }).start();




        return v;
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

    }

    @Override
    public void onResponse(String response) {

        handler.post(new Runnable() {
            @Override
            public void run() {
                cargando.setVisibility(View.GONE);
            }
        });

        try {
            JSONArray array = new JSONArray(response);
            for (int i = 0; i < array.length(); i++) {
                JSONObject unidad = array.getJSONObject(i);
                listaunidades.add(new Unidad(
                        unidad.getInt("reg"),
                        unidad.getString("estado")


                ));

            }

            unidadesAdapter adaptador = new unidadesAdapter(getContext(), listaunidades);
            reciclador.setAdapter(adaptador);



        } catch (JSONException ex) {
            ex.printStackTrace();
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
