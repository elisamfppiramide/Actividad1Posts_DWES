package com.cpifppiramide.demo.dao.posts;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.clases.Usuario;
import com.cpifppiramide.demo.dao.usuarios.DAOUsuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DAOPostsRAM implements DAOPosts{


    private List<Post> posts;
    private DAOUsuarios daoUsuarios;

    public DAOPostsRAM(DAOUsuarios daoUsuarios){
        this.posts = new ArrayList<>();
        this.daoUsuarios = daoUsuarios;
    }

    @Override
    public void add(Post post) {
        posts.add(post);
    }

    @Override
    public List<Post> listaPosts() {
        return this.posts;
    }

    @Override
    public List<Post> listaFiltrarUsuario(String nombreUsuario) {
        return List.of();
    }


    @Override
    public List<Post> listaFiltrarTexto(String texto) {
        return List.of();
    }

    @Override
    public List<Post> listaFiltrarFecha(String fechaAhora, String fechaLuego) {
        return List.of();
    }
}