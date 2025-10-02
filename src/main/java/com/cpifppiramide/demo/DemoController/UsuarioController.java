package com.cpifppiramide.demo.DemoController;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.clases.Usuario;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@org.springframework.stereotype.Controller
public class UsuarioController {
    private List<Usuario> usuarios =  new ArrayList<>();

    public UsuarioController(){

        Usuario user1 = new Usuario("Carlos", "carlos123");
        Usuario user2 = new Usuario("Ana", "ana_g");
        Usuario user3 = new Usuario("Luis", "luis_dev");

        // Creamos posts
        Post post1 = new Post(1, user1, "¡Hola a todos! Este es mi primer post 🚀", new Date(), 10, 2);
        Post post2 = new Post(2, user2, "Hoy aprendí sobre programación en Java 💻", new Date(), 25, 5);
        Post post3 = new Post(3, user3, "¿Alguien sabe cómo usar streams en Java?", new Date(), 15, 3);
        Post post4 = new Post(4, user1, "Practicando POO en Java, está genial 😎", new Date(), 40, 8);

        user1.addPost(post1);
        user1.addPost(post4);
        user2.addPost(post2);
        user3.addPost(post3);

        usuarios.add(user1);
        usuarios.add(user2);
        usuarios.add(user3);
    }

    @PostMapping("/")

    @RequestMapping("/inicioSesion")
    String inicioSesion(){
        return "inicioSesion";
    }

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







}
