package com.example.editoria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    Button bFiltro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        getSupportActionBar().hide();

        Log.i("PROBANDO", "AE");

        bFiltro = (Button) findViewById(R.id.filtro);
        bFiltro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("PROBANDO", "asdasd");
                Intent i = new Intent(getApplicationContext(), PopFiltro.class);
                startActivity(i);
            }
        });
    }



}
