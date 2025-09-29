package com.cpifppiramide.demo.clases;

public class Usuario {
    private String nombreUsuario;
    private String password;

    public Usuario(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
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
