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
import com.example.editoria.model.CartaRanking;
import com.example.editoria.model.ListAdapter;
import com.example.editoria.model.ListElement;
import com.example.editoria.model.ListElementRanking;

import java.util.ArrayList;
import java.util.List;


public class EventoRanking extends Fragment {

    View view;
    ImageView menu, lupa;
    List<ListElementRanking> elements;
    EditText buscador;
    AlertDialog.Builder builder;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_evento_ranking, container, false);
        menu = view.findViewById(R.id.menu_ranking_evento);
        lupa = view.findViewById(R.id.lupa);
        buscador = view.findViewById(R.id.editTextBusqueda);
        builder = new AlertDialog.Builder(view.getContext());



        /*private void borrarPilaFragments(){
            int count = getActivity().getSupportFragmentManager().getBackStackEntryCount();

            for (int i = 0; i < count; i++) { getActivity().getSupportFragmentManager().popBackStack(); }

        }*/

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
        elements.add(new ListElementRanking("https://www.dzoom.org.es/wp-content/uploads/2020/02/portada-foto-perfil-redes-sociales-consejos.jpg", "Maria","https://www.xlsemanal.com/wp-content/uploads/sites/3/2020/09/las-fotos-de-animales-mas-divertidas-del-mundo.jpg", "1", "10"));
        elements.add(new ListElementRanking("https://i.pinimg.com/280x280_RS/8f/c6/b6/8fc6b6364c319b9881b184baf7f8889d.jpg", "Mario","https://www.ngenespanol.com/wp-content/uploads/2018/09/Fotos-Divertidas-del-mundo-animal-8.png", "2",  "5"));
        elements.add(new ListElementRanking("https://img.unocero.com/2022/04/Foto-de-perfil-como-saber-si-es-falsa-1024x576.jpg", "José","https://cdn.businessinsider.es/sites/navi.axelspringer.es/public/styles/bi_570/public/media/image/2019/03/oh-no-you-didnt.jpg?itok=miPom1Ob", "3",  "3"));


        CartaRanking listAdapter = new CartaRanking(elements, view.getContext(), new CartaRanking.OnItemClickListener() {
            @Override
            public void onItemClick(ListElementRanking item) {

                //COMPROBAR QUE SEA EDITOR O CLIENTE DESDE FIREBASE

                MainFragmentContainer.bottomNavigation.show(3, true);
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ListElement le = new ListElement("icono", item.getName(),"descripcion", "titulo", "https://img.freepik.com/vector-gratis/trofeo-oro-placa-ganador-concurso_68708-545.jpg?w=2000", "2","");

                GlobalVariable.listElementServicios = le;
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

        MainFragmentContainer.bottomNavigation.show(3, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new EventoParticipantes()).addToBackStack("tag");
        ft.commit();

    }

    private void paginaRanking() {

    }

    private void paginaInsertarImagen() {
        MainFragmentContainer.bottomNavigation.show(3, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new EventoInsertarImagen()).addToBackStack("tag");
        ft.commit();
    }
}