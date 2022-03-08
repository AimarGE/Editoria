package com.example.editoria;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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


    public void register(View view) {



        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}