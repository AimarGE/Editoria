package com.example.editoria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.editoria.model.Cliente;
import com.example.editoria.model.Usuario;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterCliente extends AppCompatActivity {

    EditText CampoUsuario, CampoPassword, CampoConfirmPassword, CampoEmail, CampoTelefono, CampoFechaNacimiento;
    String usuario, contra, contraConfirmar, email, telefono, fechaNacimiento;
    FirebaseDatabase Fdatabase;
    DatabaseReference dRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registercliente);
        CampoUsuario = (EditText) findViewById(R.id.usuario);
        CampoPassword = (EditText) findViewById(R.id.password);
        CampoConfirmPassword= (EditText) findViewById(R.id.ConfirmPassword);
        CampoEmail = (EditText) findViewById(R.id.email);
        CampoTelefono = (EditText) findViewById(R.id.telefono);
        CampoFechaNacimiento = (EditText) findViewById(R.id.FechaNacimiento);

        Fdatabase= FirebaseDatabase.getInstance("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/");
        dRef= Fdatabase.getReference();

        getSupportActionBar().hide();
    }


    public void register(View _) {
        usuario = CampoUsuario.getText().toString();
        contra= CampoPassword.getText().toString();
        contraConfirmar= CampoConfirmPassword.getText().toString();
        email = CampoEmail.getText().toString();
        telefono = CampoTelefono.getText().toString();
        fechaNacimiento = CampoFechaNacimiento.getText().toString();
        comprobacionDatos();
    }

    private void comprobacionDatos() {
        Intent intent = new Intent(this, Login.class);
        boolean contrasTamano = comprobarContrasLenght();
        boolean contrasMatch = comprobarContrasMatch();
        boolean telefono = comprobarTelefono();
        boolean email = comprobarEmail();
        boolean usuarioComp = comprobarLenghtUsuario(usuario);
        int contador = 0;
        if (contrasTamano) {
            contador++;
        } else{
            CampoPassword.setText("");
            CampoPassword.setHint("Contraseñas muy cortas");
            CampoConfirmPassword.setText("");
            CampoConfirmPassword.setHint("Contraseñas muy cortas");
        }
        if(contrasMatch){
            contador++;
        } else{
            CampoPassword.setText("");
            CampoPassword.setHint("Las contraseñas no coinciden");
            CampoConfirmPassword.setText("");
            CampoConfirmPassword.setHint("Las contraseñas no coinciden");
        }
        if (telefono) {
            contador++;
        }else{
            CampoTelefono.setText("");
            CampoTelefono.setHint("El tamaño del teléfono no es correcto");
        }
        if (email) {
            contador++;
        }else{
            CampoEmail.setText("");
            CampoEmail.setHint("Formato de email no válido");
        }
        if(usuarioComp){
            contador++;
        }else{
            CampoUsuario.setText("");
            CampoUsuario.setHint("Nombre de usuario corto");
        }
        if(contador == 5){
            addUser();
            startActivity(intent);
        }
    }


    private boolean comprobarContrasLenght(){
        if(contra.length() < 6){
            return false;
        }
        return true;
    }

    private boolean comprobarContrasMatch(){
        if(contra.equals(contraConfirmar)){
            return true;
        }
        return false;
    }

    private boolean comprobarTelefono(){
        if(telefono.length() != 9){
            return false;
        }
        return true;
    }

    private boolean comprobarEmail(){
        if(email.contains("@")){
            Log.i("EMAIL", "EMAIL CORRECTO");
            return true;
        }
        return false;
    }

    private boolean comprobarLenghtUsuario(String usuario){
        if(usuario.length() < 6){
            return false;
        }
        return true;
    }

    private void addUser(){
        Usuario user = new Usuario(usuario, contra, email, telefono, fechaNacimiento, "Cliente");
        Cliente cliente = new Cliente(user.getId());
        dRef.child("Usuarios").child(user.getId()).setValue(user);
        dRef.child("Clientes").child(cliente.getIdCliente()).setValue(cliente);
        Toast.makeText(this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
    }
}