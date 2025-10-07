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

        Usuario elisa = new Usuario("Elisa", "1234");
        Usuario carlos = new Usuario("Carlos", "abcd");

        daoUsuarios.registrarUsuario(elisa);
        daoUsuarios.registrarUsuario(carlos);

        add(elisa, new Post(1, "Hoy aprendí a usar Thymeleaf sin romper nada 😅", new Date(), 5, 1));
        add(carlos, new Post(2, "Spring Boot empieza a tener sentido.", new Date(), 3, 0));
        add(elisa, new Post(3, "Ya casi acabo el proyecto 🚗", new Date(), 7, 2));
    }

    @Override
    public void add(Usuario usuario, Post post) {
        usuario.addPost(post);
        this.posts.add(post);
    }

    @Override
    public List<Post> listaPosts() {
        return this.posts;
    }
}
