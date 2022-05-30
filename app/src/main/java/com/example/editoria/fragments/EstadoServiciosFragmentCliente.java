package com.example.editoria.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.editoria.GlobalVariable;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.model.ListAdapter;
import com.example.editoria.model.ListElement;
import com.example.editoria.model.RecursosCliente;

import java.util.ArrayList;


public class EstadoServiciosFragmentCliente extends Fragment {

    View view;
    ArrayList<ListElement> elements;
    AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_estado_servicios_cliente, container, false);
        builder = new AlertDialog.Builder(view.getContext());


        init();


        return view;
    }

    private void init() {

        mostrarServiciosPendientes();
        mostrarProyectosConfirmarEntrega();

    }



    private void mostrarServiciosPendientes() {

        elements = new ArrayList<>();
        //OBTENER INFORMACIÓN DE LA BASE DE DATOS Y INSERTARLA AQUÍ PARA QUE SE MUESTRE
        elements.add(new ListElement("icono", "Mario Pendiente", "","", "https://media.revistagq.com/photos/5ca5f6a77a3aec0df5496c59/master/w_1600%2Cc_limit/bob_esponja_9564.png" ,"930.02", ""));
        elements.add(new ListElement("icono", "Ejemplo2 Pendiente", "","", "https://media.revistagq.com/photos/5ca5f6a77a3aec0df5496c59/master/w_1600%2Cc_limit/bob_esponja_9564.png" , "3420.02", ""));
     ListAdapter listAdapter = new ListAdapter(elements, view.getContext(), new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {

            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.listaServiciosPendiente);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }




    private void mostrarProyectosConfirmarEntrega() {

        elements = new ArrayList<>();
        //OBTENER INFORMACIÓN DE LA BASE DE DATOS Y INSERTARLA AQUÍ PARA QUE SE MUESTRE
        elements.add(new ListElement("icono", "Mario Confirmar", "","", "https://media.revistagq.com/photos/5ca5f6a77a3aec0df5496c59/master/w_1600%2Cc_limit/bob_esponja_9564.png" ,"930.02",""));
        elements.add(new ListElement("icono", "Ejemplo2 Confirmar", "","", "https://media.revistagq.com/photos/5ca5f6a77a3aec0df5496c59/master/w_1600%2Cc_limit/bob_esponja_9564.png" , "3420.02",""));
      ListAdapter listAdapter = new ListAdapter(elements, view.getContext(), new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                dialogoConfirmarProyecto(item);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.listaEntregasPendientes);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }


    private void dialogoConfirmarProyecto(ListElement item) {

        builder.setTitle("¿Confirmar Proyecto?")
                .setCancelable(true)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        descargarProyecto(item);
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

    private void descargarProyecto(ListElement item) {

        MainFragmentContainer.bottomNavigation.show(4, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        RecursosCliente recursosCliente = new RecursosCliente(item.getName(), "imagen", "descripcion", Double.parseDouble(item.getPrecio()));

        Bundle bundle = new Bundle();
        bundle.putSerializable("recursosCliente", recursosCliente);
        GlobalVariable.bundleSolicitudOferta = bundle;

        ft.replace(R.id.mainFrame, new DescargarRecursoFragmentCliente()).addToBackStack("tag");
        ft.commit();

    }


}