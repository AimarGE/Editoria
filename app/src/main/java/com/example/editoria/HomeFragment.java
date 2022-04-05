package com.example.editoria;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeFragment extends Fragment {

    Button filtro;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        filtro = view.findViewById(R.id.filtro);

        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*crearDialogoFiltro();*/
            }
        });




        return view;
    }

    /*private void crearDialogoFiltro() {

        AlertDialog.Builder builder = new AlertDialog.Builder();
        builder.setTitle("Filtro");
        builder.setMultiChoiceItems(https://www.youtube.com/watch?v=1vIRg6_a_fc&ab_channel=TechnicalSkillz);

        builder.create();
        builder.show();

    }*/
}