package com.example.editoria.model;

import java.io.Serializable;

public class ListElement implements Serializable {

    public String icon;
    public String name;
    public String titulo;
    public String descripcion;
    public String precio;
    public String foto;

    public ListElement(String icon, String name, String descripcion, String titulo, String foto, String precio) {
        this.icon = icon;
        this.name = name;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.precio = precio;
        this.foto = foto;
    }

    public void setColor(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    @Override
    public String toString() {
        return "ListElement{" +
                "name='" + name + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", foto='" + foto + '\'' +
                '}';
    }
}
