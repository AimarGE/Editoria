package com.example.editoria.model;

public class Paquete {

    private String tipo;
    private String precio;
    private String descripcion;


    public Paquete(String tipo, String precio, String descripcion){
        this.tipo=tipo;
        this.precio=precio;
        this.descripcion=descripcion;
    }

    public Paquete(){

    }

    public String getPrecio() {
        return precio;
    }

    public String getTipo(){
        return tipo;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
