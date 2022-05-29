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
    private String mail;
    private String twitter;
    private String facebook;

    public Editor(String categoria, String idUsuario, String nombre){

        this.categoria=categoria;
        this.idUsuario=idUsuario;
        this.nombreUsuario=nombre;
        this.proyectos = new ArrayList<>();
        this.mail = "";
        this.twitter = "";
        this.facebook = "";
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
        this.proyectos.add(proyecto);
    }

    public ArrayList<Proyecto> getProyectos(){
        return proyectos;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getTwitter() {
        return twitter;
    }

    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }

    public String getFacebook() {
        return facebook;
    }

    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
}

