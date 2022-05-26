package com.example.editoria.model;

public class ListElementRanking {

    public String icon;
    public String name;
    public String foto;
    public String ranking;
    public String likes;

    public ListElementRanking(String icon, String name, String foto, String ranking, String likes) {
        this.icon = icon;
        this.name = name;
        this.foto = foto;
        this.ranking = ranking;
        this.likes = likes;
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

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getRanking() {
        return ranking;
    }

    public void setRanking(String ranking) {
        this.ranking = ranking;
    }

    public String getLikes() {
        return likes;
    }

    public void setLikes(String likes) {
        this.likes = likes;
    }
}
