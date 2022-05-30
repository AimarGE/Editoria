package com.example.editoria.fragments;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import com.example.editoria.GlobalVariable;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.model.CartaOfertasPendientes;
import com.example.editoria.model.ListAdapter;
import com.example.editoria.model.ListElement;
import com.example.editoria.model.RecursosCliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class SolicitudesOfertaEditorFragment extends Fragment {

    View view;
    List<ListElement> elements;
    AlertDialog.Builder builder;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_solicitudes_oferta_editor, container, false);
        builder = new AlertDialog.Builder(view.getContext());
        init();

        return view;
    }

    private void init() {

        mostrarOfertasPendientes();

    }


    private void dialogAbandonarEvento(ListElement item) {

        builder.setTitle("¿Confirmar oferta?")
                .setCancelable(true)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        aceptarOferta(item);
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

    private void aceptarOferta(ListElement item) {


        MainFragmentContainer.bottomNavigation.show(4, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();
        //FALTA OBTENER INFORMACIÓN DESDE LA BASE DA DATOS

        RecursosCliente recursosCliente = new RecursosCliente(item.getName(), "imagen", "descripcion", Double.parseDouble(item.getPrecio()));

        bundle.putSerializable("recursosCliente", recursosCliente);
        GlobalVariable.bundleSolicitudOferta = bundle;

        //FUNCION EN LA BASE DE DATOS PARA CONFIRMAR LA OFERTA --> CONFIRMAR LA OFERTA EN EL WIREFRAME DEL CLIENTE

        ft.replace(R.id.mainFrame, new DescargarRecursoFragmentEditor()).addToBackStack("tag");
        ft.commit();

    }

    private void mostrarOfertasPendientes() {

        elements = new ArrayList<>();
        //OBTENER INFORMACIÓN DE LA BASE DE DATOS Y INSERTARLA AQUÍ PARA QUE SE MUESTRE
        elements.add(new ListElement("https://i.pinimg.com/originals/75/97/12/759712abd30ecec7865705483ddc3b52.png", "Luigi Cliente", "","Edito fotos retro", "https://www.smashbros.com/images/og/luigi.jpg","10.99"));

        CartaOfertasPendientes listAdapter = new CartaOfertasPendientes(elements, view.getContext(), new CartaOfertasPendientes.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                dialogAbandonarEvento(item);
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.listaServiciosPendiente);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }
}