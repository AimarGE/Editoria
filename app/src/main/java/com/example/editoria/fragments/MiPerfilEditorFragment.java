package com.example.editoria.fragments;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.editoria.GlobalVariable;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.fragments.OpcionesFragment;
import com.squareup.picasso.Picasso;

public class MiPerfilEditorFragment extends Fragment {

    ImageView opciones, profile_image;
    Button contactar;
    View view;
    private RatingBar ratingBar;
    TextView nombre, from;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_mi_perfil_editor, container, false);
        ratingBar = (RatingBar) view.findViewById(R.id.ratingBar);
        contactar = view.findViewById(R.id.contactar);
        opciones = view.findViewById(R.id.opciones);
        nombre = view.findViewById(R.id.nombreMiPEditor);
        from = view.findViewById(R.id.from);
        profile_image = view.findViewById(R.id.profile_image);
        nombre.setText(GlobalVariable.usuario.getUsuario());
        Log.i("EJEMPLO", "asdasd "+GlobalVariable.usuario.getPais());

        if (GlobalVariable.usuario.getPais() != null && !GlobalVariable.usuario.getPais().toString().equalsIgnoreCase("")){
            from.setText(GlobalVariable.usuario.getPais());
        }else{
            from.setText("----");
        }

        if (GlobalVariable.usuario.getIcono() != null && !GlobalVariable.usuario.getIcono().equalsIgnoreCase("")){
            Picasso.get().load(GlobalVariable.usuario.getIcono()).into(profile_image);
        }



        //RatingBar (0.5 - 5.0)
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Log.i("aaa", ""+rating);
            }
        });

        //Botón de contacto
        contactar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirContactos();
            }
        });


        //abrir ajustes
        opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirOpciones();
            }
        });


        return view;
    }


    private void abrirOpciones() {

        MainFragmentContainer.bottomNavigation.show(5, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new OpcionesFragment()).addToBackStack("tag");
        ft.commit();

    }

    private void abrirContactos() {
        Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.contactos_dialog);

        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        RelativeLayout contacto_mail = dialog.findViewById(R.id.contacto_mail);
        RelativeLayout contacto_twitter = dialog.findViewById(R.id.contacto_twitter);
        RelativeLayout contacto_facebook = dialog.findViewById(R.id.contacto_facebook);
        TextView tv_mail = dialog.findViewById(R.id.tv_mail);
        TextView tv_twitter = dialog.findViewById(R.id.tv_twitter);
        TextView tv_facebook = dialog.findViewById(R.id.tv_facebook);
        tv_twitter.setText(GlobalVariable.editor.getTwitter());
        tv_facebook.setText(GlobalVariable.editor.getFacebook());
        tv_mail.setText(GlobalVariable.editor.getMail());

        contacto_mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent email_redirect =new Intent(Intent.ACTION_SEND);
                String[] mail = {tv_mail.getText().toString()};
                email_redirect.putExtra(Intent.EXTRA_EMAIL, mail);
                email_redirect.setType("message/rfc822");
                startActivity(Intent.createChooser(email_redirect, "Selecciona por donde quieres enviar el correo."));


            }
        });

        contacto_twitter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent twitter_redirect =new Intent("android.intent.action.VIEW", Uri.parse("https://twitter.com/"+tv_twitter.getText().toString()));
                startActivity(twitter_redirect);


            }
        });

        contacto_facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent facebook_redirect =new Intent("android.intent.action.VIEW", Uri.parse("https://facebook.com/"+tv_facebook.getText().toString()));
                startActivity(facebook_redirect);
            }
        });


        dialog.show();
    }

}