package com.example.editoria.model;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class Usuario {

    private String id;
    private String usuario;
    private String password;
    private String correoE;
    private String telefono;
    private String fechaNacimiento;
    private String clase;
    private String pais;
    private String icono;
    private String dinero;
    private String fotoEvento;

    public Usuario(String usuario, String password, String correoE, String telefono, String fechaNacimiento, String clase, String pais) {
        this.id = UUID.randomUUID().toString();
        this.usuario = usuario;
        this.password = password;
        this.correoE = correoE;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.clase = clase;
        this.pais = pais;
        this.icono = "";
        this.dinero = String.format("%.2f",ThreadLocalRandom.current().nextDouble(1, 500)).replace(",",".");
        this.fotoEvento="";
    }

    public Usuario() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCorreoE() {
        return correoE;
    }

    public void setCorreoE(String correoE) {
        this.correoE = correoE;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getIcono() {
        return icono;
    }

    public void setIcono(String icono) {
        this.icono = icono;
    }

    public String getDinero() {
        return dinero;
    }

    public void setDinero(String dinero) {
        this.dinero = dinero;
    }

    public void setFotoEvento(String foto){
        this.fotoEvento=foto;
    }

    public String getFotoEvento(){
        return this.fotoEvento;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", usuario='" + usuario + '\'' +
                ", password='" + password + '\'' +
                ", correoE='" + correoE + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", clase='" + clase + '\'' +
                ", pais='" + pais + '\'' +
                ", icono='" + icono + '\'' +
                ", dinero=" + dinero +
                '}';
    }
}
