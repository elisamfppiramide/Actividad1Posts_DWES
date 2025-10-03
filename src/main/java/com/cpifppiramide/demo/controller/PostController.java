package com.cpifppiramide.demo.controller;

import com.cpifppiramide.demo.clases.Post;
import com.cpifppiramide.demo.dao.DAOFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;

@Controller
public class PostController {

    @GetMapping("/posts")
    public String mostrarPosts(){
        DAOFactory daoFactory = DAOFactory.getInstance();
        List<Post> posts = daoFactory.getDaoPosts().listaPosts();
        System.out.println(posts);
        return "posts";
    }


}
