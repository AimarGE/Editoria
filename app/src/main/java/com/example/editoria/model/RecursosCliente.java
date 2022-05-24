package com.example.editoria.model;

import java.io.Serializable;

public class RecursosCliente implements Serializable {


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

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
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
