package com.example.editoria.model;

public class RecursosCliente {


    private String nombreCliente;
    private String imagen;
    private String descripcion;
    private double precio;

    public RecursosCliente(String nombreCliente, String imagen, String descripcion, double precio) {
        this.nombreCliente = nombreCliente;
        this.imagen = imagen;
        this.descripcion = descripcion;
        this.precio = precio;
    }

    public RecursosCliente(){

    }

    @Override
    public String toString() {
        return "RecursosCliente{" +
                "nombreCliente='" + nombreCliente + '\'' +
                ", imagen='" + imagen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                '}';
    }
}
