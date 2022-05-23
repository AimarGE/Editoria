package com.example.editoria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.editoria.R;
import com.example.editoria.model.CartaOfertasPendientes;
import com.example.editoria.model.CartaParticipantes;
import com.example.editoria.model.CartaRecursosClientes;
import com.example.editoria.model.ListElement;

import java.util.ArrayList;
import java.util.List;


public class DescargarRecursoFragmentEditor extends Fragment {

    View view;
    List<ListElement> elements;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_descargar_recurso_editor, container, false);

        init();


        return view;
    }

    private void init() {

        mostrarRecursoCliente();

    }

    private void mostrarRecursoCliente() {

        elements = new ArrayList<>();

        //CAMBIAR
        elements.add(new ListElement("icono", "Mario","", "", "Precio: "+ 30.02+"€"));

        CartaRecursosClientes listAdapter = new CartaRecursosClientes(elements, view.getContext(), new CartaRecursosClientes.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {

            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recursoCliente);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }
}