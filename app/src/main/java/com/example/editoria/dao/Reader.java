package com.example.editoria.dao;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Reader {

    private BufferedReader br;
    private FileReader fr;

	public Reader(String fichero) {
	    try {
	        this.fr = new FileReader(fichero);
	        this.br = new BufferedReader(this.fr);
	    } catch (IOException ex) {
	        System.out.println("FICHERO NO ENCONTRADO " + ex.getMessage());
	    }
	}

	public String readLine() {
	    try {
	        return br.readLine();
	    } catch (IOException e) {
	        System.out.println("ERROR AL LEER EL ARCHIVO: " + e.getMessage());
	    }
	    return null;
	}

	public void closeFile() {
	    try {
	        if (br != null) {
	            br.close();
	        }
	    } catch (IOException ex) {
	        System.out.println("Error al cerrar el archivo: " + ex.getMessage());
	    }
	}

	public int read() {
	    try {
	        return fr.read();
	    } catch (IOException ex) {
	        System.out.println("ERROR al leer el archivo: " + ex.getMessage());
	    }
	    return -1;
	}
}

