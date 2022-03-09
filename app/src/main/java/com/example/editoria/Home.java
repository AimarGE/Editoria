package com.example.editoria;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Home extends AppCompatActivity {

    private Dialog dialog;
    private Button cancelar, bFiltro;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        bFiltro = findViewById(R.id.button6);


        /*
        bFiltro = findViewById(R.id.bfiltro);
        dialog = new Dialog(this);

        bFiltro.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Log.i("PRUEBA", "asdasd");
                dialog.setContentView(R.layout.paginafiltro);

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        });*/

    }

    public void ss(View view){
        Log.i("ejemplo", "rara arara ");
    }



}
