package com.cpifppiramide.demo.dao.usuarios;

import com.cpifppiramide.demo.clases.Usuario;
import com.cpifppiramide.demo.dao.DBConnector;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DAOUsuariosMySQL implements DAOUsuarios{
    @Override
    public List<Usuario> listaClientes() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "select * from usuario";

        try{
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Usuario usuario = new Usuario(rs.getLong("id"), rs.getString("nombreUsuario"), rs.getString("password"));
                usuarios.add(usuario);
            }
        }catch (SQLException e){
            throw new RuntimeException();
        }
        return usuarios;
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        try{
            String query = "insert into usuario values(?, ?, ?)";
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(0, usuario.getNombreUsuario());
            ps.setString(1, usuario.getPassword());
            ps.execute();
        }catch(SQLException e){
            throw new RuntimeException();
        }
    }

    @Override
    public Usuario getUsuario(Usuario usuario) {
        return usuario;
    }

    @Override
    public Usuario buscaUsuario(String nombreUsuario, String password) {
        String query = "select * from usuario where nombreUsuario = ? AND password = ?";
        try {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(0, nombreUsuario);
            ps.setString(1, password);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getLong("id"), rs.getString("nombreUsuario"), rs.getString("password"));
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return null;
    }

}
