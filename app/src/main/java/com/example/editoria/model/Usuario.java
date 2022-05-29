package com.example.editoria.model;

import java.util.UUID;

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
    }

    public Usuario() {
    }

    public String getId() {
        return this.id;
    }

    public String getUsuario() {
        return usuario;
    }

    public String getPassword() {
        return password;
    }

    public String getCorreoE() {
        return correoE;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public String getClase() {
        return clase;
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
                '}';
    }
}
