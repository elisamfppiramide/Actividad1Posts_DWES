package com.cpifppiramide.demo.dao.posts;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.clases.Usuario;

import java.util.ArrayList;
import java.util.List;

public class DAOPostsRAM implements DAOPosts{

    private List<Post> posts;

    public DAOPostsRAM(){
        this.posts = new ArrayList<>();
    }

    @Override
    public void add(Usuario usuario, Post post) {
        usuario.addPost(post);
    }

    @Override
    public List<Post> listaPosts() {
        return this.posts;
    }
}
