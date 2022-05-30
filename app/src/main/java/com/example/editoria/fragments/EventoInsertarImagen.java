package com.example.editoria.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
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
import com.example.editoria.model.Proyecto;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class EventoInsertarImagen extends Fragment {

    View view;
    ImageView menu;
    ImageView imagen;
    List<ListElementRanking> elements;
    AlertDialog.Builder builder;
    CartaInsertarImagenEvento cartaInsertarImagenEvento;
    Button seleccionarArchivo, confirmar;
    private StorageReference storageRef;
    private String urlFoto;
    private DatabaseReference dRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/");

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
        imagen = view.findViewById(R.id.insertImagenEvent);
        storageRef = FirebaseStorage.getInstance().getReference("evento/");

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
            if(checkPhoto()){
                actualizarUsuario();
            }
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

    private void actualizarUsuario(){
        storageRef = FirebaseStorage.getInstance().getReference("evento/");
        storageRef.child(GlobalVariable.usuario.getUsuario()).getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                urlFoto = uri.toString();
                GlobalVariable.usuario.setFotoEvento(urlFoto);
                dRef.child("Usuarios").child(GlobalVariable.usuario.getUsuario()).setValue(GlobalVariable.usuario);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                try {
                    TimeUnit.MILLISECONDS.sleep(500);
                    actualizarUsuario();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
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
        elements.add(new ListElement("icono", GlobalVariable.nombreUsuario,"", "","https://uning.es/wp-content/uploads/2016/08/ef3-placeholder-image.jpg", "0",""));

        cartaInsertarImagenEvento = new CartaInsertarImagenEvento(elements, view.getContext(), new CartaInsertarImagenEvento.OnItemClickListener() {
            @Override
            public void onItemClick(ListElement item) {

            }
        });

        cartaInsertarImagenEvento.getImageView();
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

    private boolean checkPhoto() {
        if (imagen != null) {
            storageRef = FirebaseStorage.getInstance().getReference("evento/");
            imagen.setDrawingCacheEnabled(true);
            imagen.buildDrawingCache();
            Bitmap bitmap = ((BitmapDrawable) imagen.getDrawable()).getBitmap();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
            byte[] data = baos.toByteArray();
            UploadTask uploadTask = storageRef.child(GlobalVariable.usuario.getUsuario()).putBytes(data);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    Log.i("imagenF", exception.getMessage());
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Log.i("imagenF", "subida correcta");
                }
            });
            return true;
        }
        return false;
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