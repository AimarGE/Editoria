package com.example.editoria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);

        getSupportActionBar().hide();
    }

    public void registerEditor(View view) {

        Intent intent = new Intent(this, RegisterEditor.class);
        startActivity(intent);
    }

    public void registerCliente(View view) {

        Intent intent = new Intent(this, RegisterCliente.class);
        startActivity(intent);
    }

    public void volver(View view) {

        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}