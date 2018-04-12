package com.example.luis.gestion_viajes;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.example.luis.gestion_viajes.objetos.Colonia;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.zip.Inflater;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Nueva_colonia.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Nueva_colonia#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Nueva_colonia extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Nueva_colonia() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Nueva_colonia.
     */
    // TODO: Rename and change types and number of parameters
    public static Nueva_colonia newInstance(String param1, String param2) {
        Nueva_colonia fragment = new Nueva_colonia();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;


    }
            Button registrar;
            EditText nombre;
            String url_post = "http://rtaxis.uttsistemas.com/nuevacolonia";
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
        View v = inflater.inflate(R.layout.fragment_nueva_colonia, container, false);
        registrar=(Button)v.findViewById(R.id.registrar);
        nombre=(EditText)v.findViewById(R.id.txt_nombre);

        request = Singleton.getInstance(getContext()).getRequestQueue();
       registrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               JSONObject colonia = new JSONObject();
               try {
                   colonia.put("nombre", nombre.getText().toString());

               } catch (JSONException ex) {
                   ex.printStackTrace();
               }


               JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url_post, colonia, new Response.Listener<JSONObject>() {
                   @Override
                   public void onResponse(JSONObject response) {
                       Toast.makeText(getContext(), "regitro existoso", Toast.LENGTH_SHORT).show();
                        Log.d("Kek", ""+response);
                        nombre.setText("");

                   }
               },
                       new Response.ErrorListener() {
                           @Override
                           public void onErrorResponse(VolleyError error) {
                               Toast.makeText(getContext(), "regitro fallido", Toast.LENGTH_SHORT).show();
                               Log.d("error", ""+error);
                           }
                       }
               );

               jsonObjectRequest.setRetryPolicy(new DefaultRetryPolicy(10000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
               request.add(jsonObjectRequest);
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
