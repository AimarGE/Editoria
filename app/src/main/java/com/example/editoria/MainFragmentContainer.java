package com.example.editoria;

import android.Manifest;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.editoria.fragments.ChatFragment;
import com.example.editoria.fragments.EventoFragment;
import com.example.editoria.fragments.HomeFragment;
import com.example.editoria.fragments.MenuProyectoFragmentCliente;
import com.example.editoria.fragments.MenuProyectoFragmentEditor;
import com.example.editoria.fragments.MiPerfilClienteFragment;
import com.example.editoria.fragments.MiPerfilEditorFragment;
import com.example.editoria.fragments.ValorarFragment;

public class MainFragmentContainer extends AppCompatActivity {

    //Button bFiltro;
    public static MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment_container);

        getSupportActionBar().hide();

        ActivityCompat.requestPermissions(MainFragmentContainer.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
        ActivityCompat.requestPermissions(MainFragmentContainer.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, 1);



        bottomNavigation = findViewById(R.id.bottom_navigation);

        bottomNavigation.add(new MeowBottomNavigation.Model(1, R.drawable.ic_home));
        bottomNavigation.add(new MeowBottomNavigation.Model(2, R.drawable.ic_chat));
        bottomNavigation.add(new MeowBottomNavigation.Model(3, R.drawable.ic_evento));
        bottomNavigation.add(new MeowBottomNavigation.Model(4, R.drawable.ic_proyectos));
        bottomNavigation.add(new MeowBottomNavigation.Model(5, R.drawable.ic_profile));

        loadFragment(new HomeFragment());


        bottomNavigation.setOnShowListener(new MeowBottomNavigation.ShowListener() {
            @Override
            public void onShowItem(MeowBottomNavigation.Model item) {
            }
        });

        bottomNavigation.setCount(2, "10");
        bottomNavigation.show(1, true);

        bottomNavigation.setOnClickMenuListener(new MeowBottomNavigation.ClickListener() {
            @Override
            public void onClickItem(MeowBottomNavigation.Model item) {
                //Toast.makeText(getApplicationContext(), "Has clicado"+ item.getId(), Toast.LENGTH_SHORT).show();
                Fragment fragment = null;

                switch (item.getId()){
                    case 1:
                        borrarPilaFragments();
                        fragment = new HomeFragment();
                        break;

                    case 2:
                        borrarPilaFragments();
                        fragment = new ChatFragment();
                        break;

                    case 3:
                        borrarPilaFragments();
                        fragment = new EventoFragment();
                        break;

                    case 4:
                        borrarPilaFragments();
                        if(GlobalVariable.usuario.getClase().equals("Editor")){
                            fragment = new MenuProyectoFragmentEditor();
                        }else{
                            fragment = new MenuProyectoFragmentCliente();
                        }
                        break;

                    case 5:
                        borrarPilaFragments();
                        if(GlobalVariable.usuario.getClase().equals("Editor")){
                            fragment = new MiPerfilEditorFragment();
                        }else{
                            fragment = new MiPerfilClienteFragment();
                        }
                        break;

                }
                loadFragment(fragment);
            }
        });

        bottomNavigation.setOnReselectListener(new MeowBottomNavigation.ReselectListener() {
            @Override
            public void onReselectItem(MeowBottomNavigation.Model item) {//PROBLEMAS AL RECARGAR?

                Fragment fragment = null;

                switch (item.getId()){
                    case 1:
                        borrarPilaFragments();
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        borrarPilaFragments();
                        fragment = new ChatFragment();
                        break;
                    case 3:
                        borrarPilaFragments();
                        fragment = new EventoFragment();
                        break;
                    case 4:
                        borrarPilaFragments();
                        if(GlobalVariable.usuario.getClase().equals("Editor")){
                            fragment = new MenuProyectoFragmentEditor();
                        }else{
                            fragment = new MenuProyectoFragmentCliente();
                        }
                        break;
                    case 5:
                        borrarPilaFragments();
                        if(GlobalVariable.usuario.getClase().equals("Editor")){
                            fragment = new MiPerfilEditorFragment();
                        }else{
                            fragment = new MiPerfilClienteFragment();
                        }
                        break;
                }
                loadFragment(fragment);

                //Toast.makeText(getApplicationContext(), "Has reseleccionado"+ item.getId(), Toast.LENGTH_SHORT).show();
            }
        });

        /*bFiltro = (Button) findViewById(R.id.filtro);
        bFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), PopFiltro.class);
                startActivity(i);
            }
        });*/
    }

    private void loadFragment(Fragment fragment) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainFrame,fragment)
                .commit();
    }

    private void borrarPilaFragments(){

        int count = getSupportFragmentManager().getBackStackEntryCount();
        for (int i = 0; i < count; i++) { getSupportFragmentManager().popBackStack();
            }

    }

}