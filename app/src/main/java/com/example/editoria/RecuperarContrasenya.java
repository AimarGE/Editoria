package com.example.editoria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.editoria.model.Proyecto;
import com.example.editoria.model.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Properties;
import java.util.Random;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class RecuperarContrasenya extends AppCompatActivity {

    EditText usuario, correo, contrasenya, confContrasenya, codigoVer;
    TextView codigoVerificacionTV;
    Button confirmar, generarCodigo;
    Usuario usuarioC;
    String codigoVerificacion;
    private DatabaseReference dRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/");

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.recuperarcontrasenya);

        getSupportActionBar().hide();

        usuario = findViewById(R.id.usuario);
        correo = findViewById(R.id.correo);
        contrasenya = findViewById(R.id.contrasenya);
        confContrasenya = findViewById(R.id.confirmarContrasenya);
        codigoVer = findViewById(R.id.codigoVer);
        codigoVerificacionTV = findViewById(R.id.codigoVerTV);
        confirmar = findViewById(R.id.confirmar);
        generarCodigo = findViewById(R.id.enviarCodigo);
        usuarioC = new Usuario();
        init();

    }
    private void init() {
        listeners();
    }
    private void listeners() {

        generarCodigo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String contra = contrasenya.getText().toString();
                String cContra = confContrasenya.getText().toString();

                if (contra.equals("") || contra == null || cContra.equals("") || cContra == null || !contra.equals(cContra)){
                    Toast.makeText(RecuperarContrasenya.this, "Los campos contraseña són incorrectos", Toast.LENGTH_LONG).show();
                }else{

                    comprobarUsuario();

                }
            }
        });

        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                confirmarDatos();

            }
        });

    }

    private void confirmarDatos() {

        if (codigoVerificacion.equalsIgnoreCase(codigoVer.getText().toString())){
            cambiarContrasenya();
        }else{
            Toast.makeText(RecuperarContrasenya.this, "El código de verificación es incorrecto", Toast.LENGTH_LONG).show();
        }

    }

    private void cambiarContrasenya() {


        dRef.child("Usuarios").child(usuarioC.getUsuario()).setValue(null);

        usuarioC.setPassword(contrasenya.getText().toString());
        dRef.child("Usuarios").child(usuarioC.getUsuario()).setValue(usuarioC);

        Intent intent = new Intent(RecuperarContrasenya.this, Login.class);
        startActivity(intent);



    }

    private void comprobarUsuario() {


        if (usuario.getText().toString() == null || usuario.getText().toString().equalsIgnoreCase("")){
            Toast.makeText(RecuperarContrasenya.this, "Rellena el campo de usuario", Toast.LENGTH_LONG).show();
        }else{

            dRef.child("/Usuarios/"+usuario.getText().toString()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {

                    if (snapshot.getValue() == null){
                        Toast.makeText(RecuperarContrasenya.this, "El usuario no existe!", Toast.LENGTH_LONG).show();
                    }else{

                        usuarioC = snapshot.getValue(Usuario.class);

                        if (usuarioC.getCorreoE().equals(correo.getText().toString())){
                            generarCodigo();
                            confirmar.setVisibility(View.VISIBLE);
                            codigoVer.setVisibility(View.VISIBLE);
                            codigoVerificacionTV.setVisibility(View.VISIBLE);
                            Toast.makeText(RecuperarContrasenya.this, "Código de verificación enviado!", Toast.LENGTH_LONG).show();
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void generarCodigo() {

        int r1 = new Random().nextInt(9);
        int r2 = new Random().nextInt(9);
        int r3 = new Random().nextInt(9);
        int r4 = new Random().nextInt(9);
        int r5 = new Random().nextInt(9);

        codigoVerificacion = String.valueOf(r1)+String.valueOf(r2)+String.valueOf(r3)+String.valueOf(r4)+String.valueOf(r5);

        final String username = "editoria.app@gmail.com";
        final String password = "editoria2002!";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        Properties props = new Properties();

        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator(){
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correo.getText().toString()));
            message.setSubject("Código de verificacion");
            message.setText("Tu código de verificación es "+codigoVerificacion);
            Transport.send(message);
            Toast.makeText(getApplicationContext(), "Código de verificación enviado!", Toast.LENGTH_LONG).show();
        }catch (MessagingException e){
            Log.i("EJEMPLO", "asdasd"+e.getMessage().toString());
            //throw new RuntimeException(e);
        }

    }


}