package com.example.editoria.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.editoria.GlobalVariable;
import com.example.editoria.MainActivity;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.Register;

public class OpcionesFragment extends Fragment {

    View view;
    TextView cerrarSesion;
    SharedPreferences.Editor editor;
    SharedPreferences preferences;
    AlertDialog.Builder builder;
    LinearLayout opcionesPerfil;
    ImageView back;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_opciones, container, false);
        cerrarSesion = view.findViewById(R.id.cerrarSesion);
        preferences = getActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        editor = preferences.edit();
        builder = new AlertDialog.Builder(view.getContext());
        opcionesPerfil= view.findViewById(R.id.opc_perfil);
        back = view.findViewById(R.id.back);

        init();

        return view;
    }

    private void init() {

        listeners();

    }

    private void listeners() {

        //LISTENER PARA RETROCEDER
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                retroceder();
            }
        });


        //LISTENER PARA ABRIR LA OPCION DE PERFIL
        opcionesPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                opcionesPerfil();
            }
        });

        //LISTENERS PARA CERRAR SESION
        cerrarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogoCerrarSesion();
            }
        });

    }

    private void retroceder() {

        getFragmentManager().popBackStackImmediate();

    }

    private void opcionesPerfil() {

        MainFragmentContainer.bottomNavigation.show(5, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new OpcionesPerfilEditorFragment()).addToBackStack("tag");
        ft.commit();

    }

    private void cerrarSesion() {

        editor.putBoolean("sesionIniciada",false);
        editor.commit();
        editor.putString("usuario", "null");

        GlobalVariable.nombreUsuario="null";

        Intent intent = new Intent(view.getContext(), MainActivity.class);
        startActivity(intent);

    }



    private void dialogoCerrarSesion() {

        builder.setTitle("¿Estás seguro que quieres cerrar sesión?")
                .setCancelable(true)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        cerrarSesion();
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
}