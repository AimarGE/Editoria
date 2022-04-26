package com.example.editoria.model;

import java.util.UUID;

public class Editor {

    private String categoria;
    private String idUsuario;
    private String idEditor;
    private String nombreUsuario;

    public Editor(String categoria, String idUsuario, String nombre){
        this.idEditor= UUID.randomUUID().toString();
        this.categoria=categoria;
        this.idUsuario=idUsuario;
        this.nombreUsuario=nombre;
    }

    public String getIdEditor(){
        return idEditor;
    }

    public String getIdUsuario(){
        return idUsuario;
    }

    public String getCategoria(){
        return categoria;
    }

    public String getNombreUsuario(){
        return nombreUsuario;
    }
}
