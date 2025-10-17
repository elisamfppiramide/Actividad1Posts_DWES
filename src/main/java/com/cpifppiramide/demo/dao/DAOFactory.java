package com.cpifppiramide.demo.dao;

import com.cpifppiramide.demo.dao.posts.DAOPosts;
import com.cpifppiramide.demo.dao.posts.DAOPostsMySQL;
import com.cpifppiramide.demo.dao.posts.DAOPostsRAM;
import com.cpifppiramide.demo.dao.usuarios.DAOUsuarios;
import com.cpifppiramide.demo.dao.usuarios.DAOUsuariosMySQL;
import com.cpifppiramide.demo.dao.usuarios.DAOUsuariosRAM;

public class DAOFactory {

    private static DAOFactory daoFactory;
    private DAOUsuarios daoUsuarios;
    private DAOPosts daoPosts;
    private DAOFactory(){}


    public static DAOFactory getInstance(){
        if(daoFactory == null){
            daoFactory = new DAOFactory();
        }
        return daoFactory;
    }

    public DAOUsuarios getDaoUsuarios(){
        if(this.daoUsuarios == null){
            this.daoUsuarios = new DAOUsuariosMySQL();
        }
        return this.daoUsuarios;
    }

    public DAOPosts getDaoPosts(){
        if(this.daoPosts == null){
            this.daoPosts = new DAOPostsMySQL();
        }
        return this.daoPosts;
    }

}
