package com.example.editoria.model;

import java.io.Serializable;

public class ListElement implements Serializable {

    public String icon;
    public String name;
    public String titulo;
    public String descripcion;
    public double precio;

    public ListElement(String icon, String name, String descripcion, String titulo, double precio) {
        this.icon = icon;
        this.name = name;
        this.descripcion = descripcion;
        this.titulo = titulo;
        this.precio = precio;
    }

    public String getIcon() {
        return icon;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "ListElement{" +
                "name='" + name + '\'' +
                ", titulo='" + titulo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio='" + precio + '\'' +
                '}';
    }
}
