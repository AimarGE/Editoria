package com.example.editoria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.model.ListAdapter;
import com.example.editoria.model.ListElement;

import java.util.ArrayList;
import java.util.List;


public class MenuProyectoFragmentCliente extends Fragment {

    LinearLayout verCartera;
    View view;
    ImageView verEstadoServicios;
    List<ListElement> elements;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_menu_proyecto_cliente, container, false);
        verEstadoServicios = view.findViewById(R.id.verOfertas);
        verCartera = view.findViewById(R.id.verCarteraLL);


        init();

        return view;
    }

    private void init() {
        borrarPilaFragments();
        listeners();
        mostrarServiciosContratados();

    }

    private void listeners() {

        //Listener para aceptar la entrega
        verCartera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainFragmentContainer.bottomNavigation.show(4, true);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, new MiCarteraFragmentCliente()).addToBackStack("tag");
                ft.commit();
            }
        });

        //Listener del icono de notificación
        verEstadoServicios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainFragmentContainer.bottomNavigation.show(4, true);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, new EstadoServiciosFragmentCliente()).addToBackStack("tag");
                ft.commit();

            }
        });
    }

    private void mostrarServiciosContratados() {

        elements = new ArrayList<>();
        //OBTENER INFORMACIÓN DE LA BASE DE DATOS Y INSERTARLA AQUÍ PARA QUE SE MUESTRE
        elements.add(new ListElement("icono", "Mario Servicios", "","", "https://media.revistagq.com/photos/5ca5f6a77a3aec0df5496c59/master/w_1600%2Cc_limit/bob_esponja_9564.png" ,930.02));
        elements.add(new ListElement("icono", "Ejemplo2 Servicios", "","", "https://media.revistagq.com/photos/5ca5f6a77a3aec0df5496c59/master/w_1600%2Cc_limit/bob_esponja_9564.png" , 3420.02));
        elements.add(new ListElement("icono", "José Servicios", "","",  "https://media.revistagq.com/photos/5ca5f6a77a3aec0df5496c59/master/w_1600%2Cc_limit/bob_esponja_9564.png" ,510.02));
        elements.add(new ListElement("icono", "Mario Servicios", "","", "https://media.revistagq.com/photos/5ca5f6a77a3aec0df5496c59/master/w_1600%2Cc_limit/bob_esponja_9564.png" ,800.02));
        elements.add(new ListElement("icono", "Ejemplo2 Servicios", "","", "https://media.revistagq.com/photos/5ca5f6a77a3aec0df5496c59/master/w_1600%2Cc_limit/bob_esponja_9564.png" ,33.02));
        elements.add(new ListElement("icono", "José Servicios", "","", "https://media.revistagq.com/photos/5ca5f6a77a3aec0df5496c59/master/w_1600%2Cc_limit/bob_esponja_9564.png" ,555.02));
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


    private void borrarPilaFragments(){
        int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < count; i++) { getActivity().getSupportFragmentManager().popBackStack(); }

    }
}