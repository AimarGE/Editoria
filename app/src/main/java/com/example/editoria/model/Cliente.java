package com.example.editoria.model;

import java.util.ArrayList;
import java.util.UUID;

public class Cliente {

    private String idUsuario;
    private String idCliente;
    private String nombreUsuario;
    private ArrayList<Proyecto> proyectos;

    public Cliente(String idUsuario, String nombre){
        this.idCliente= UUID.randomUUID().toString();
        this.idUsuario=idUsuario;
        this.nombreUsuario=nombre;
        this.proyectos = new ArrayList<>();
    }

    public String getIdUsuario(){
        return idUsuario;
    }

    public String getIdCliente(){
        return idCliente;
    }

    public String getNombre(){
        return nombreUsuario;
    }

    public void addProyecto(Proyecto proyecto){
        this.proyectos.add(proyecto);
    }
}
