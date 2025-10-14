package com.cpifppiramide.demo.dao.usuarios;

import com.cpifppiramide.demo.clases.Usuario;

import java.util.List;

public interface DAOUsuarios {
    public List<Usuario> listaClientes();
    public void registrarUsuario(Usuario usuario);
    public Usuario buscaUsuario(String nombreUsuario, String password);
    public Usuario getUsuario(Usuario usuario);
}
