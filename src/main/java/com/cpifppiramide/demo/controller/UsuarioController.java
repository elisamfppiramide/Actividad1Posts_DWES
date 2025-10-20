package com.cpifppiramide.demo.controller;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.clases.Usuario;
import com.cpifppiramide.demo.dao.DAOFactory;
import com.cpifppiramide.demo.dao.usuarios.DAOUsuarios;
import com.cpifppiramide.demo.dao.usuarios.DAOUsuariosMySQL;
import com.cpifppiramide.demo.dao.usuarios.DAOUsuariosRAM;
import jakarta.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @PostMapping("/inicioSesion")
    String inicioSesion(@RequestParam String nombreUsuario, @RequestParam String password, Model model, HttpSession session){
       DAOFactory daoFactory = DAOFactory.getInstance();
       Usuario usuario = daoFactory.getDaoUsuarios().inicioUsuario(nombreUsuario, password);
       if(usuario == null){
           model.addAttribute("error", "Usuario no encontrado");
           return "inicioSesion";
       }
       model.addAttribute("usuario", usuario);
       session.setAttribute("usuarioLogueado", usuario);
       System.out.println("Usuario logueado " + nombreUsuario);

       return "redirect:/posts";
    }

    @PostMapping("/usuario/registrar")
    public String registrar(@RequestParam String nombreUsuario, @RequestParam String password, Model model) {
        Usuario usuarioExistente = DAOFactory.getInstance().getDaoUsuarios().buscarUsuarioNyC(nombreUsuario, password);
        if (usuarioExistente != null) {
            model.addAttribute("error", "El nombre de usuario ya está registrado.");
            return "registrarse";
        }
        Usuario usuarioNuevo = new Usuario(nombreUsuario, password);
        DAOFactory.getInstance().getDaoUsuarios().registrarUsuario(usuarioNuevo);
        System.out.println("Usuario registrado: " + usuarioNuevo);

        return "redirect:/inicioSesion";
    }
}
