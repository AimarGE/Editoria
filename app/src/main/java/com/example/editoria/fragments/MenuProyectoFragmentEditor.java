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
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.editoria.GlobalVariable;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.model.ListAdapter;
import com.example.editoria.model.ListElement;
import com.example.editoria.model.RecursosCliente;

import java.util.ArrayList;
import java.util.List;

public class MenuProyectoFragmentEditor extends Fragment {

    View view;
    List<ListElement> elements;
    Button crearServicio;
    ImageView verOfertas;
    LinearLayout verCartera;
    AlertDialog.Builder builder;
    TextView dinero;

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
        builder = new AlertDialog.Builder(view.getContext());
        verCartera = view.findViewById(R.id.verCarteraLL);
        dinero = view.findViewById(R.id.dinero);

        init();



        return view;
    }

    private void init() {

        Log.i("EJEMPLO", "Aaaaaa");

        dinero.setText(GlobalVariable.usuario.getDinero()+"€");
        mostrarMisServicios();
        mostrarServiciosContratados();


        listeners();


    }

    private void listeners() {

        verCartera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainFragmentContainer.bottomNavigation.show(4, true);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, new MiCarteraFragmentEditor()).addToBackStack("tag");
                ft.commit();

            }
        });

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
        elements.add(new ListElement("https://i0.wp.com/www.senpai.com.mx/wp-content/uploads/2020/08/8dc3fa32016308543e1f34f4c1996a1747.jpg?fit=1280%2C720&ssl=1", "Peach Cliente", "","", "https://preview.redd.it/xmotbwksh7911.jpg?auto=webp&s=de75f9fff1e3521550925291c68e4ea499a48e0d" ,"19.99", ""));

        ListAdapter listAdapter = new ListAdapter(elements, view.getContext(), new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                dialogoServicioContratado(item);
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
        elements.add(new ListElement("https://img.unocero.com/2020/07/Super-Mario-Bros-verdadera-nacionalidad-1024x576.jpg", "MarioWopi", "","", "https://cloudfront-us-east-1.images.arcpublishing.com/infobae/MN2XNZY4XJH27KAROURFGYWLYY.jpg" ,"49.99", ""));
        elements.add(new ListElement("https://img.unocero.com/2020/07/Super-Mario-Bros-verdadera-nacionalidad-1024x576.jpg", "MarioWopi", "","", "https://preview.redd.it/xmotbwksh7911.jpg?auto=webp&s=de75f9fff1e3521550925291c68e4ea499a48e0d" , "19.99", ""));
        elements.add(new ListElement("https://img.unocero.com/2020/07/Super-Mario-Bros-verdadera-nacionalidad-1024x576.jpg", "MarioWopi", "","",  "https://c.tenor.com/P0H97FaEQhoAAAAM/mario-edit-edit.gif" ,"10.99", ""));

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

    private void dialogoServicioContratado(ListElement item) {

        builder.setTitle("Seleccione una opción")
                .setCancelable(true)
                .setPositiveButton("Ver información", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       verInformacionServicioCliente(item);
                    }
                })
                .setNegativeButton("Enviar proyecto", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        enviarProyecto(item);
                    }
                })
                .show();

    }

    private void enviarProyecto(ListElement item) {

        MainFragmentContainer.bottomNavigation.show(4, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        RecursosCliente recursosCliente = new RecursosCliente(item.getName(), "imagen", "descripcion", Double.parseDouble(item.getPrecio()));

        Bundle bundle = new Bundle();
        bundle.putSerializable("recursosCliente", recursosCliente);
        GlobalVariable.bundleSolicitudOferta = bundle;

        ft.replace(R.id.mainFrame, new EnviarProyectoEditorFragment()).addToBackStack("tag");
        ft.commit();

    }

    private void verInformacionServicioCliente(ListElement item) {

        MainFragmentContainer.bottomNavigation.show(4, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        RecursosCliente recursosCliente = new RecursosCliente(item.getName(), "imagen", "descripcion", Double.parseDouble(item.getPrecio()));

        Bundle bundle = new Bundle();
        bundle.putSerializable("recursosCliente", recursosCliente);
        GlobalVariable.bundleSolicitudOferta = bundle;

        ft.replace(R.id.mainFrame, new DescargarRecursoFragmentEditor()).addToBackStack("tag");
        ft.commit();

    }
}