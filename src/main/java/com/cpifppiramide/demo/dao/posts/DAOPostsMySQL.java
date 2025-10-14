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
        String query = "insert into post (texto, id_usuario) values(?, ?)";

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
        String query = "select * from post";
        try{
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Post post = new Post(rs.getLong("id"), rs.getString("texto"), rs.getDate("fecha"), rs.getInt("likes"), rs.getInt("repost"), null);
            }
        }catch(SQLException e){
            throw new RuntimeException();
        }
        return posts;
    }
}
