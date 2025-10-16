package com.cpifppiramide.demo.dao.usuarios;

import com.cpifppiramide.demo.clases.Usuario;

import java.util.List;

public interface DAOUsuarios {
    public void registrarUsuario(Usuario usuario);
    public Usuario buscaUsuario(String nombreUsuario);
    public Usuario getUsuario(Usuario usuario);
    public Usuario inicioUsuario(String nombreUsuario, String password);
    public Usuario buscarUsuarioNyC(String nombreUsuario, String password);
}
