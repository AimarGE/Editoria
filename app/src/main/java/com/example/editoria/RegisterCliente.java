package com.example.editoria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterCliente extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registercliente);

        getSupportActionBar().hide();
    }


    public void register(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}