package com.cpifppiramide.demo.dao.posts;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.clases.Usuario;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public interface DAOPosts {

    public void add(Post post);
    public List<Post> listaPosts();
    public List<Post> listaFiltrarUsuario(Long usuarioId);
    public List<Post> listaFiltrarTexto(String texto);
    public List<Post> listaFiltrarFecha(String fechaAhora, String fechaLuego);



}
