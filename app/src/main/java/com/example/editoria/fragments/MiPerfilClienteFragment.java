package com.example.editoria.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.editoria.GlobalVariable;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;


public class MiPerfilClienteFragment extends Fragment {


    ImageView opciones;
    TextView nombreCliente;
    View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_mi_perfil_cliente, container, false);
        opciones = view.findViewById(R.id.opciones);
        nombreCliente = view.findViewById(R.id.nombreMiCliente);



        nombreCliente.setText(GlobalVariable.usuario.getUsuario());

        //abrir ajustes
        opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirOpciones();
            }
        });


        return view;
    }


    private void abrirOpciones() {

        MainFragmentContainer.bottomNavigation.show(5, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new OpcionesFragment()).addToBackStack("tag");
        ft.commit();

    }


}