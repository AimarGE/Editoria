package com.example.editoria.fragments;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.editoria.GlobalVariable;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.model.CartaRecursosClientes;
import com.example.editoria.model.ListElement;
import com.example.editoria.model.RecursosCliente;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DescargarRecursoFragmentCliente extends Fragment {

    View view;
    List<ListElement> elements;
    Button descargar;
    RecursosCliente recursosCliente;
    String nombre;
    double precio;
    CartaRecursosClientes cartaRecursosClientes;
    AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_descargar_recurso_editor, container, false);

        recursosCliente = (RecursosCliente) GlobalVariable.bundleSolicitudOferta.getSerializable("recursosCliente");
        nombre = recursosCliente.getNombreCliente();
        precio = recursosCliente.getPrecio();
        descargar = view.findViewById(R.id.descargar);
        builder = new AlertDialog.Builder(view.getContext());

        init();

        return view;
    }

    private void init() {


        mostrarRecursoCliente();
        listeners();


    }

    private void listeners() {

        descargar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    descagarImagen();
                    dialogoValorarServicio();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }
    private void dialogoValorarServicio() {

        builder.setTitle("¿Quieres valorar el servicio?")
                .setCancelable(true)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        valorar();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        menuProyectos();
                    }
                })
                .show();

    }

    private void valorar() {

        MainFragmentContainer.bottomNavigation.show(4, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new ValorarFragment()).addToBackStack("tag");
        ft.commit();

    }

    private void menuProyectos() {

        MainFragmentContainer.bottomNavigation.show(4, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new MenuProyectoFragmentCliente());
        ft.commit();

    }


    private void descagarImagen() throws IOException {

        Log.i("EJEMPLO", "aa ->"+ cartaRecursosClientes.getImageView());

        BitmapDrawable draw = (BitmapDrawable) cartaRecursosClientes.getImageView().getDrawable();
        Bitmap bitmap = draw.getBitmap();

        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "tituloNobita" , "descripcionNobita");

    }

    private void mostrarRecursoCliente() {

        elements = new ArrayList<>();

        //CAMBIAR
        elements.add(new ListElement("icono", nombre,"", "", "https://www.exclusivadigital.com/fotos/16368848691.jpg", "50"));

        cartaRecursosClientes = new CartaRecursosClientes(elements, view.getContext(), new CartaRecursosClientes.OnItemClickListener() {
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