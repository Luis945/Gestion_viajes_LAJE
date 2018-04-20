package com.example.luis.gestion_viajes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link nueva_Basee.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link nueva_Basee#newInstance} factory method to
 * create an instance of this fragment.
 */
public class nueva_Basee extends Fragment{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    ProgressBar cargando;
    Handler handler = new Handler();
    int contadorcargando=0;
    public nueva_Basee() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment nueva_Basee.
     */
    // TODO: Rename and change types and number of parameters
    public static nueva_Basee newInstance(String param1, String param2) {
        nueva_Basee fragment = new nueva_Basee();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    Spinner combotipo;
    Button registrar,limpiar;
    EditText nombre,direccion;
    String url_post = "http://rtaxis.uttsistemas.com/nuevabase";
    RequestQueue request;
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
        View v = inflater.inflate(R.layout.fragment_nueva__basee, container, false);
        combotipo=v.findViewById(R.id.combotipobase);
        nombre=(EditText)v.findViewById(R.id.txt_nombre);
        direccion=(EditText)v.findViewById(R.id.txt_direccion);
        registrar=(Button)v.findViewById(R.id.registrar);
        limpiar=(Button)v.findViewById(R.id.limpiar);
        cargando=(ProgressBar)v.findViewById(R.id.barrawait);
        String [] opc = {"Base","Sub-Base"};
        combotipo.setAdapter(new ArrayAdapter<String>(getContext(),android.R.layout.simple_spinner_dropdown_item,opc));

        request = Singleton.getInstance(getContext()).getRequestQueue();




        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombre.getText().toString().isEmpty()||direccion.getText().toString().isEmpty())
                {
                    Toast.makeText(getContext(), "¡Completa los campos!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    cargando.setVisibility(View.VISIBLE);
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
                    JSONObject base = new JSONObject();
                    try {
                        base.put("nombre",nombre.getText().toString());
                        base.put("direccion",direccion.getText().toString());
                        base.put("tipo",combotipo.getSelectedItem().toString());
                    } catch (JSONException ex) {
                        ex.printStackTrace();
                    }


                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_post, base , new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            handler.post(new Runnable() {
                                @Override
                                public void run() {
                                    cargando.setVisibility(View.GONE);
                                }
                            });
                            Toast.makeText(getContext(), "Regitro existoso", Toast.LENGTH_SHORT).show();
                            Log.d("Kek", ""+response);
                            nombre.setText("");
                            direccion.setText("");


                        }
                    },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    handler.post(new Runnable() {
                                        @Override
                                        public void run() {
                                            cargando.setVisibility(View.GONE);
                                        }
                                    });
                                    Toast.makeText(getContext(), "Regitro fallido", Toast.LENGTH_SHORT).show();
                                    Log.d("error", ""+error);
                                }
                            }
                    );

                    jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
                    request.add(jsonObjectRequest);
                }

            }
        });
        limpiar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nombre.setText("");
                direccion.setText("");

            }
        });
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
