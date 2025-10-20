package com.cpifppiramide.demo.dao.posts;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.clases.Usuario;
import com.cpifppiramide.demo.dao.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
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
        String query = "select p.*, u.id_usuario AS usuario_id, u.nombreUsuario from post p join usuario u ON p.fk_id_usuario = u.id_usuario";
        try{
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario(rs.getInt("usuario_id"), rs.getString("nombreUsuario"));
                Post post = new Post(rs.getInt("id"), rs.getString("texto"), rs.getTimestamp("fecha"), rs.getInt("likes"), rs.getInt("repost"), usuario);
                posts.add(post);
            }
        }catch(SQLException e){
            throw new RuntimeException("Error al obtener los datos del post" + e);
        }
        return posts;
    }

    @Override
    public List<Post> listaFiltrarUsuario(String nombreUsuario) {
        String query = "select p.id, p.texto, p.fecha, p.likes, p.repost, u.id_usuario AS usuarioID, u.nombreUsuario from post p INNER JOIN usuario u ON p.fk_id_usuario = u.id_usuario WHERE u.nombreUsuario = ?";
        List<Post> listaPosts = new ArrayList<>();
        try{
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario(rs.getInt("usuarioID"), rs.getString("nombreUsuario"));
                Post post = new Post(rs.getInt("id"), rs.getString("texto"), rs.getTimestamp("fecha"), rs.getInt("likes"), rs.getInt("repost"), usuario);
                listaPosts.add(post);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaPosts;
    }


    //Aquí está el chorizo de la fecha (sacarlo a una funcion para poder añadirlo en cada query)
    @Override
    public List<Post> listaFiltrarTexto(String texto) {
        String query = "select p.id, p.texto, p.likes, p.repost, p.fecha, u.id_usuario AS usuarioID, u.nombreUsuario from post p inner join usuario u on p.fk_id_usuario = u.id_usuario where p.texto like ?";
        List<Post> listaPosts = new ArrayList<>();
        try{
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1, "%" + texto + "%");
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario(rs.getInt("usuarioID"), rs.getString("nombreUsuario"));
                String fecha = rs.getString("fecha");
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                Date date = Date.from(LocalDate.parse(fecha, formatter).atStartOfDay(ZoneId.systemDefault()).toInstant());
                Post post = new Post(rs.getInt("id"), rs.getString("texto"), date, rs.getInt("likes"), rs.getInt("repost"), usuario);
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
                Post post = new Post(rs.getInt("id"), rs.getString("texto"), rs.getTimestamp("fecha"), rs.getInt("likes"), rs.getInt("repost"), null);
                listaPosts.add(post);
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return listaPosts;
    }
}
