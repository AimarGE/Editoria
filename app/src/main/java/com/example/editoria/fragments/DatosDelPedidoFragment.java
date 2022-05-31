package com.example.editoria.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;


public class DatosDelPedidoFragment extends Fragment {

    View view;
    Button continuarPago, seleccionar;
    ImageView foto;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_datos_del_pedido, container, false);
        continuarPago = view.findViewById(R.id.continuar_pago);
        foto = view.findViewById(R.id.foto);
        seleccionar = view.findViewById(R.id.seleccionarArchivo);


        init();
        
        
        return view;
    }

    private void init() {

        listeners();
        
    }

    private void listeners() {

        continuarPago.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                MainFragmentContainer.bottomNavigation.show(1, true);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, new DatosTargetaFragment()).addToBackStack("tag");
                ft.commit();
            }
        });

        seleccionar.setOnClickListener(new View.OnClickListener() {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri path = data.getData();
            foto.setImageURI(path);//SET IMAGE DEL CARDVIEW DEL
        }

    }
}