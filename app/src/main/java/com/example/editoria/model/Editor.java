package com.example.editoria.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class Editor {

    private String categoria;
    private String idUsuario;
    private String idEditor;
    private String nombreUsuario;
    private ArrayList<Proyecto> proyectos;

    public Editor(String categoria, String idUsuario, String nombre){
        this.idEditor= UUID.randomUUID().toString();
        this.categoria=categoria;
        this.idUsuario=idUsuario;
        this.nombreUsuario=nombre;
        this.proyectos = new ArrayList<>();
    }

    public Editor(){

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

    public void addProyecto(Proyecto proyecto){
        if(this.proyectos == null){
            proyectos = new ArrayList<>();
        }
        this.proyectos.add(proyecto);
    }

    public ArrayList<Proyecto> getProyectos(){
        return proyectos;
    }

    @Override
    public String toString() {
        return "Editor{" +
                "categoria='" + categoria + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", idEditor='" + idEditor + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", proyectos=" + proyectos +
                '}';
    }
}
