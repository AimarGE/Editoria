package com.example.editoria.fragments;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.editoria.MainFragmentContainer;
import com.example.editoria.fragments.HomeFragment;
import com.example.editoria.model.Proyecto;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.editoria.R;

public class ProyectosFragment extends Fragment {

    ImageView imagen;
    Button galeria;
    Button publicar;
    TextView paquetes;
    private EditText nombreProyecto;
    private EditText descripcionProyecto;
    private CheckBox manyana, tarde, noche;
    private String disponibilidad, nombreP,descripcionP;
    private DatabaseReference dRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/");
    private View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_proyectos, container, false);

        nombreProyecto = (EditText) view.findViewById(R.id.nombreP);
        descripcionProyecto = (EditText) view.findViewById(R.id.descripcionP);
        imagen = (ImageView) view.findViewById(R.id.foto);
        galeria = (Button) view.findViewById(R.id.botonImagen);
        publicar = (Button) view.findViewById(R.id.publicar);
        paquetes = (TextView) view.findViewById(R.id.textViewAnyadirPaquetes);
        manyana = (CheckBox) view.findViewById(R.id.checkBoxManana);
        noche = (CheckBox) view.findViewById(R.id.checkBoxNoche);
        tarde = (CheckBox) view.findViewById(R.id.checkBoxTarde);

        paquetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Ejemplo", "asd");
            }
        });

        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });

        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                publicar();
            }
        });


        return view;
    }

    private void publicar() {
        disponibilidad="";
        nombreP= nombreProyecto.getText().toString();
        descripcionP= descripcionProyecto.getText().toString();
        if(manyana.isChecked()){
            disponibilidad += manyana.getText().toString();
        }
        if(tarde.isChecked()){
            if(!disponibilidad.equals("")) {
                disponibilidad += ",";
                disponibilidad += tarde.getText().toString();
            }
            else{
                disponibilidad = tarde.getText().toString();
            }
        }
        if(noche.isChecked()){
            if(!disponibilidad.equals("")) {
                disponibilidad += ",";
                disponibilidad += noche.getText().toString();
            }
            else{
                disponibilidad = noche.getText().toString();
            }
        }
        if(comprobarLenghtNombre(nombreP) && comprobarDescripcion(descripcionP)){
           addProyecto();
            MainFragmentContainer.bottomNavigation.show(1, true);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, new HomeFragment());
            ft.commit();
        }
    }

    private void addProyecto(){
        Proyecto proyecto = new Proyecto(nombreP, descripcionP, disponibilidad, "Juan");
        dRef.child("Proyectos").child(proyecto.getNombreUsuario()).setValue(proyecto);
    }

    private boolean comprobarDescripcion(String descripcion){
        if(descripcion.length() < 5){
            Toast.makeText(view.getContext(), "Descripción muy corta", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean comprobarLenghtNombre(String nombre){
        if(nombre.length() < 5){
            Toast.makeText(view.getContext(), "Nombre muy corto", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
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
            imagen.setImageURI(path);
        }

    }
}