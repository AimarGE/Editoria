package com.example.editoria.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;


public class DatosTargetaFragment extends Fragment {

    View view;
    Button pagar;
    AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_datos_targeta, container, false);
        pagar = view.findViewById(R.id.pagar);
        builder = new AlertDialog.Builder(view.getContext());

        init();

        return view;
    }

    private void init() {

        listeners();

    }

    private void listeners() {

        pagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmarPago();

            }
        });


    }

    private void confirmarPago() {


        builder.setTitle("¿Confirmar pago?")
                .setCancelable(true)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                            pagoConfirmado();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();

    }

    private void pagoConfirmado() {

        borrarPilaFragments();

        MainFragmentContainer.bottomNavigation.show(4, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new MenuProyectoFragmentEditor());
        ft.commit();

    }


    private void borrarPilaFragments(){
        int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();

        for (int i = 0; i < count; i++) { getActivity().getSupportFragmentManager().popBackStack(); }

    }
}