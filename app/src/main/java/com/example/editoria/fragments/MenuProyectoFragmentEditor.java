package com.example.editoria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.model.ListAdapter;
import com.example.editoria.model.ListElement;

import java.util.ArrayList;
import java.util.List;

public class MenuProyectoFragmentEditor extends Fragment {

    View view;
    List<ListElement> elements;
    Button crearServicio;
    ImageView verOfertas;

    public MenuProyectoFragmentEditor() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu_proyecto_editor, container, false);
        crearServicio = view.findViewById(R.id.botonCrearNuevoServicio);
        verOfertas = view.findViewById(R.id.verOfertas);


        init();



        return view;
    }

    private void init() {


        mostrarMisServicios();
        mostrarServiciosContratados();

        listeners();


    }

    private void listeners() {

        verOfertas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainFragmentContainer.bottomNavigation.show(4, true);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, new SolicitudesOfertaEditorFragment()).addToBackStack("tag");
                ft.commit();

            }
        });

        //LISTENER PARA CUANDO LE DE AL BOTÓN DE CREAR SERVICIO
        crearServicio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainFragmentContainer.bottomNavigation.show(4, true);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, new ProyectosFragment()).addToBackStack("tag");
                ft.commit();

            }
        });

    }

    private void mostrarServiciosContratados() {

        elements = new ArrayList<>();
        //OBTENER INFORMACIÓN DE LA BASE DE DATOS Y INSERTARLA AQUÍ PARA QUE SE MUESTRE
        elements.add(new ListElement("icono", "Mario Servicios Contratados", "","", "Precio: "+ 30.02+"€"));
        elements.add(new ListElement("icono", "Ejemplo2 Servicios Contratados", "","", "Precio: "+ 30.02+"€"));
        elements.add(new ListElement("icono", "José Servicios Contratados", "","", "Precio: "+ 30.02+"€"));
        elements.add(new ListElement("icono", "Mario Servicios Contratados", "","", "Precio: "+ 30.02+"€"));
        elements.add(new ListElement("icono", "Ejemplo2 Servicios Contratados", "","", "Precio: "+ 30.02+"€"));
        elements.add(new ListElement("icono", "José Servicios Contratados", "","", "Precio: "+ 30.02+"€"));
        ListAdapter listAdapter = new ListAdapter(elements, view.getContext(), new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {

            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.listaServiciosContratados);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }

    private void mostrarMisServicios() {

        elements = new ArrayList<>();
        //OBTENER INFORMACIÓN DE LA BASE DE DATOS Y INSERTARLA AQUÍ PARA QUE SE MUESTRE
        elements.add(new ListElement("icono", "Mario MisServicios", "","", "Precio: "+ 30.02+"€"));
        elements.add(new ListElement("icono", "Ejemplo2 MisServicios", "","", "Precio: "+ 30.02+"€"));
        elements.add(new ListElement("icono", "José MisServicios", "","", "Precio: "+ 30.02+"€"));
        elements.add(new ListElement("icono", "Maria MisServicios", "","", "Precio: "+ 30.02+"€"));
        elements.add(new ListElement("icono", "Rodrigo MisServicios", "","", "Precio: "+ 30.02+"€"));

        ListAdapter listAdapter = new ListAdapter(elements, view.getContext(), new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {

            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.listaMisServicios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }
}