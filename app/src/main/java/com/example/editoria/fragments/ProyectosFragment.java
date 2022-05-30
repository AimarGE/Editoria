package com.example.editoria.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Settings;
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

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.example.editoria.GlobalVariable;
import com.example.editoria.Login;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.fragments.HomeFragment;
import com.example.editoria.model.Editor;
import com.example.editoria.model.Paquete;
import com.example.editoria.model.Proyecto;
import com.example.editoria.model.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.example.editoria.R;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class ProyectosFragment extends Fragment {

    ImageView imagen;
    Button galeria;
    Button publicar;
    //TextView paquetes;
    private EditText nombreProyecto;
    private EditText descripcionProyecto;
    private EditText precioPaqueteBasico, precioPaqueteAvanzado, precioPaquetePremium;
    private EditText descripcionPaqueteBasico, descripcionPaqueteAvanzado, descripcionPaquetePremium;
    private CheckBox manyana, tarde, noche;
    private String disponibilidad, nombreP, descripcionP;
    private DatabaseReference dRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/");
    private View view;
    private StorageReference storageRef;
    private String urlFoto;
    private boolean paqueteUno;
    private boolean paqueteDos;
    private boolean paqueteTres;
    private Paquete paquete;
    private Paquete paquete2;
    private Paquete paquete3;
    private ArrayList<Paquete> paquetes;
    private int contador;

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
        //paquetes = (TextView) view.findViewById(R.id.textViewAnyadirPaquetes);
        manyana = (CheckBox) view.findViewById(R.id.checkBoxManana);
        noche = (CheckBox) view.findViewById(R.id.checkBoxNoche);
        tarde = (CheckBox) view.findViewById(R.id.checkBoxTarde);
        storageRef = FirebaseStorage.getInstance().getReference("proyectos/");
        paquetes= new ArrayList<>();
        getEditor();
        /*paquetes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("Ejemplo", "asd");
            }
        });*/

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
        disponibilidad = "";
        nombreP = nombreProyecto.getText().toString();
        descripcionP = descripcionProyecto.getText().toString();
        if (manyana.isChecked()) {
            disponibilidad += manyana.getText().toString();
        }
        if (tarde.isChecked()) {
            if (!disponibilidad.equals("")) {
                disponibilidad += ",";
                disponibilidad += tarde.getText().toString();
            } else {
                disponibilidad = tarde.getText().toString();
            }
        }
        if (noche.isChecked()) {
            if (!disponibilidad.equals("")) {
                disponibilidad += ",";
                disponibilidad += noche.getText().toString();
            } else {
                disponibilidad = noche.getText().toString();
            }
        }
        if (checkPhoto() && comprobarLenghtNombre(nombreP) && comprobarDescripcion(descripcionP)) {
            if(paqueteUno){
                paquete = new Paquete("Básico", precioPaqueteBasico.getText().toString(), descripcionPaqueteBasico.getText().toString());
                paquetes.add(paquete);
            }
            if(paqueteDos){
                paquete2 = new Paquete("Avanzado", precioPaqueteAvanzado.getText().toString(), descripcionPaqueteAvanzado.getText().toString());
                paquetes.add(paquete2);
            }
            if(paqueteTres){
                paquete3 = new Paquete("Premium", precioPaquetePremium.getText().toString(), descripcionPaquetePremium.getText().toString());
                paquetes.add(paquete3);
            }
            addProyecto();
            MainFragmentContainer.bottomNavigation.show(1, true);
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.replace(R.id.mainFrame, new HomeFragment());
            ft.commit();
        }
    }


    private void addProyecto() {
        //getListProyectos();
        storageRef = FirebaseStorage.getInstance().getReference("proyectos/");
        storageRef.child(nombreP.replace(" ", "_")).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                urlFoto = uri.toString();
                Proyecto proyecto = new Proyecto(nombreP, descripcionP, disponibilidad, GlobalVariable.nombreUsuario, urlFoto, paquetes);
                if(GlobalVariable.editor != null){
                    Log.i("entra", GlobalVariable.editor.toString());
                    GlobalVariable.editor.addProyecto(proyecto);
                }
                actualizarEditor();
                dRef.child("Proyectos").child(proyecto.getNombreUsuario()).setValue(GlobalVariable.editor.getProyectos());
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.i("hola", exception.toString());
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                    addProyecto();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
/*
    private List<Proyecto> getListProyectos(){
        List<Proyecto> proyectos = new ArrayList<>();
        dRef.child("Proyectos").child(GlobalVariable.usuario.getUsuario()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Proyecto proyecto = postSnapshot.getValue(Proyecto.class);
                    proyectos.add(proyecto);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/
    private void actualizarEditor(){
        dRef.child("Editores").child(GlobalVariable.usuario.getUsuario()).setValue(GlobalVariable.editor);
    }

    private boolean comprobarDescripcion(String descripcion) {
        if (descripcion.length() < 5) {
            Toast.makeText(view.getContext(), "Descripción muy corta", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean comprobarLenghtNombre(String nombre) {
        if (nombre.length() < 5) {
            Toast.makeText(view.getContext(), "Nombre muy corto", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean comprobarPaquetes(){
        contador = 0;
        String precioBasico = precioPaqueteBasico.getText().toString();
        String descripcionBasico = descripcionPaqueteBasico.getText().toString();
        String precioAvanzado = precioPaqueteAvanzado.getText().toString();
        String descripcionAvanzado = descripcionPaqueteAvanzado.getText().toString();
        String precioPremium = precioPaquetePremium.getText().toString();
        String descripcionPremium = descripcionPaquetePremium.getText().toString();
        paqueteUno=comprobarPaqueteBasico(precioBasico, descripcionBasico);
        paqueteDos=comprobarPaqueteAvanzado(precioAvanzado, descripcionAvanzado);
        paqueteTres=comprobarPaquetePremium(precioPremium, descripcionPremium);
        if(contador == 0){
            Toast.makeText(view.getContext(), "Debes tener al menos un paquete", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private boolean comprobarPaqueteBasico(String precio, String descripcion){
        if(precio.equals("") && descripcion.equals("")){
            return false;
        }
        else if(!precio.equals("") && !precio.equals(" ") && !descripcion.equals("") && !descripcion.equals(" ")){
            contador++;
            return true;
        }
        return false;
    }

    private boolean comprobarPaqueteAvanzado(String precio, String descripcion){
        if(precio.equals("") && descripcion.equals("")){
            return false;
        }
        else if(!precio.equals("") && !precio.equals(" ") && !descripcion.equals("") && !descripcion.equals(" ")){
            contador++;
            return true;
        }
        return false;
    }

    private boolean comprobarPaquetePremium(String precio, String descripcion){
        if(precio.equals("") && descripcion.equals("")){
            return false;
        }
        else if(!precio.equals("") && !precio.equals(" ") && !descripcion.equals("") && !descripcion.equals(" ")){
            contador++;
            return true;
        }
        return false;
    }

    private boolean checkPhoto() {
        if (imagen != null) {
            storageRef = FirebaseStorage.getInstance().getReference("proyectos/");
            imagen.setDrawingCacheEnabled(true);
            imagen.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable) imagen.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = storageRef.child(nombreP.replace(" ", "_")).putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.i("imagenF", exception.getMessage());
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.i("imagenF", "subida correcta");
                }
            });
            return true;
        }
        return false;
    }

    private void getEditor(){
        dRef.child("Editores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(GlobalVariable.usuario.getUsuario())) {
                    GlobalVariable.editor = snapshot.child(GlobalVariable.usuario.getUsuario()).getValue(Editor.class);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

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
            imagen.setImageURI(path);
        }

    }
}