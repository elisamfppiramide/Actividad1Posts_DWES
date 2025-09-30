package com.cpifppiramide.demo.DemoController;

import com.cpifppiramide.demo.clases.Usuario;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@org.springframework.stereotype.Controller
public class Controller {
    private List<Usuario> usuarios =  new ArrayList<>();

    @RequestMapping("/index")
    String inicio(){
        return "index";
    }

    @GetMapping ("/registrarse")
    String registro(){
        return "registrarse";
    }
    @PostMapping
    String registrar(Model model, Usuario usuario){
        boolean existe = usuario.getNombreUsuario().equals(usuario.getNombreUsuario());
        if(existe){
            model.addAttribute("mensaje","Este usuario ya existe");
            return "registrarse";
        }else{
            usuarios.add(usuario);
        }
        return "inicioSesion";
    }
    @RequestMapping("/inicioSesion")
    String inicioSesion(){
        return "inicioSesion";
    }






}
