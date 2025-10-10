package com.cpifppiramide.demo.clases;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private Long id;
    private String nombreUsuario;
    private String password;

    public Usuario(Long id, String nombreUsuario, String password) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public Long getId() {
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
