package com.example.editoria.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.editoria.GlobalVariable;
import com.example.editoria.R;
import com.example.editoria.model.Editor;
import com.example.editoria.model.Proyecto;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class ValorarFragment extends Fragment {

    View view;
    ImageView icono_editor;
    TextView nombre_editor;
    RatingBar rbEditor, rbProyecto;
    EditText comentario;
    Button publicar;
    private DatabaseReference dRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_valorar, container, false);
        icono_editor = view.findViewById(R.id.profile_image);
        nombre_editor = view.findViewById(R.id.nombreEditor);
        rbEditor = view.findViewById(R.id.ratingBarEditor);
        rbProyecto = view.findViewById(R.id.ratingBarProyecto);
        comentario = view.findViewById(R.id.descripcionP);
        publicar = view.findViewById(R.id.publicar);
        init();

        return view;
    }

    private void init() {

        listeners();


    }

    private void listeners() {

        publicar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Log.i("EJEMPLO", "-> "+rbEditor.getRating()+rbProyecto.getRating()+" "+comentario.getText());
                enviarValoracion();

            }
        });
    }

    private void enviarValoracion() {

        Log.i("EJEMPLO", "ENTRA");

        //valorarEditor
        dRef.child("Editores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("mario12")) {
                    GlobalVariable.editorAvalorar = snapshot.child("mario12").getValue(Editor.class);
                    GlobalVariable.editorAvalorar.setValoraciones(String.valueOf(rbEditor.getRating()));
                    Editor editor = GlobalVariable.editorAvalorar;
                    dRef.child("/Editores").child("mario12").setValue(editor);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        //VALORACION servicio CUANDO ESTÉ HECHO LA RELACION ENTRE EL PROYECTO DE CLIENTE Y EDITOR EN LA BASE DE DATOS
        //dRef.child("/Proyectos/aimar12345/9").child(GlobalVariable.usuario.getUsuario()).setValue();
        //valorarEditor
        dRef.child("Proyectos/mario12/0").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Proyecto proyecto = snapshot.getValue(Proyecto.class);
                proyecto.setValoracion(String.valueOf(rbProyecto.getRating()));
                proyecto.setComentario(comentario.getText().toString());
                Log.i("EJEMPLO","->"+proyecto.toString());
                dRef.child("Proyectos/mario12/0").setValue(proyecto);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}