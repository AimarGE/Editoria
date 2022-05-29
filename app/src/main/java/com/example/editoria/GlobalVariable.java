package com.example.editoria;

import android.os.Bundle;

import com.example.editoria.model.Editor;
import com.example.editoria.model.RecursosCliente;
import com.example.editoria.model.Usuario;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class GlobalVariable {

    public static Bundle bundleEditor;
    public static Bundle bundleSolicitudOferta;
    public static String nombreUsuario;
    public static Usuario usuario;
    public static Editor editor;
    public static ArrayList<String> filtroDisponibilidad;
    public static ArrayList<Double> filtroPrecio;
}
