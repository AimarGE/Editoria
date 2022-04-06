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

    public Usuario(String usuario, String password, String correoE, String telefono, String fechaNacimiento, String clase){
        this.id= UUID.randomUUID().toString();
        this.usuario=usuario;
        this.password=password;
        this.correoE=correoE;
        this.telefono=telefono;
        this.fechaNacimiento=fechaNacimiento;
        this.clase=clase;
    }

    public String getId(){
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
}
