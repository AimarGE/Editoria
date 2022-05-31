package com.example.editoria.model;

public class ListElementComentario {

    public String icon;
    public String name;
    public String comentario;
    public String valoracion;

    public ListElementComentario(String icon, String name, String comentario, String valoracion) {
        this.icon = icon;
        this.name = name;
        this.comentario = comentario;
        this.valoracion = valoracion;
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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }
}