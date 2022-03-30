package com.example.editoria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Login extends AppCompatActivity {

    private EditText campoEmail;
    private EditText campoPassword;
    private FirebaseDatabase Fdatabase;
    private DatabaseReference dRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        campoEmail= (EditText) findViewById(R.id.emailLogIn);
        campoPassword= (EditText) findViewById(R.id.contraLogin);
        Fdatabase= FirebaseDatabase.getInstance("https://editoria-bb3aa-default-rtdb.europe-west1.firebasedatabase.app/");
        dRef= Fdatabase.getReference();
        getSupportActionBar().hide();
    }

    public void register(View view) {
        Intent intent = new Intent(this, Register.class);
        startActivity(intent);
    }

    public void login(View _){
        String email=campoEmail.getText().toString();
        String password = campoPassword.getText().toString();
        dRef.child("Usuarios").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (!task.isSuccessful()){
                    Log.e("firebase", "Error getting data", task.getException());
                }else{
                    Log.d("firebase", String.valueOf(task.getResult().getValue()));
                }
            }
        });
    }

    public void forgotenPass(View _){
        //Intent intent = new Intent(this,);
        //startActivity(intent);
    }
}