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
import com.example.editoria.model.Paquete;
import com.example.editoria.model.Proyecto;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ProyectoInformacionFragment extends Fragment {
    View view;
    ArrayList<ListElement> elements;
    ArrayList<ListElementComentario> elementsComentario;
    TextView basico, estandard, premium, descripcionPaquete, descripcionServicio;
    Button botonContratar;
    ListElement listElement;
    String nombre, descripcion, titulo;
    private Proyecto p;
    private Proyecto proyecto;
    private Paquete Pbasico, Pavanzado, Ppremium;
    private DatabaseReference dRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/");

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
        p = new Proyecto();
        obtenerPaquete();
        if (GlobalVariable.listElementServicios != null ){

            listElement = (ListElement) GlobalVariable.listElementServicios;
            nombre = listElement.getName();
            descripcion = listElement.getDescripcion();
            titulo = listElement.getTitulo();
            init();
        }

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
                if(Pbasico != null){
                    botonContratar.setText("Contratar (" + Pbasico.getPrecio() + "€)");
                    descripcionPaquete.setText(Pbasico.getDescripcion());
                }
                else{
                    botonContratar.setText("Paquete no disponible");
                    descripcionPaquete.setText("Paquete no disponible");
                }

            }
        });

        estandard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basico.setTextColor(Color.parseColor("#808080"));
                premium.setTextColor(Color.parseColor("#808080"));
                estandard.setTextColor(Color.parseColor("#000000"));

                if(Pavanzado != null){
                    botonContratar.setText("Contratar (" +Pavanzado.getPrecio()+ "€)");
                    descripcionPaquete.setText(Pavanzado.getDescripcion());
                }else{
                    botonContratar.setText("Paquete no disponible");
                    descripcionPaquete.setText("Paquete no disponible");
                }
            }
        });

        premium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                basico.setTextColor(Color.parseColor("#808080"));
                estandard.setTextColor(Color.parseColor("#808080"));
                premium.setTextColor(Color.parseColor("#000000"));

                if(Ppremium != null){
                    botonContratar.setText("Contratar (" +Ppremium.getPrecio()+ "€)");
                    descripcionPaquete.setText(Ppremium.getDescripcion());
                }else{
                    botonContratar.setText("Paquete no disponible");
                    descripcionPaquete.setText("Paquete no disponible");
                }
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

        elements.add(new ListElement("icono", nombre, "", titulo, "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif","30.02", ""));

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

        obtenerPaquete();
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

    private void obtenerPaquete(){

        dRef.child("/Proyectos/"+GlobalVariable.listElementServicios.getName()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot postSnapshot: snapshot.getChildren()) {

                    Proyecto p = snapshot.child(postSnapshot.getKey()).getValue(Proyecto.class);
                    if (p.getNombre().equals(GlobalVariable.listElementServicios.getTitulo())){
                        proyecto = postSnapshot.getValue(Proyecto.class);
                        Log.i("PAQUES", proyecto.toString());
                    }

                }
                if(proyecto.getPaquetes().get(0).getTipo().equals("Basico")){
                    botonContratar.setText("Contratar (" + proyecto.getPaquetes().get(0).getPrecio() + "€)");
                    descripcionPaquete.setText(proyecto.getPaquetes().get(0).getDescripcion());
                }else{
                    botonContratar.setText("Paquete no disponible");
                    descripcionPaquete.setText("Paquete no disponible");
                }
                getPaquetes();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void getPaquetes(){
        ArrayList<Paquete> paquetes = proyecto.getPaquetes();
        for(int i=0; i < paquetes.size(); i++){
            if(paquetes.get(i).getTipo().equals("Basico")){
                Pbasico = new Paquete(paquetes.get(i).getTipo(), paquetes.get(i).getPrecio(), paquetes.get(i).getDescripcion());
            }
            else if(paquetes.get(i).getTipo().equals("Avanzado")){
                Pavanzado = new Paquete(paquetes.get(i).getTipo(), paquetes.get(i).getPrecio(), paquetes.get(i).getDescripcion());
            }
            else if(paquetes.get(i).getTipo().equals("Premium")){
                Ppremium = new Paquete(paquetes.get(i).getTipo(), paquetes.get(i).getPrecio(), paquetes.get(i).getDescripcion());
            }
        }
    }

    private void mostrarComentarios() {

        //BUCLE PARA BUSCAR LOS COMENTARIOS DEL PROYECTO
        elementsComentario = new ArrayList<>();
        elementsComentario.add(new ListElementComentario(GlobalVariable.listElementServicios.getIcon(),p.getNombreUsuario(), p.getComentario(), p.getValoracion()));

        CartaComentario listAdapter = new CartaComentario(elementsComentario, view.getContext());
        RecyclerView recyclerView = view.findViewById(R.id.cvComentarios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }
}