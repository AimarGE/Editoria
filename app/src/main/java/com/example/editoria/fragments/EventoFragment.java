package com.example.editoria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.airbnb.lottie.L;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.squareup.picasso.Picasso;


public class EventoFragment extends Fragment {

    View view;
    ImageView imagenEvento;
    Button botonInscribirse;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_evento, container, false);
        imagenEvento = view.findViewById(R.id.imagenEvento);
        botonInscribirse = view.findViewById(R.id.botonInscribirse);


        //IF PARA COMPROBAR QUE SEA UN EDITOR O NO --> REDIRIGE DIRECTAMENTE A EVENTO-PAGINA-PRINCIPAL-FRAGMENT


        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/f/ff/Anas_platyrhynchos_qtl1.jpg").into(imagenEvento);

        init();

        return view;
    }

    private void init() {

        botonInscribirse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentContainer.bottomNavigation.show(3, true);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, new EventoPaginaPrincipalFragment()).addToBackStack("tag");
                ft.commit();
            }
        });

    }
}