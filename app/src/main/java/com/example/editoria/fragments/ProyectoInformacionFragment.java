package com.example.editoria.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.model.ListAdapter;
import com.example.editoria.model.ListElement;
import com.example.editoria.model.Proyecto;

import java.util.ArrayList;
import java.util.List;

public class ProyectoInformacionFragment extends Fragment {

    View view;
    List<Proyecto> proyectos;
    ArrayList<ListElement> elements;
    TextView basico, estandard, premium, descripcionPaquete;
    Button botonContratar;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_proyecto_informacion, container, false);
        basico = view.findViewById(R.id.paqueteBasico);
        estandard = view.findViewById(R.id.paqueteEstandard);
        premium = view.findViewById(R.id.paquetePremium);
        descripcionPaquete = view.findViewById(R.id.descripcionPaquete);
        botonContratar = view.findViewById(R.id.botonContratar);

        init();

        return view;
    }

    private void init() {

        mostrarProyecto();
        listeners();

    }

    private void listeners() {

        basico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                premium.setTextColor(Color.parseColor("#808080"));
                estandard.setTextColor(Color.parseColor("#808080"));
                basico.setTextColor(Color.parseColor("#000000"));

                botonContratar.setText("Contratar (20€)");
                descripcionPaquete.setText("Doña Uzeada de Ribera Maldonado de Bracamonte y Anaya era baja, rechoncha, abigotada. Ya no existia razon para llamar talle al suyo. Sus colores vivos, sanos, podian mas que el albayalde y el soliman del afeite, con que se blanqueaba por simular melancolias. Gastaba dos parches oscuros, adheridos a las sienes y que fingian medicamentos. Tenia los ojitos ratoniles, maliciosos. Sabia dilatarlos duramente o desmayarlos con recato o levantarlos con disimulo. Caminaba contoneando las imposibles caderas y era dificil, al verla, no asociar su estampa achaparrada con la de ciertos palmipedos domesticos. Sortijas celestes y azules le ahorcaban las falanges");
            }
        });

        estandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basico.setTextColor(Color.parseColor("#808080"));
                premium.setTextColor(Color.parseColor("#808080"));
                estandard.setTextColor(Color.parseColor("#000000"));

                botonContratar.setText("Contratar (50€)");
                descripcionPaquete.setText("Los flamencos son aves gregarias altamente especializadas, que habitan sistemas salinos de donde obtienen su alimento (compuesto generalmente de algas microscópicas e invertebrados) y materiales para desarrollar sus hábitos reproductivos.*");
            }
        });

        premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basico.setTextColor(Color.parseColor("#808080"));
                estandard.setTextColor(Color.parseColor("#808080"));
                premium.setTextColor(Color.parseColor("#000000"));

                botonContratar.setText("Contratar (75€)");
                descripcionPaquete.setText("Para ingresar al cajero pase la tarjeta por la ranura que se encuentra junto a la puerta vidriada del Banco en la posición señalada en la imagen.\n" +
                        "Aguarde a que se encienda la luz y empuje la puerta.\n" +
                        "Inserte la tarjeta en la ranura señalada, en la posicion correcta (observar ilustracion)\n" +
                        "Ingrese su codigo de seguridad o pin, luego de que el mismo sea solicitado en la pantalla. Luego oprima el boton confirmar.\n" +
                        "Seleccione la operacion a realizar.");
            }
        });

        botonContratar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainFragmentContainer.bottomNavigation.show(1, true);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.mainFrame, new DatosDelPedidoFragment()).addToBackStack("tag");
                ft.commit();
            }
        });

    }

    private void mostrarProyecto() {

        elements = new ArrayList<>();

        elements.add(new ListElement("icono", "Mario"));

        ListAdapter listAdapter = new ListAdapter(elements, view.getContext());
        RecyclerView recyclerView = view.findViewById(R.id.cvProyectoInfo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }
}