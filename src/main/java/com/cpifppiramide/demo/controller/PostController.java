package com.cpifppiramide.demo.controller;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.clases.Usuario;
import com.cpifppiramide.demo.dao.DAOFactory;
import com.cpifppiramide.demo.dao.posts.DAOPosts;
import com.cpifppiramide.demo.dao.usuarios.DAOUsuarios;
import com.cpifppiramide.demo.dao.usuarios.DAOUsuariosRAM;
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
    public String addPost(@RequestParam String texto) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOPosts daoPosts = daoFactory.getDaoPosts();
        DAOUsuariosRAM daoUsuariosRAM = (DAOUsuariosRAM) daoFactory.getDaoUsuarios();

        Usuario usuario = daoUsuariosRAM.getUsuarioActual();
        int nuevoID = daoPosts.listaPosts().size() + 1;
        Post nuevoPost = new Post(nuevoID, texto, new Date(), 0, 0, usuario);
        daoPosts.add(nuevoPost, usuario);
        System.out.println("Nuevo post añadido por: " + usuario.getNombreUsuario());

        return "redirect:/posts";
    }


    @GetMapping("/posts/user")
    public String filtrarPorUsuario(@RequestParam String username, Model model) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        DAOPosts daoPosts = daoFactory.getDaoPosts();

        List<Post> filtrados = new ArrayList<>();
        for (Post post : daoPosts.listaPosts()) {
            Usuario user = post.getUsuario();

            if(user.getNombreUsuario().equalsIgnoreCase(username)){
                filtrados.add(post);
            }
        }
        model.addAttribute("posts", filtrados);
        return "posts";
    }

    @GetMapping("/posts/fecha")
    public String postsOrdenadosFecha(@RequestParam(defaultValue="asc") String orden, Model model) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        List<Post> posts = daoFactory.getDaoPosts().listaPosts();

        posts.sort(Comparator.comparing(Post::getFecha));
        if ("desc".equalsIgnoreCase(orden)) {
            Collections.reverse(posts);
        }
        model.addAttribute("posts", posts);
        return "posts";
    }

    @GetMapping("/posts/search")
    public String postsPorTexto(@RequestParam String q, Model model) {
        DAOFactory daoFactory = DAOFactory.getInstance();
        List<Post> posts = daoFactory.getDaoPosts().listaPosts();

        List<Post> filtrados = posts.stream()
                .filter(p -> p.getTexto().toLowerCase().contains(q.toLowerCase()))
                .toList();
        model.addAttribute("posts", filtrados);
        return "posts";
    }
}

