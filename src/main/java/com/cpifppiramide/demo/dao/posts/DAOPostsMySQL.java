package com.cpifppiramide.demo.dao.posts;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.clases.Usuario;
import com.cpifppiramide.demo.dao.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOPostsMySQL implements DAOPosts{
    @Override
    public void add(Post post) {
        String query = "insert into post (texto, fk_id_usuario) values(?, ?)";
        try {
            PreparedStatement statement = DBConnector.getInstance().prepareStatement(query);
            statement.setString(1, post.getTexto());
            statement.setLong(2, post.getUsuario().getId());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Post> listaPosts() {
        List<Post> posts = new ArrayList<>();
        String query = "SELECT p.*, u.id_usuario AS usuario_id, u.nombreUsuario " +
                "FROM post p " +
                "JOIN usuario u ON p.fk_id_usuario = u.id_usuario";
        try{
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario(rs.getInt("usuario_id"), rs.getString("nombreUsuario"));
                Post post = new Post(rs.getInt("id"), rs.getString("texto"), rs.getDate("fecha"), rs.getInt("likes"), rs.getInt("repost"), usuario);
                posts.add(post);
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al obtener los datos del post" + e);
        }
        return posts;
    }

    @Override
    public List<Post> listaFiltrarUsuario(Long usuarioId) {
        String query = "select * from post where fk_id_usuario = ?";
        List<Post> listaPosts = new ArrayList<>();
        try{
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setLong(1, usuarioId);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Post post = new Post(rs.getInt("id"), rs.getString("texto"), rs.getDate("fecha"), rs.getInt("likes"), rs.getInt("repost"), null);
                listaPosts.add(post);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaPosts;
    }

    @Override
    public List<Post> listaFiltrarTexto(String texto) {
        String query = "select * from post where texto = ?";
        List<Post> listaPosts = new ArrayList<>();
        try{
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1, texto);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Post post = new Post(rs.getInt("id"), rs.getString("texto"), rs.getDate("fecha"), rs.getInt("likes"), rs.getInt("repost"), null);
                listaPosts.add(post);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaPosts;
    }

    @Override
    public List<Post> listaFiltrarFecha(String fechaAhora, String fechaLuego) {
    String query = "select * from post where fecha BETWEEN ? AND ?";
    List<Post> listaPosts = new ArrayList<>();
        try{
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1, fechaAhora);
            ps.setString(2, fechaLuego);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Post post = new Post(rs.getInt("id"), rs.getString("texto"), rs.getDate("fecha"), rs.getInt("likes"), rs.getInt("repost"), null);
                listaPosts.add(post);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaPosts;
    }
}
