package com.example.editoria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.editoria.R;


public class MiCarteraFragmentEditor extends Fragment {

    View view;
    Button mas100, menos100, confirmar;
    TextView dinero;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mi_cartera, container, false);
        mas100 = view.findViewById(R.id.mas100);
        menos100 = view.findViewById(R.id.menos100);
        dinero = view.findViewById(R.id.dinero_modificado);
        confirmar = view.findViewById(R.id.confirmar);
        init();

        return view;
    }

    private void init() {
        Log.i("EJEMPLO", "aaa");
        listeners();

    }

    private void listeners() {

        mas100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dineroS = dinero.getText().toString().replace("€", "");

                Double dineroD = Double.valueOf(dineroS)+100.00;

                dineroS = String.format("%.2f", dineroD).replace(",",".");

                dinero.setText(dineroS+"€");


            }
        });

        menos100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String dineroS = dinero.getText().toString().replace("€", "");

                Double dineroD = Double.valueOf(dineroS)-100.00;

                dineroS = String.format("%.2f", dineroD).replace(",",".");

                dinero.setText(dineroS+"€");

            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //CAMBIAR EL DINERO DE LA CARTERA DEL USUARIO
                getFragmentManager().popBackStackImmediate();

            }
        });

    }
}