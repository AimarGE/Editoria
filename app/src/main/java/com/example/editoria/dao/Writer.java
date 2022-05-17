package com.example.editoria.dao;

import android.content.Context;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Writer {

/*
    public void writeToFile(String user, String password, Context context) {
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(context.openFileOutput("./.data/credentials.txt", Context.MODE_PRIVATE));
            outputStreamWriter.write(user);
            outputStreamWriter.write(password);
            outputStreamWriter.close();
        }
        catch (IOException e) {
            Log.e("Exception", "File write failed: " + e.toString());
        }
    }
 */

    private FileWriter fw;
    private BufferedWriter bw;

    public Writer(String fichero) {
        try {
            this.fw= new FileWriter(fichero);
            this.bw= new BufferedWriter(this.fw);
        } catch (IOException ex) {
            System.out.println("FICHERO NO ENCONTRADO" + ex.getMessage());
        }
    }

    public void escribir(String texto) {
        try {
            bw.write(texto);
        }
        catch(IOException e){
            System.out.println("ERROR al escribir en el fichero: " + e.getMessage());
        }
    }

    public void closeFile() {
        try {
            if(bw != null) {
                bw.close();
            }
        }
        catch(IOException ex){
            System.out.println("Error al cerrar el archivo: " + ex.getMessage());
        }
    }
}
