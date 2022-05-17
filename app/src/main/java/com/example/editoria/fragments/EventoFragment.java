package com.example.editoria.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.editoria.R;
import com.squareup.picasso.Picasso;


public class EventoFragment extends Fragment {

    View view;
    ImageView imagenEvento;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_evento, container, false);
        imagenEvento = view.findViewById(R.id.imagenEvento);

        Picasso.get().load("https://upload.wikimedia.org/wikipedia/commons/f/ff/Anas_platyrhynchos_qtl1.jpg").into(imagenEvento);

        return view;
    }
}