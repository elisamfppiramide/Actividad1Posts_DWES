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
    public void registrarUsuario(Usuario usuario) {
        try{
            String query = "insert into usuario (nombreUsuario, password) values (?, ?)";
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1, usuario.getNombreUsuario());
            ps.setString(2, usuario.getPassword());
            ps.execute();
        }catch(SQLException e){
            throw new RuntimeException("Fallo al registrar al usuario" + e);
        }
    }

    @Override
    public Usuario inicioUsuario(String nombreUsuario, String password) {
        String query = "select id_usuario, nombreUsuario, password from usuario where nombreUsuario = ? and password = ?";
        try{
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Usuario(rs.getInt("id_usuario"), rs.getString("nombreUsuario"), rs.getString("password"));
            }
        } catch (SQLException e) {
            System.out.println("Error al hacer el login " + e.getMessage());
        }
        return null;
    }

    @Override
    public Usuario buscarUsuarioNyC(String nombreUsuario, String password) {
        String query =  "select * from usuario where nombreUsuario = ? and password = ?";
        try{
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                return new Usuario(rs.getInt("id"), rs.getString("nombreUsuario"), rs.getString("password"));
            }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public Usuario buscaUsuario(String nombreUsuario) {
        String query = "select * from usuario where nombreUsuario = ?";
        try {
            PreparedStatement ps = DBConnector.getInstance().prepareStatement(query);
            ps.setString(1, nombreUsuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return new Usuario(rs.getInt("id"), rs.getString("nombreUsuario"), null);
            }
        } catch (SQLException e) {
            throw new RuntimeException();
        }
        return null;
    }

    @Override
    public Usuario getUsuario(Usuario usuario) {
        return usuario;
    }

}
