package com.cpifppiramide.demo.DemoController;

import org.springframework.web.bind.annotation.RequestMapping;

@org.springframework.stereotype.Controller
public class Controller {

    @RequestMapping("/index")
    String inicio(){
        return "index";
    }

    @RequestMapping("/registarse")
    String registro(){
        return "registro";
    }

    @RequestMapping("/inicioSesion")
    String inicioSesion(){
        return "inicioSesion";
    }


}
