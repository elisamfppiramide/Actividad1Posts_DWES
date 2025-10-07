package com.cpifppiramide.demo.controller;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.clases.Usuario;
import com.cpifppiramide.demo.dao.DAOFactory;
import com.cpifppiramide.demo.dao.posts.DAOPosts;
import com.cpifppiramide.demo.dao.usuarios.DAOUsuarios;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String mostrarPosts(Model model){
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOUsuarios daoUsuarios = daoFactory.getDaoUsuarios();
        DAOPosts daoPosts = daoFactory.getDaoPosts();

        if (daoUsuarios.listaClientes().isEmpty()) {
            Usuario elisa = new Usuario("Elisa", "1234");
            Usuario carlos = new Usuario("Carlos", "abcd");

            daoUsuarios.registrarUsuario(elisa);
            daoUsuarios.registrarUsuario(carlos);

            daoPosts.add(elisa, new Post(1, "Hoy aprendí a usar Thymeleaf sin romper nada 😅", new Date(), 5, 1));
            daoPosts.add(carlos, new Post(2, "Spring Boot empieza a tener sentido.", new Date(), 3, 0));
            daoPosts.add(elisa, new Post(3, "Ya casi acabo el proyecto 🚗", new Date(), 7, 2));
        }
        List<Post> todosLosPosts = new ArrayList<>();
        for (Usuario usuario : daoUsuarios.listaClientes()) {
            todosLosPosts.addAll(usuario.getPosts());
        }
        model.addAttribute("posts", todosLosPosts);
        return "posts";
    }

    @PostMapping("/like/{id}")
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
    @PostMapping("/repost/{id}")
    public String repostPost(@PathVariable int id){
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
}

