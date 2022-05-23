package com.example.editoria.model;

public class Proyecto {

    private String nombre;
    private String descripcion;
    private String disponibilidad;
    private String nombreUsuario;
    private RecursosCliente recursoCliente;

    public Proyecto(String nombre, String descripcion, String disponibilidad, String nombreUsuario){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.disponibilidad=disponibilidad;
        this.nombreUsuario=nombreUsuario;
    }

    public Proyecto(){

    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getDisponibilidad() {
        return disponibilidad;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", disponibilidad='" + disponibilidad + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                '}';
    }
}
