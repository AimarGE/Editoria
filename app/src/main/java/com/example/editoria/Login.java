package com.example.editoria;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.editoria.dao.Writer;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {

    private EditText campoNombreUsuario;
    private EditText campoPassword;
    private DatabaseReference dRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/");
    private Writer writer;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        campoNombreUsuario= (EditText) findViewById(R.id.UserLogIn);
        campoPassword= (EditText) findViewById(R.id.contraLogin);
        getSupportActionBar().hide();
        preferences = getSharedPreferences("sesion", Context.MODE_PRIVATE);
        editor = preferences.edit();


    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void login(View _){
        String usuario= campoNombreUsuario.getText().toString();
        String password = campoPassword.getText().toString();
        if(usuario.equals("") || usuario.equals(" ") || password.equals("") || password.equals(" ")) {
            Toast.makeText(this, "No puede haber campos vacíos", Toast.LENGTH_LONG).show();
        }else{
            dRef.child("Usuarios").addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if(snapshot.hasChild(usuario)){

                        final String getPassword = snapshot.child(usuario).child("password").getValue(String.class);

                        if(getPassword.equals(password)){
                            Toast.makeText(Login.this, "Sesión iniciada con éxito", Toast.LENGTH_SHORT).show();
                            addDataToSharedPreferences(usuario, password);
                            startActivity(new Intent(Login.this, MainFragmentContainer.class));
                        }else{
                            Toast.makeText(Login.this, "Wrong password", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(Login.this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void addDataToSharedPreferences(String usuario, String password){
        editor.putBoolean("sesionIniciada",true);
        editor.putString("usuario", usuario);
        editor.putString("password", password);
        GlobalVariable.nombreUsuario=usuario;
        editor.commit();
    }

    public void forgotenPass(View _){
        //Intent intent = new Intent(this,);
        //startActivity(intent);
    }
}