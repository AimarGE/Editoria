package com.example.editoria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.editoria.GlobalVariable;
import com.example.editoria.R;
import com.example.editoria.model.CartaRecursosClientes;
import com.example.editoria.model.ListElement;
import com.example.editoria.model.RecursosCliente;

import java.util.ArrayList;


public class EnviarProyectoEditorFragment extends Fragment {

    View view;
    RecursosCliente recursosCliente;
    String nombre;
    double precio;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_enviar_proyecto_editor, container, false);
        recursosCliente = (RecursosCliente) GlobalVariable.bundleSolicitudOferta.getSerializable("recursosCliente");
        nombre = recursosCliente.getNombreCliente();
        precio = recursosCliente.getPrecio();

        init();

        return view;
    }

    private void init() {

        mostrarRecursoCliente();
        listeners();

    }

    private void listeners() {
    }


    private void mostrarRecursoCliente() {

        ArrayList elements = new ArrayList<>();

        //CAMBIAR
        elements.add(new ListElement("icono", nombre,"", "", precio));

        CartaRecursosClientes cartaRecursosClientes = new CartaRecursosClientes(elements, view.getContext(), new CartaRecursosClientes.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {

            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.recursoCliente);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(cartaRecursosClientes);
        recyclerView.setNestedScrollingEnabled(false);



    }

}