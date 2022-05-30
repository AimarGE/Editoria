package com.example.editoria.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.UUID;

public class Editor {

    private String categoria;
    private String idUsuario;
    private String idEditor;
    private String nombreUsuario;
    private ArrayList<Proyecto> proyectos = new ArrayList<Proyecto>();
    private String mail;
    private String twitter;
    private String facebook;
    private String valoraciones;


    public Editor(String categoria, String idUsuario, String nombre){

        this.categoria=categoria;
        this.idUsuario=idUsuario;
        this.nombreUsuario=nombre;
        this.proyectos = new ArrayList<Proyecto>();
        this.mail = "";
        this.twitter = "";
        this.facebook = "";
        this.valoraciones = "";;
    }


    public Editor(){
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void addProyecto(Proyecto proyecto){
        this.proyectos.add(proyecto);
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getIdEditor() {
        return idEditor;
    }

    public void setIdEditor(String idEditor) {
        this.idEditor = idEditor;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public ArrayList<Proyecto> getProyectos() {
        return proyectos;
    }

    public void setProyectos(ArrayList<Proyecto> proyectos) {
        this.proyectos = proyectos;
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

    public String getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(String valoraciones) {
        this.valoraciones = valoraciones;
    }

    @Override
    public String toString() {
        return "Editor{" +
                "categoria='" + categoria + '\'' +
                ", idUsuario='" + idUsuario + '\'' +
                ", idEditor='" + idEditor + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", proyectos=" + proyectos +
                ", mail='" + mail + '\'' +
                ", twitter='" + twitter + '\'' +
                ", facebook='" + facebook + '\'' +
                '}';
    }
}

