package com.cpifppiramide.demo.dao.posts;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.clases.Usuario;

import java.util.ArrayList;
import java.util.List;

public interface DAOPosts {

    public void add(Post post);
    public List<Post> listaPosts();

}
