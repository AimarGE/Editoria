package com.example.editoria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;


public class DatosDelPedidoFragment extends Fragment {

    View view;
    Button continuarPago;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_datos_del_pedido, container, false);
        continuarPago = view.findViewById(R.id.continuar_pago);


        init();
        
        
        return view;
    }

    private void init() {

        listeners();
        
    }

    private void listeners() {

        continuarPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentContainer.bottomNavigation.show(1, true);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, new DatosTargetaFragment()).addToBackStack("tag");
                ft.commit();
            }
        });

    }
}