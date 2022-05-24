package com.example.editoria.fragments;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.editoria.GlobalVariable;
import com.example.editoria.Login;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.model.ListAdapter;
import com.example.editoria.model.ListElement;
import com.example.editoria.model.Proyecto;
import com.example.editoria.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    Button filtro;
    List<ListElement> elements;
    View view;
    private DatabaseReference dRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/");
    List<Proyecto> proyectos;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home_v2, container, false);
        filtro = view.findViewById(R.id.filtro);
        proyectos = new ArrayList<>();
        getAllProyectos();
        getUsuario();

        filtro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*crearDialogoFiltro();*/
            }
        });
        return view;
    }

    private void getUsuario(){
       /* dRef.child("Usuarios").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.hasChild(GlobalVariable.nombreUsuario)) {
                    GlobalVariable.usuario = snapshot.child(GlobalVariable.nombreUsuario).getValue(Usuario.class);
                    if(snapshot.child(GlobalVariable).child("clase").getValue(String.class).equals("Editor")){
                        getEditor();
                    }else{
                        getCliente();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/
    }

    private void getEditor(){

    }

    private void getAllProyectos(){
        dRef.child("Proyectos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                proyectos.clear();
                for (DataSnapshot postSnapshot: snapshot.getChildren()) {
                    Proyecto proyecto = postSnapshot.getValue(Proyecto.class);
                    proyectos.add(proyecto);
                }
                mostrarProyectos(proyectos);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void mostrarProyectos(List<Proyecto> proyectos){
        elements = new ArrayList<>();
        for(int i=0; i < proyectos.size(); i++){
            Log.i("proyectos", proyectos.get(i).toString());
            elements.add(new ListElement("icono", proyectos.get(i).getNombreUsuario(), proyectos.get(i).getDescripcion(), proyectos.get(i).getNombre(), proyectos.get(i).getFoto(), 50.02));
        }

        ListAdapter listAdapter = new ListAdapter(elements, view.getContext(), new ListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {
                proyectoSeleccionado(item);
            }
        });

        RecyclerView recyclerView = view.findViewById(R.id.listaCVEditoresRecomendados);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        //recyclerView.setOverScrollMode(view.OVER_SCROLL_NEVER);
    }

    private void proyectoSeleccionado(ListElement item) {

        MainFragmentContainer.bottomNavigation.show(1, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();

        Bundle bundle = new Bundle();

        bundle.putSerializable("item", item);
        GlobalVariable.bundleEditor = bundle;


        //getParentFragmentManager().setFragmentResult("key", bundle);

        ft.replace(R.id.mainFrame, new ProyectoInformacionFragment()).addToBackStack("tag");
        ft.commit();


    }

    /*private void crearDialogoFiltro() {

        AlertDialog.Builder builder = new AlertDialog.Builder();
        builder.setTitle("Filtro");
        builder.setMultiChoiceItems(https://www.youtube.com/watch?v=1vIRg6_a_fc&ab_channel=TechnicalSkillz);

        builder.create();
        builder.show();

    }*/
}