package com.example.editoria.model;

import java.util.ArrayList;

public class Proyecto {

    private String nombre;
    private String descripcion;
    private String disponibilidad;
    private String nombreUsuario;
    private RecursosCliente recursoCliente;
    private String foto;
    private ArrayList<Paquete> paquetes;
    private String valoracion;
    private String comentario;

    public Proyecto(String nombre, String descripcion, String disponibilidad, String nombreUsuario, String foto, ArrayList<Paquete> paquetes){
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.disponibilidad=disponibilidad;
        this.nombreUsuario=nombreUsuario;
        this.foto=foto;
        this.paquetes = paquetes;
        this.valoracion = "";
        this.comentario = "";
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

    public ArrayList<Paquete> getPaquetes(){
        if(paquetes == null){
            paquetes = new ArrayList<>();
        }
        return this.paquetes;
    }

    public String getPaqueteMasBarato(){
        if(paquetes.size() == 1){
            return paquetes.get(0).getPrecio();
        }else if(paquetes.size() == 2){
            if(Double.parseDouble(paquetes.get(0).getPrecio()) < Double.parseDouble(paquetes.get(1).getPrecio())){
                return paquetes.get(0).getPrecio();
            }else{
                return paquetes.get(1).getPrecio();
            }
        }else if(paquetes.size() == 3){
            if(Double.parseDouble(paquetes.get(0).getPrecio()) < Double.parseDouble(paquetes.get(1).getPrecio()) && Double.parseDouble(paquetes.get(0).getPrecio()) < Double.parseDouble(paquetes.get(2).getPrecio())){
                return paquetes.get(0).getPrecio();
            }
            else if(Double.parseDouble(paquetes.get(1).getPrecio()) < Double.parseDouble(paquetes.get(0).getPrecio()) && Double.parseDouble(paquetes.get(1).getPrecio()) < Double.parseDouble(paquetes.get(2).getPrecio())){
                return paquetes.get(1).getPrecio();
            }else{
                return paquetes.get(2).getPrecio();
            }
        }
        return null;
    }

    public String getValoracion() {
        return valoracion;
    }

    public void setValoracion(String valoracion) {
        this.valoracion = valoracion;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
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
