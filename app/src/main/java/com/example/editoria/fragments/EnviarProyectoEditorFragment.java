package com.example.editoria.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

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
    Button seleccionarArchivo;

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
        seleccionarArchivo = view.findViewById(R.id.seleccionarArchivo);


        init();

        return view;
    }

    private void init() {

        mostrarRecursoCliente();
        listeners();

    }

    private void listeners() {

        seleccionarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });

    }

    private void cargarImagen() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);

    }


    private void mostrarRecursoCliente() {

        ArrayList elements = new ArrayList<>();

        //CAMBIAR
        elements.add(new ListElement("icono", nombre,"", "","https://uning.es/wp-content/uploads/2016/08/ef3-placeholder-image.jpg", precio));

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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri path = data.getData();
            imagen.setImageURI(path);//SET IMAGE DEL CARDVIEW DEL
        }

    }

}