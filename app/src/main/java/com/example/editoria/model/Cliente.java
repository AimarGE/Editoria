package com.example.editoria.model;

import java.util.UUID;

public class Cliente {

    private String idUsuario;
    private String idCliente;
    //private ArrayList<Proyecto> proyectos;

    public Cliente(String idUsuario){
        this.idCliente= UUID.randomUUID().toString();
        this.idUsuario=idUsuario;
    }

    public String getIdUsuario(){
        return idUsuario;
    }

    public String getIdCliente(){
        return idCliente;
    }
}
