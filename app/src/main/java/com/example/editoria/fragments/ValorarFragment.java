package com.example.editoria.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.editoria.GlobalVariable;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.model.Editor;
import com.example.editoria.model.Proyecto;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

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

        Picasso.get().load("https://cloudfront-us-east-1.images.arcpublishing.com/infobae/MN2XNZY4XJH27KAROURFGYWLYY.jpg").into(icono_editor);
        nombre_editor.setText("MarioWopi");
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


                //enviarValoracion();
                MainFragmentContainer.bottomNavigation.show(4, true);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, new MenuProyectoFragmentCliente());
                ft.commit();

            }
        });
    }

    private void enviarValoracion() {

        Log.i("EJEMPLO", "ENTRA");

        //valorarEditor
        dRef.child("Editores").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild("MarioWopi")) {
                    GlobalVariable.editorAvalorar = snapshot.child("MarioWopi").getValue(Editor.class);
                    GlobalVariable.editorAvalorar.setValoraciones(String.valueOf(rbEditor.getRating()));
                    Editor editor = GlobalVariable.editorAvalorar;
                    dRef.child("/Editores").child("MarioWopi").setValue(editor);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });


        //VALORACION servicio CUANDO ESTÉ HECHO LA RELACION ENTRE EL PROYECTO DE CLIENTE Y EDITOR EN LA BASE DE DATOS
        //dRef.child("/Proyectos/aimar12345/9").child(GlobalVariable.usuario.getUsuario()).setValue();
        //valorarEditor
        dRef.child("Editores/MarioWopi/0").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Proyecto proyecto = snapshot.getValue(Proyecto.class);
                proyecto.setValoracion(String.valueOf(rbProyecto.getRating()));
                proyecto.setComentario(comentario.getText().toString());
                Log.i("EJEMPLO","->"+proyecto.toString());
                dRef.child("Proyectos/MarioWopi/0").setValue(proyecto);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}