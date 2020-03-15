package com.example.proyectofinalandroid.fragments;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.proyectofinalandroid.R;
import com.example.proyectofinalandroid.adaptadores.PilotosAdaptador;
import com.example.proyectofinalandroid.utils.Piloto;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class FragmentPilotos extends Fragment {
    private RecyclerView lista;
    private ArrayList<Piloto> listaPilotos;
    private PilotosAdaptador pilotosAdaptador;
    String elemento;

    public FragmentPilotos(String elemento) {
        this.elemento = elemento;
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        pilotosAdaptador = new PilotosAdaptador(context);
        listaPilotos = new ArrayList();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_piloto, container, false);
        lista = v.findViewById(R.id.lista_pilotos);
        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        JsonObjectRequest peticionJson = new JsonObjectRequest(Request.Method.GET,
                "http://ergast.com/api/f1/2019/drivers.json", null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject jsonObject = response.getJSONObject("MRData");



                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.v("volley","Error en la conexion");
            }
        });
        Volley.newRequestQueue(getContext()).add(peticionJson);
        lista.setAdapter(pilotosAdaptador);
        lista.setLayoutManager(new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false));
    }
}
