package com.example.editoria.fragments;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.editoria.GlobalVariable;
import com.example.editoria.MainActivity;
import com.example.editoria.R;
import com.example.editoria.model.CartaOfertasPendientes;
import com.example.editoria.model.CartaParticipantes;
import com.example.editoria.model.CartaRecursosClientes;
import com.example.editoria.model.ListElement;
import com.example.editoria.model.RecursosCliente;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class DescargarRecursoFragmentEditor extends Fragment {

    View view;
    List<ListElement> elements;
    Button descargar;
    RecursosCliente recursosCliente;
    String nombre;
    double precio;
    CartaRecursosClientes cartaRecursosClientes;

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
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

    }

    private void descagarImagen() throws IOException {

        Log.i("EJEMPLO", "aa ->"+ cartaRecursosClientes.getImageView());

        BitmapDrawable draw = (BitmapDrawable) cartaRecursosClientes.getImageView().getDrawable();
        Bitmap bitmap = draw.getBitmap();

        MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "tituloNobita" , "descripcionNobita");

       /* FileOutputStream outStream = null;
        File sdCard = Environment.getExternalStorageDirectory();
        File dir = new File(sdCard.getAbsolutePath() + "/Editoria/");
        dir.mkdirs();

        Log.i("EJEMPLO", "direcotrio -> "+dir.toString());

        String fileName = String.format("%d.jpg", System.currentTimeMillis());
        File outFile = new File(dir, fileName);
        outStream = new FileOutputStream(outFile);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        outStream.flush();
        outStream.close();*/

    }

    private void mostrarRecursoCliente() {

        elements = new ArrayList<>();

        //CAMBIAR
        elements.add(new ListElement("icono", nombre,"", "", "https://www.exclusivadigital.com/fotos/16368848691.jpg", precio));

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