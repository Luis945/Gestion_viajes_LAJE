package com.example.luis.gestion_viajes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.luis.gestion_viajes.adaptadores.coloniasadaptador;
import com.example.luis.gestion_viajes.objetos.Colonia;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ver_colonias.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ver_colonias#newInstance} factory method to
 * create an instance of this fragment.
 */


//-----------------------HAY QUE IMPLEMENTAR PRIMERO-------------------------//
public class ver_colonias extends Fragment implements Response.ErrorListener,Response.Listener<String>,
        View.OnClickListener,Nueva_colonia.OnFragmentInteractionListener{

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;


    //LA LISTA AQUÍ SE LLENA
    ArrayList<Colonia> listacolonias;
    RecyclerView reciclador;
    final String URL="http://rtaxis.uttsistemas.com/vercolonias";

    public ver_colonias() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ver_colonias.
     */
    // TODO: Rename and change types and number of parameters
    public static ver_colonias newInstance(String param1, String param2) {
        ver_colonias fragment = new ver_colonias();
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

        View view = inflater.inflate(R.layout.fragment_ver_colonias,container,false);
        listacolonias = new ArrayList<>();
        reciclador= (RecyclerView)view.findViewById(R.id.recyclercol);
        reciclador.setLayoutManager(new GridLayoutManager(getContext(),1));

        StringRequest stringRequest = new StringRequest(Request.Method.GET,URL,this,this);
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(6000,DefaultRetryPolicy.DEFAULT_MAX_RETRIES,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        Volley.newRequestQueue(getContext()).add(stringRequest);
        return view;
    }

    /*ESTOS METODOS SON LOS QUE SE IMPLEMENTARON*/

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(String response) {
        Toast.makeText(getContext(), "¡Conectado en ver colonias!", Toast.LENGTH_SHORT).show();
        try {
            JSONArray array = new JSONArray(response);
            for (int i=0;i<array.length();i++){
                JSONObject colonia = array.getJSONObject(i);
                listacolonias.add(new Colonia(colonia.getString("nombre")));
            }

            coloniasadaptador adaptador = new coloniasadaptador(getContext(),listacolonias);
            reciclador.setAdapter(adaptador);
        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    /*AQUÍ TERMINAN LOS METODOS IMPLEMENTADOS*/


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
