package com.example.editoria;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.editoria.model.ListAdapter;
import com.example.editoria.model.ListElement;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    Button filtro;
    List<ListElement> elements;
    View view;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        init();



        filtro = view.findViewById(R.id.filtro);
        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*crearDialogoFiltro();*/
            }
        });




        return view;
    }

    private void init() {

        elements = new ArrayList<>();

        elements.add(new ListElement("icono", "Mario"));
        elements.add(new ListElement("icono", "Ejemplo2"));
        elements.add(new ListElement("icono", "José"));
        elements.add(new ListElement("icono", "Maria"));
        elements.add(new ListElement("icono", "Rodrigo"));

        ListAdapter listAdapter = new ListAdapter(elements, view.getContext());
        RecyclerView recyclerView = view.findViewById(R.id.listaCVEditoresRecomendados);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);

        recyclerView.setOverScrollMode(view.OVER_SCROLL_NEVER);
    }

    /*private void crearDialogoFiltro() {

        AlertDialog.Builder builder = new AlertDialog.Builder();
        builder.setTitle("Filtro");
        builder.setMultiChoiceItems(https://www.youtube.com/watch?v=1vIRg6_a_fc&ab_channel=TechnicalSkillz);

        builder.create();
        builder.show();

    }*/
}