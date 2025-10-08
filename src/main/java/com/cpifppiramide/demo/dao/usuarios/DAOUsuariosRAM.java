package com.cpifppiramide.demo.dao.usuarios;

import com.cpifppiramide.demo.clases.Usuario;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DAOUsuariosRAM implements DAOUsuarios{
    private List<Usuario> usuarios;
    private Usuario usuarioActual;

    public DAOUsuariosRAM(){
        this.usuarios = new ArrayList<>();
    }


    @Override
    public List<Usuario> listaClientes() {
        return this.usuarios;
    }

    @Override
    public void registrarUsuario(Usuario usuario) {
        this.usuarios.add(usuario);
    }

    @Override
    public Usuario buscaUsuario(String nombreUsuario) {
        for (Usuario usuario : this.usuarios) {
            if(usuario.getNombreUsuario().equalsIgnoreCase(nombreUsuario)) {
                return usuario;
            }
        }
        return null;
    }

    public void setUsuarioActual(Usuario usuarioActual) {
        this.usuarioActual = usuarioActual;
    }

    public Usuario getUsuarioActual() {
        return this.usuarioActual;
    }
}
