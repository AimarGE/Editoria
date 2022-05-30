package com.example.editoria.fragments;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.editoria.GlobalVariable;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.model.CartaComentario;
import com.example.editoria.model.CartaProyectoInformacion;
import com.example.editoria.model.ListAdapter;
import com.example.editoria.model.ListElement;
import com.example.editoria.model.ListElementComentario;
import com.example.editoria.model.Proyecto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ProyectoInformacionFragment extends Fragment {
    View view;
    ArrayList<ListElement> elements;
    ArrayList<ListElementComentario> elementsComentario;
    TextView basico, estandard, premium, descripcionPaquete, descripcionServicio;
    Button botonContratar;
    ListElement listElement;
    String nombre, descripcion, titulo, precioBasico, descripcionBasico;

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
        descripcionServicio = view.findViewById(R.id.descripcionServicio);

        if (GlobalVariable.bundleEditor != null ){

            listElement = (ListElement) GlobalVariable.bundleEditor.getSerializable("item");
            nombre = listElement.getName();
            descripcion = listElement.getDescripcion();
            titulo = listElement.getTitulo();

            init();

        }


        /*//PARA RECIBIR DATOS ENTRE FRAGMENTS
        getParentFragmentManager().setFragmentResultListener("key", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                listElement = (ListElement) result.getSerializable("item");//PARA RECOGER EL ITEM DE LISTELEMENT CON LA KEY ITEM DEL FRAGMENT ANTERIOR

                nombre = listElement.getName();


            }
        });*/



        return view;
    }

    private void init() {

        descripcionServicio.setText(listElement.getDescripcion());


        mostrarProyecto();
        mostrarComentarios();
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

        elements.add(new ListElement("icono", nombre, "", titulo, "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif","30.02"));

        CartaProyectoInformacion listAdapter = new CartaProyectoInformacion(elements, view.getContext(), new CartaProyectoInformacion.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                    perfilSeleccionado(item);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.cvProyectoInfo);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }

    private void perfilSeleccionado(ListElement item) {

        MainFragmentContainer.bottomNavigation.show(1, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();

        bundle.putSerializable("item", item);

        getParentFragmentManager().setFragmentResult("perfilEditor", bundle);

        ft.replace(R.id.mainFrame, new FragmentPerfilEditor()).addToBackStack("tag");
        ft.commit();

    }

    private void mostrarComentarios() {

        //BUCLE PARA BUSCAR LOS COMENTARIOS DEL PROYECTO
        elementsComentario = new ArrayList<>();
        elementsComentario.add(new ListElementComentario("icono","Mario","Me refiero a que la gente debe ser salvada de sí misma, debe ser protegida de sus propios deseos y f\n" +
                "\n" +
                "Fuente: https://concepto.de/comentario/#ixzz7TqbMcwux"));

        elementsComentario.add(new ListElementComentario("icono","Mario","Los comentarios literarios se distinguen de los análisis o de los comentarios filológicos en que abordan la obra literaria como un universo cerrado en sí mismo, y trabajan únicamente con los elementos que allí se encuentran y con la reverberación que ellos generen en el lector y comentarista. Es decir: se trata de una lectura personal de la obra, que se sustenta en lo leído y por lo tanto es demostrable, tiene fundamentos, no es una opinión o una interpretación enteramente libre. Fuente: https://concepto.de/comentario/#ixzz7TqatdzLw"));
        elementsComentario.add(new ListElementComentario("icono","Mario","Su nombre ya revela sus filiaciones con la palabra griega para la muerte: thanatos, lo cual es conveniente a la hora de explicar su proyecto para el Universo, que es la erradicación de la mitad de los seres vivientes que alberga. Esto lo hace motivado por el deseo, paradójico, de preservar la vida como un todo: para Thanos, somos demasiados los habitantes del Universo y estamos agotando los recursos demasiado aprisa, por lo que deben tomarse medidas drásticas para garantizar que no nos extingamos a nosotros mismos.\n" +
                "\n" +
                "Fuente: https://concepto.de/comentario/#ixzz7Tqb9n6Mf"));

        CartaComentario listAdapter = new CartaComentario(elementsComentario, view.getContext());
        RecyclerView recyclerView = view.findViewById(R.id.cvComentarios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }
}