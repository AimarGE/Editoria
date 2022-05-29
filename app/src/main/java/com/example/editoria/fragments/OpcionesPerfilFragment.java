package com.example.editoria.fragments;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.editoria.GlobalVariable;
import com.example.editoria.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class OpcionesPerfilFragment extends Fragment {

    CircleImageView profile_image;
    View view;
    TextView nombreUsuario, correoElectronico, contrasenya, telefono, pais, mail, twitter, facebook;
    Button guardar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_opciones_perfil, container, false);
        profile_image = view.findViewById(R.id.profile_image);
        nombreUsuario = view.findViewById(R.id.nombreUsuario);
        correoElectronico = view.findViewById(R.id.correoElectronico);
        contrasenya = view.findViewById(R.id.contrasenya);
        telefono = view.findViewById(R.id.numTelefono);
        pais = view.findViewById(R.id.pais);
        mail = view.findViewById(R.id.mail);
        twitter = view.findViewById(R.id.twitter);
        facebook = view.findViewById(R.id.facebook);
        guardar = view.findViewById(R.id.guardar);

        init();

        return view;
    }

    private void init() {
        sethints();
        listeners();
    }

    private void sethints() {

        if (GlobalVariable.usuario.getIcono() != null){
            Picasso.get().load(GlobalVariable.usuario.getIcono()).into(profile_image);
        }

        nombreUsuario.setHint(GlobalVariable.usuario.getUsuario());
        correoElectronico.setHint(GlobalVariable.usuario.getCorreoE());
        contrasenya.setHint(GlobalVariable.usuario.getPassword());
        telefono.setHint(GlobalVariable.usuario.getTelefono());
        pais.setHint(GlobalVariable.usuario.getPais());
        mail.setHint(GlobalVariable.editor.getMail());
        twitter.setHint(GlobalVariable.editor.getTwitter());
        facebook.setHint(GlobalVariable.editor.getFacebook());

    }

    private void listeners() {

        profile_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
            }
        });

        guardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                guardarCambios();
                getFragmentManager().popBackStackImmediate();
            }
        });

    }

    private void guardarCambios() {


        //GlobalVariable.usuario.setIcono();


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
            profile_image.setImageURI(path);
        }
    }

}