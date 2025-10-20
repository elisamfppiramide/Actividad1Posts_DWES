package com.cpifppiramide.demo.clases;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private Integer id;
    private String nombreUsuario;
    private String password;

    public Usuario(Integer id, String nombreUsuario, String password) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }
    public Usuario(String nombreUsuario, String password){
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }
    public Usuario(Integer id, String nombreUsuario){
        this.id = id;
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getId() {
        return id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return  "Nombre de usuario: " + nombreUsuario + "\n" +
                "Contraseña: " + password;
    }
}
