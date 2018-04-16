package com.example.luis.gestion_viajes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ventana.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ventana#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ventana extends Fragment implements Response.Listener<String>,Response.ErrorListener {
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


    ArrayList<Viaje> viajes= new ArrayList<>();
    String url="http://rtaxis.uttsistemas.com/verviajes";

    RequestQueue queue;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.fragment_ventana, container, false);



    cargarViajes();
        //añadir al adapter y al listview
        return view;
    }

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
        Toast.makeText(getContext(), "conectado", Toast.LENGTH_SHORT).show();
        Log.d("ejemplo", ""+response.toString());

        try{

            JSONArray array= new JSONArray(response);
            for (int i = 0; i < array.length(); i++) {

                JSONObject object= array.getJSONObject(i);

                viajes.add(new Viaje(
                        Integer.parseInt(object.getString("unidad")),
                        Integer.parseInt(object.getString("operadora")),
                        object.getString("telefono"),
                        object.getString("direccion"),
                        object.getString("fecha_viaje")
                ));
            }
            viajesAdapter viajesAdapter= new viajesAdapter(viajes,getContext());


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
