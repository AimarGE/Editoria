package com.example.editoria.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import com.example.editoria.model.Editor;
import com.example.editoria.model.Proyecto;
import com.example.editoria.model.Usuario;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.util.concurrent.TimeUnit;

import de.hdodenhof.circleimageview.CircleImageView;

public class OpcionesPerfilEditorFragment extends Fragment {

    CircleImageView profile_image;
    View view;
    TextView nombreUsuario, correoElectronico, contrasenya, telefono, pais, mail, twitter, facebook;
    Button guardar;
    SharedPreferences.Editor editorR;
    SharedPreferences preferences;
    private StorageReference storageRef;
    private DatabaseReference dRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/");
    private String urlFoto;

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
        preferences = getActivity().getSharedPreferences("sesion", Context.MODE_PRIVATE);
        editorR = preferences.edit();

        init();

        return view;
    }

    private void init() {
        sethints();
        listeners();
    }

    private void sethints() {

        if (GlobalVariable.usuario.getIcono() != null && !GlobalVariable.usuario.getIcono().equalsIgnoreCase("")){
            Picasso.get().load(GlobalVariable.usuario.getIcono()).into(profile_image);
        }

        nombreUsuario.setHint(GlobalVariable.usuario.getUsuario());
        correoElectronico.setHint(GlobalVariable.usuario.getCorreoE());
        contrasenya.setHint(GlobalVariable.usuario.getPassword());
        telefono.setHint(GlobalVariable.usuario.getTelefono());
        pais.setHint(GlobalVariable.usuario.getPais());
        if (GlobalVariable.editor.getMail() != null){
            mail.setHint(GlobalVariable.editor.getMail());
        }
        if (GlobalVariable.editor.getTwitter() != null){
            twitter.setHint(GlobalVariable.editor.getTwitter());
        }
        if (GlobalVariable.editor.getFacebook() != null){
            facebook.setHint(GlobalVariable.editor.getFacebook());
        }


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

        DatabaseReference dRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/");
        Editor editor = GlobalVariable.editor;
        Usuario usuario = GlobalVariable.usuario;

        String nombreUsuarioaux = GlobalVariable.nombreUsuario;

        if (nombreUsuario != null && (!nombreUsuario.getText().toString().replace(" ","").equalsIgnoreCase("")) ){
            GlobalVariable.usuario.setUsuario(nombreUsuario.getText().toString());
            usuario.setUsuario(nombreUsuario.getText().toString());
        }

        if (correoElectronico != null && (!correoElectronico.getText().toString().replace(" ","").equalsIgnoreCase("")) ){
            GlobalVariable.usuario.setCorreoE(correoElectronico.getText().toString());
            usuario.setCorreoE(correoElectronico.getText().toString());
        }

        if (contrasenya != null && (!contrasenya.getText().toString().replace(" ","").equalsIgnoreCase("")) ){
            GlobalVariable.usuario.setPassword(contrasenya.getText().toString());
            usuario.setPassword(contrasenya.getText().toString());
        }

        if (telefono != null && (!telefono.getText().toString().replace(" ","").equalsIgnoreCase("")) ){
            GlobalVariable.usuario.setTelefono(telefono.getText().toString());
            usuario.setTelefono(telefono.getText().toString());
        }
        if (pais != null && (!pais.getText().toString().replace(" ","").equalsIgnoreCase("")) ){
            GlobalVariable.usuario.setPais(pais.getText().toString());
            usuario.setPais(pais.getText().toString());
        }

        if (mail != null && (!mail.getText().toString().replace(" ","").equalsIgnoreCase("")) ){
            editor.setMail(mail.getText().toString());
        }

        if (twitter != null && (!twitter.getText().toString().replace(" ","").equalsIgnoreCase("")) ){
            editor.setTwitter(twitter.getText().toString());
        }

        if (facebook != null && (!facebook.getText().toString().replace(" ","").equalsIgnoreCase("")) ){
            editor.setFacebook(facebook.getText().toString());
        }

        if (checkPhoto()){
            addUsuarioIcono();
        }

        dRef.child("Usuarios").child(nombreUsuarioaux).setValue(null);
        dRef.child("Editores").child(GlobalVariable.editor.getNombreUsuario()).setValue(null);

        editor.setNombreUsuario(GlobalVariable.usuario.getUsuario());
        dRef.child("Editores").child(GlobalVariable.editor.getNombreUsuario()).setValue(editor);
        dRef.child("Usuarios").child(GlobalVariable.usuario.getUsuario()).setValue(usuario);

        editorR.putString("usuario", GlobalVariable.editor.getNombreUsuario());
        editorR.commit();

        //GlobalVariable.usuario.setIcono();

    }

    private void addUsuarioIcono() {

        storageRef = FirebaseStorage.getInstance().getReference("fotosperfil/");
        storageRef.child(GlobalVariable.usuario.getUsuario()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                urlFoto = uri.toString();
                GlobalVariable.usuario.setIcono(urlFoto);
                dRef.child("Usuarios").child(GlobalVariable.usuario.getUsuario()).setValue(GlobalVariable.usuario);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                Log.i("hola", exception.toString());
                try {
                    TimeUnit.MILLISECONDS.sleep(300);
                    addUsuarioIcono();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean checkPhoto() {
        if (profile_image != null) {
            storageRef = FirebaseStorage.getInstance().getReference("fotosperfil/");
            profile_image.setDrawingCacheEnabled(true);
            profile_image.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable) profile_image.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = storageRef.child(GlobalVariable.usuario.getUsuario()).putBytes(data);
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