package com.example.editoria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterCliente extends AppCompatActivity {

    EditText usuario;
    EditText password;
    EditText confirmPassword;
    EditText email;
    EditText telefono;
    EditText fechaNacimiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registercliente);

        getSupportActionBar().hide();
    }


    public void register(View _) {
        usuario = (EditText) findViewById(R.id.usuario);
        password = (EditText) findViewById(R.id.password);
        confirmPassword= (EditText) findViewById(R.id.ConfirmPassword);
        email = (EditText) findViewById(R.id.email);
        telefono = (EditText) findViewById(R.id.telefono);
        fechaNacimiento = (EditText) findViewById(R.id.FechaNacimiento);
        comprobacionDatos();
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    private void comprobacionDatos(){
        comprobarContras();
    }

    private void comprobarContras(){

    }
}