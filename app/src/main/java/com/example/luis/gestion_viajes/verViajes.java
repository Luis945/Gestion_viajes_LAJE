package com.example.luis.gestion_viajes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.TextView;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.luis.gestion_viajes.objetos.Viaje;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link verViajes.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link verViajes#newInstance} factory method to
 * create an instance of this fragment.
 */
public class verViajes extends Fragment implements Response.Listener<JSONObject>,Response.ErrorListener {
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
        if (radio_todos.isChecked()){
            cargarViajes();
        }
        return view;
    }
    boolean cambio=false;
    JSONObject object;
    private void cargarViajes() {
      if (radio_todos.isChecked()){
          url="http://rtaxis.uttsistemas.com/verviajes";
          try {
              object.put("validacion","aceptar");
          } catch (JSONException e) {
              e.printStackTrace();
          }
          cambio=true;
      }else if (radio_unidad.isChecked()){
          url="http://rtaxis.uttsistemas.com/verviajesUnidad";
          try {
              object.put("unidad",buscar.getText().toString());
          } catch (JSONException e) {
              e.printStackTrace();
          }
          cambio=true;
      }else if (radio_telefono.isChecked()){
          url="http://rtaxis.uttsistemas.com/verviajesTelefono";
          try {
              object.put("telefono",buscar.getText().toString());
          } catch (JSONException e) {
              e.printStackTrace();
          }
          cambio=true;
      }

        if (cambio) {
            JsonObjectRequest stringRequest1= new JsonObjectRequest(Request.Method.GET,url,object,this,this);
            stringRequest1.setRetryPolicy(new DefaultRetryPolicy(6000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
            //  queue.add(jsonObjectRequest);
            Volley.newRequestQueue(getContext()).add(stringRequest1);

        }
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
    List<Viaje> viajes;
    @Override
    public void onResponse(JSONObject response) {

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
