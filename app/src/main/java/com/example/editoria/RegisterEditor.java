package com.example.editoria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.editoria.model.Editor;
import com.example.editoria.model.Usuario;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterEditor extends AppCompatActivity {

    private EditText CampoUsuario, CampoPassword, CampoConfirmPassword, CampoEmail, CampoTelefono, CampoFechaNacimiento;
    private String usuario, contra, contraConfirmar, email, telefono, fechaNacimiento;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    private String categoria;

    private FirebaseDatabase Fdatabase;
    private DatabaseReference dRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registereditor);

        CampoUsuario = (EditText) findViewById(R.id.usuario);
        CampoPassword = (EditText) findViewById(R.id.password);
        CampoConfirmPassword= (EditText) findViewById(R.id.confirmPassword);
        CampoEmail = (EditText) findViewById(R.id.correo);
        CampoTelefono = (EditText) findViewById(R.id.telefonoEditor);
        CampoFechaNacimiento = (EditText) findViewById(R.id.fechaNacimientoEditor);
        radioGroup= (RadioGroup) findViewById(R.id.radioGroup);

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
        int selectedId = radioGroup.getCheckedRadioButtonId();
        radioButton = (RadioButton) findViewById(selectedId);
        categoria = radioButton.getText().toString();

        if (usuario.equals("") || contra.equals("") || contraConfirmar.equals("") || email.equals("") || telefono.equals("") || fechaNacimiento.equals("") || categoria.equals("")){
            Toast.makeText(this, "Rellena todos los campos", Toast.LENGTH_LONG).show();
        }else{
            boolean entrada = comprobacionDatos();
            if(entrada){
                Intent intent = new Intent(this, Login.class);
                startActivity(intent);
            }
        }
    }

    private boolean comprobacionDatos() {
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
            CampoPassword.setHint("Contrase??as muy cortas");
            CampoConfirmPassword.setText("");
            CampoConfirmPassword.setHint("Contrase??as muy cortas");
        }
        if(contrasMatch){
            contador++;
        } else{
            CampoPassword.setText("");
            CampoPassword.setHint("Las contrase??as no coinciden");
            CampoConfirmPassword.setText("");
            CampoConfirmPassword.setHint("Las contrase??as no coinciden");
        }
        if (telefono) {
            contador++;
        }else{
            CampoTelefono.setText("");
            CampoTelefono.setHint("El tama??o del tel??fono no es correcto");
        }
        if (email) {
            contador++;
        }else{
            CampoEmail.setText("");
            CampoEmail.setHint("Formato de email no v??lido");
        }
        if(usuarioComp){
            contador++;
        }else{
            CampoUsuario.setText("");
            CampoUsuario.setHint("Nombre de usuario corto");
        }
        if(contador == 5){
            addUser();
            return true;
        }
        return false;
    }


    private boolean comprobarContrasLenght(){
        if(contra.length() < 6 || contra.length() > 14){
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
        if(usuario.length() < 6 || usuario.length() > 16){
            return false;
        }
        return true;
    }

    private void addUser(){
        Usuario user = new Usuario(usuario, contra, email, telefono, fechaNacimiento, "Editor", "");
        Editor editor = new Editor(categoria, user.getId(), usuario);
        dRef.child("Usuarios").child(usuario).setValue(user);
        dRef.child("Editores").child(usuario).setValue(editor);
        Toast.makeText(this, "Usuario registrado con ??xito", Toast.LENGTH_SHORT).show();
    }
}