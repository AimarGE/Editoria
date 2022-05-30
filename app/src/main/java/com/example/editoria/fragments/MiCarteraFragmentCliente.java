package com.example.editoria.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.editoria.GlobalVariable;
import com.example.editoria.R;
import com.example.editoria.model.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class MiCarteraFragmentCliente extends Fragment {

    View view;
    Button mas100, menos100, confirmar;
    TextView dinero, dineroActual;
    String dineroS;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mi_cartera, container, false);
        mas100 = view.findViewById(R.id.mas100);
        menos100 = view.findViewById(R.id.menos100);
        dinero = view.findViewById(R.id.dinero_modificado);
        confirmar = view.findViewById(R.id.confirmar);
        dineroActual = view.findViewById(R.id.dinero_actual);
        init();

        return view;
    }

    private void init() {

        dineroS = GlobalVariable.usuario.getDinero();
        dineroActual.setText(GlobalVariable.usuario.getDinero()+"€");
        dinero.setText(GlobalVariable.usuario.getDinero()+"€");
        listeners();

    }

    private void listeners() {

        mas100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dineroS = dinero.getText().toString().replace("€", "");

                Double dineroD = Double.valueOf(dineroS)+100.00;

                dineroS = String.format("%.2f", dineroD).replace(",",".");

                dinero.setText(dineroS+"€");


            }
        });

        menos100.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                dineroS = dinero.getText().toString().replace("€", "");

                Double dineroD = Double.valueOf(dineroS)-100.00;

                dineroS = String.format("%.2f", dineroD).replace(",",".");

                dinero.setText(dineroS+"€");

            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                modificarDinero();
                getFragmentManager().popBackStackImmediate();

            }
        });
    }

    private void modificarDinero() {

        DatabaseReference dRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/");

        Usuario usuario = GlobalVariable.usuario;
        usuario.setDinero(dineroS);

        dRef.child("Usuarios").child(GlobalVariable.nombreUsuario).setValue(usuario);

        /*DatabaseReference dRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/Usuarios/");



        dRef.child("asdasd").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                Log.i("EJEMPLO", "-> "+task.getResult().toString());
            }
        });*/

        //Log.i("EJEMPLO", "-> "+dRef.child("asdasd").);



    }
}