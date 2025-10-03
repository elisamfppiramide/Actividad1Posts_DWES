package com.cpifppiramide.demo.controller;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.clases.Usuario;
import com.cpifppiramide.demo.dao.DAOFactory;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
public class UsuarioController {

    @RequestMapping("/inicioSesion")
    String inicioSesion(){
        return "inicioSesion";
    }
    @RequestMapping("/index")
    String inicio(){
        return "index";
    }

    @GetMapping ("/usuario/nuevo")
    String registro(){
        return "registrarse";
    }

    @PostMapping("/usuario/registrar")
    public String registrar(Usuario usuario, Model model) {
        Usuario usuarioExistente = DAOFactory.getInstance().getDaoUsuarios().buscaUsuario(usuario.getNombreUsuario());
        if (usuarioExistente != null) {
            model.addAttribute("error", "El nombre de usuario ya está registrado.");
            return "registro";
        }
        DAOFactory.getInstance().getDaoUsuarios().registrarUsuario(usuario);
        System.out.println("Usuario registrado: " + usuario);

        return "redirect:/inicioSesion";
    }

    @GetMapping("/usuarios")
    public String mostrarUsuarios(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        List<Usuario> usuarios = daoFactory.getDaoUsuarios().listaClientes();
        System.out.println(usuarios);
        return "usuarios";
    }







}
