package com.example.editoria.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.editoria.GlobalVariable;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.model.CartaParticipantes;
import com.example.editoria.model.ListAdapter;
import com.example.editoria.model.ListElement;

import java.util.ArrayList;
import java.util.List;


public class EventoParticipantes extends Fragment {

    View view;
    ImageView menu, lupa;
    List<ListElement> elements;
    EditText buscador;
    AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_evento_participantes, container, false);
        menu = view.findViewById(R.id.menu_participantes);
        lupa = view.findViewById(R.id.lupa);
        buscador = view.findViewById(R.id.editTextBusqueda);
        builder = new AlertDialog.Builder(view.getContext());



        init();



        return view;
    }

    private void init() {


        mostrarRankingParticipantes();

        listeners();

    }

    private void listeners() {

        buscador.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus){
                    //root.setBackgroundColor(Color.GREEN);
                }else{
                    //root.setBackgroundColor(Color.RED);
                }
            }
        });

        //LISTENER LUPA
        lupa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buscador.setVisibility(view.VISIBLE);

                if (buscador.requestFocus()){
                    showKeyboard();
                }
            }
        });


        //LISTENER MENU
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.i("EJEMPLO", "ENTRA");

                PopupMenu popupMenu = new PopupMenu(getActivity().getApplicationContext(), menu);

                popupMenu.getMenuInflater().inflate(R.menu.menu_evento, popupMenu.getMenu());

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        switch (menuItem.getItemId()){
                            case R.id.rankingEvento:
                                paginaRanking();
                                //root.setBackgroundColor(Color.RED);
                                return true;
                            case R.id.participantesEvento:
                                paginaParticipantes();
                                //root.setBackgroundColor(Color.GREEN);
                                return true;
                            case R.id.abandonarEvento:
                                dialogAbandonarEvento();
                                //root.setBackgroundColor(Color.YELLOW);
                                return true;
                            case R.id.insertarImagen:
                                paginaInsertarImagen();
                                //root.setBackgroundColor(Color.YELLOW);
                                return true;
                        }

                        return false;
                    }
                });

                popupMenu.show();
            }
        });

    }

    private void mostrarRankingParticipantes() {

        elements = new ArrayList<>();

        elements.add(new ListElement("icono", "Mario","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "Ejemplo2","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "José","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "Maria","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "Rodrigo","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "Mario","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "Ejemplo2","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "José","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "Maria","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "Rodrigo","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "Mario","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "Ejemplo2","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "José","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "Maria","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        elements.add(new ListElement("icono", "Rodrigo","", "", "https://www.poresto.net/u/fotografias/m/2021/5/21/f608x342-82231_111954_14.gif",30.02));
        CartaParticipantes listAdapter = new CartaParticipantes(elements, view.getContext(), new CartaParticipantes.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {

                //COMPROBAR QUE SEA EDITOR O CLIENTE DESDE FIREBASE

                MainFragmentContainer.bottomNavigation.show(3, true);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                Bundle bundle = new Bundle();
                bundle.putSerializable("item", item);
                GlobalVariable.bundleEditor = bundle;
                ft.replace(R.id.mainFrame, new FragmentPerfilEditor()).addToBackStack("tag");
                ft.commit();
            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.listaParticipanetesEventos);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(listAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }


    public void showKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
    }

    public void closeKeyboard(){
        InputMethodManager inputMethodManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    private void dialogAbandonarEvento() {

        builder.setTitle("¿Seguro que quieres abandonar el evento?")
                .setCancelable(true)
                .setPositiveButton("Si", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        abandonarEvento();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    private void abandonarEvento() {
        MainFragmentContainer.bottomNavigation.show(3, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new EventoFragment());
        ft.commit();
    }


    private void paginaParticipantes() {

    }

    private void paginaRanking() {

        MainFragmentContainer.bottomNavigation.show(3, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new EventoRanking()).addToBackStack("tag");
        ft.commit();

    }

    private void paginaInsertarImagen() {
        MainFragmentContainer.bottomNavigation.show(3, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new EventoInsertarImagen()).addToBackStack("tag");
        ft.commit();
    }
}