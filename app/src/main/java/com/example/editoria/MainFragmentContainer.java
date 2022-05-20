package com.example.editoria;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.etebarian.meowbottomnavigation.MeowBottomNavigation;
import com.example.editoria.fragments.ChatFragment;
import com.example.editoria.fragments.EventoFragment;
import com.example.editoria.fragments.HomeFragment;
import com.example.editoria.fragments.MenuProyectoFragment;
import com.example.editoria.fragments.MiPerfilEditorFragment;
import com.example.editoria.fragments.PerfilFragment;
import com.example.editoria.fragments.ProyectoInformacionFragment;
import com.example.editoria.fragments.ProyectosFragment;

public class MainFragmentContainer extends AppCompatActivity {

    //Button bFiltro;
    public static MeowBottomNavigation bottomNavigation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_fragment_container);

        getSupportActionBar().hide();

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
                        fragment = new ProyectoInformacionFragment();
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
                        fragment = new MenuProyectoFragment();
                        break;

                    case 5:
                        borrarPilaFragments();
                        fragment = new MiPerfilEditorFragment();
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
                        fragment = new HomeFragment();
                        break;
                    case 2:
                        fragment = new ChatFragment();
                        break;
                    case 3:
                        fragment = new EventoFragment();
                        break;
                    case 4:
                        fragment = new MenuProyectoFragment();
                        break;
                    case 5:
                        fragment = new MiPerfilEditorFragment();
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
        for (int i = 0; i < count; i++) { getSupportFragmentManager().popBackStack(); }

    }

}