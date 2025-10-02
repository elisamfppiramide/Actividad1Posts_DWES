package com.cpifppiramide.demo.clases;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String nombreUsuario;
    private String password;
    private List<Post> posts;

    public Usuario(String nombreUsuario, String password) {
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.posts = new ArrayList<>();
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }
    public String getPassword() {
        return password;
    }

    public List<Post> getPosts() {
        return posts;
    }
    public void addPost(Post post) {
        posts.add(post);
    }
    @Override
    public String toString() {
        return  "Nombre de usuario: " + nombreUsuario + "\n" +
                "Contraseña: " + password;
    }
}
