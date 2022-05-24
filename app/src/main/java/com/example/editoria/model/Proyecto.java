package com.example.editoria.model;

public class Proyecto {

    private String nombre;
    private String descripcion;
    private String disponibilidad;
    private String nombreUsuario;
    private RecursosCliente recursoCliente;
    private String foto;

    public Proyecto(String nombre, String descripcion, String disponibilidad, String nombreUsuario, String foto){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.disponibilidad=disponibilidad;
        this.nombreUsuario=nombreUsuario;
        this.foto=foto;
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

    public RecursosCliente getRecursoCliente() {
        return recursoCliente;
    }

    public String getFoto() {
        return foto;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", disponibilidad='" + disponibilidad + '\'' +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", recursoCliente=" + recursoCliente +
                ", foto='" + foto + '\'' +
                '}';
    }
}
