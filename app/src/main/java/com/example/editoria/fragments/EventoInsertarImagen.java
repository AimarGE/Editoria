package com.example.editoria.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.editoria.GlobalVariable;
import com.example.editoria.MainFragmentContainer;
import com.example.editoria.R;
import com.example.editoria.model.CartaInsertarImagenEvento;
import com.example.editoria.model.CartaRanking;
import com.example.editoria.model.CartaRecursosClientes;
import com.example.editoria.model.ListElement;
import com.example.editoria.model.ListElementRanking;

import java.util.ArrayList;
import java.util.List;


public class EventoInsertarImagen extends Fragment {

    View view;
    ImageView menu;
    List<ListElementRanking> elements;
    AlertDialog.Builder builder;
    CartaInsertarImagenEvento cartaInsertarImagenEvento;
    Button seleccionarArchivo, confirmar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_evento_insertar_imagen, container, false);
        menu = view.findViewById(R.id.menu_ranking_evento);
        builder = new AlertDialog.Builder(view.getContext());
        seleccionarArchivo = view.findViewById(R.id.seleccionarArchivo);
        confirmar = view.findViewById(R.id.confirmar);

        init();

        return view;
    }

    private void init() {

        insertarImagenCard();

        listeners();

    }

    private void listeners() {

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //añadir imagen en el proyecto de firebase
                getFragmentManager().popBackStackImmediate();
            }
        });

        seleccionarArchivo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarImagen();
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

    private void cargarImagen() {

        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);

    }

    private void insertarImagenCard() {
        ArrayList elements = new ArrayList<>();

        //CAMBIAR
        elements.add(new ListElement("https://img.unocero.com/2020/07/Super-Mario-Bros-verdadera-nacionalidad-1024x576.jpg", GlobalVariable.nombreUsuario,"", "","https://uning.es/wp-content/uploads/2016/08/ef3-placeholder-image.jpg", "0",""));

        cartaInsertarImagenEvento = new CartaInsertarImagenEvento(elements, view.getContext(), new CartaInsertarImagenEvento.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {

            }
        });
        RecyclerView recyclerView = view.findViewById(R.id.insertarImagen);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(cartaInsertarImagenEvento);
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

        MainFragmentContainer.bottomNavigation.show(3, true);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(R.id.mainFrame, new EventoRanking()).addToBackStack("tag");
        ft.commit();

    }

    private void paginaInsertarImagen() {
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri path = data.getData();
            cartaInsertarImagenEvento.getImageView().setImageURI(path);//SET IMAGE DEL CARDVIEW DEL
        }
    }


}