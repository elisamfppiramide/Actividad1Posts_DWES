package com.cpifppiramide.demo.controller;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.clases.Usuario;
import com.cpifppiramide.demo.dao.DAOFactory;
import com.cpifppiramide.demo.dao.posts.DAOPosts;
import com.cpifppiramide.demo.dao.usuarios.DAOUsuarios;
import com.cpifppiramide.demo.dao.usuarios.DAOUsuariosRAM;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String verPosts(Model model){
        DAOFactory daoFactory = DAOFactory.getInstance();
        List<Post> posts = daoFactory.getDaoPosts().listaPosts();
        model.addAttribute("posts", posts);
        return "posts";
    }

    @PostMapping("/post/like/{id}")
    public String likePost(@PathVariable int id){
        DAOFactory daoFactory = DAOFactory.getInstance();
        List<Post> posts = daoFactory.getDaoPosts().listaPosts();
        for(Post post : posts){
            if(post.getId() == id){
                post.setLikes(post.getLikes() + 1);
                break;
            }
        }

        return "redirect:/posts";
    }

    @PostMapping("/post/repost/{id}")
    public String repostPost(@PathVariable int id){
        DAOFactory daoFactory = DAOFactory.getInstance();
        List<Post> posts = daoFactory.getDaoPosts().listaPosts();
        for(Post post : posts){
            if(post.getId() == id){
                post.setRepost(post.getRepost() +1);
                break;
            }
        }
        return "redirect:/posts";
    }

    @PostMapping("/posts/add")
    public String addPost(@RequestParam String texto, HttpSession sesion) {
        Usuario usuario = (Usuario) sesion.getAttribute("usuarioLogueado");
        if (usuario == null) {
            System.out.println("⚠️ Usuario no encontrado en sesión.");
            return "redirect:/inicioSesion";
        }

        System.out.println("Usuario logueado: " + usuario.getNombreUsuario());
        System.out.println("Usuario completo: " + usuario.getPassword() + usuario.getNombreUsuario() + usuario.getId());
        System.out.println("ID del usuario: " + usuario.getId());

        Post post = new Post(texto, usuario);
        DAOFactory.getInstance().getDaoPosts().add(post);

        return "redirect:/posts";
    }

    @GetMapping("/posts/user")
    public String filtrarPorUsuario(@RequestParam("id") Long idUsuario, Model model) {
        List<Post> posts = DAOFactory.getInstance().getDaoPosts().listaFiltrarUsuario(idUsuario);
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/fecha")
    public String postsOrdenadosFecha(@RequestParam String fechaAhora, @RequestParam String fechaLuego, Model model) {
        List<Post> posts = DAOFactory.getInstance().getDaoPosts().listaFiltrarFecha(fechaAhora, fechaLuego);
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/search")
    public String postsPorTexto(@RequestParam String texto, Model model) {
        List<Post> posts = DAOFactory.getInstance().getDaoPosts().listaFiltrarTexto(texto);
        model.addAttribute("posts", posts);
        return "posts";
    }
}

