package com.cpifppiramide.demo.DemoController;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.clases.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class PostController {
    private List<Post> posts = new ArrayList<>();

    public PostController(){
        Usuario user1 = new Usuario("Carlos", "carlos123");
        Usuario user2 = new Usuario("Ana", "ana_g");
        Usuario user3 = new Usuario("Luis", "luis_dev");

        // Creamos posts
        Post post1 = new Post(1, user1, "¡Hola a todos! Este es mi primer post 🚀", new Date(), 10, 2);
        Post post2 = new Post(2, user2, "Hoy aprendí sobre programación en Java 💻", new Date(), 25, 5);
        Post post3 = new Post(3, user3, "¿Alguien sabe cómo usar streams en Java?", new Date(), 15, 3);
        Post post4 = new Post(4, user1, "Practicando POO en Java, está genial 😎", new Date(), 40, 8);


        // Añadimos los posts a la lista
        posts.add(post1);
        posts.add(post2);
        posts.add(post3);
        posts.add(post4);
    }

    @RequestMapping("/listaPost")
    public String listarPost(Model model){
        model.addAttribute("posts", this.posts);
        return "posts";
    }

    @PostMapping("/like/{id}")
    public String likePost(@PathVariable int id){
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
        for(Post post : posts){
            if(post.getId() == id){
                post.setLikes(post.getLikes() + 1);
                break;
            }
        }
        return "redirect:/posts";
    }
}
