package com.example.editoria.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.editoria.R;
import com.example.editoria.model.ListAdapter;
import com.example.editoria.model.ListElement;

import java.util.ArrayList;
import java.util.List;

public class EventoPaginaPrincipalFragment extends Fragment {
    View view;
    List<ListElement> elements;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_evento_pagina_principal, container, false);


        init();

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
        RecyclerView recyclerView = view.findViewById(R.id.listaParticipanetesEventos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }

}