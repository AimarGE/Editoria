package com.example.editoria.model;

public class ListElementComentario {

    public String icon;
    public String name;
    public String comentario;

    public ListElementComentario(String icon, String name, String comentario) {
        this.icon = icon;
        this.name = name;
        this.comentario = comentario;
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

}
