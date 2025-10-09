package com.cpifppiramide.demo.servicios;

import com.cpifppiramide.demo.clases.Usuario;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserServiceMySQL implements UserService {
    @Override
    public List<Usuario> getUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        try{


        }catch (SQLException e){
            return usuarios;
        }
        return usuarios;
    }
}
