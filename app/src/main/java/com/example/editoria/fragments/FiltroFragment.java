package com.example.editoria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.editoria.GlobalVariable;
import com.example.editoria.R;

import java.util.ArrayList;


public class FiltroFragment extends Fragment {

    View view;
    ImageView filtro;
    Button confirmar;
    CheckBox dManyana, dTarde, dNoche;
    EditText precioMin, precioMax;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_filtro, container, false);
        filtro = view.findViewById(R.id.back);
        confirmar = view.findViewById(R.id.confirmar);
        dManyana = view.findViewById(R.id.disponibilidadManyana);
        dTarde = view.findViewById(R.id.disponibilidadTarde);
        dNoche = view.findViewById(R.id.disponibilidadNoche);
        precioMin = view.findViewById(R.id.precioMinimo);
        precioMax = view.findViewById(R.id.precioMaximo);


        init();

        return view;
    }

    private void init() {

        listeners();

    }

    private void listeners() {

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                guardarFiltros();

            }
        });


        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStackImmediate();
            }
        });
    }

    private void guardarFiltros() {

        GlobalVariable.filtroDisponibilidad = new ArrayList<String>();
        GlobalVariable.filtroPrecio = new ArrayList<Double>();

       if (dManyana.isChecked()){
           GlobalVariable.filtroDisponibilidad.add((String) dManyana.getText());
       }
       if(dTarde.isChecked()){
           GlobalVariable.filtroDisponibilidad.add((String) dTarde.getText());
       }
       if(dNoche.isChecked()){
           GlobalVariable.filtroDisponibilidad.add((String) dNoche.getText());
       }

       if (precioMin == null){
           GlobalVariable.filtroPrecio.add(00.00);
       }else{
           Double dineroD;

           String dineroS = precioMin.getText().toString();
           if (dineroS == null || dineroS.equals("")){
               dineroD = 00.00;
           }else{
               dineroD = Double.valueOf(dineroS);
           }

           dineroS = String.format("%.2f", dineroD).replace(",",".");

           GlobalVariable.filtroPrecio.add(Double.parseDouble(dineroS));
       }

       if (precioMax == null){
           GlobalVariable.filtroPrecio.add(100.00);
       }else{

           String dineroS = precioMax.getText().toString();
           Double dineroD;

           if (dineroS == null || dineroS.equals("")){
               dineroD = 100.00;
           }else{
               dineroD = Double.valueOf(dineroS);
           }

           dineroS = String.format("%.2f", dineroD).replace(",",".");

           GlobalVariable.filtroPrecio.add(Double.parseDouble(dineroS));
       }
        getFragmentManager().popBackStackImmediate();
    }

}