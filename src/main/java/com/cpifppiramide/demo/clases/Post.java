package com.cpifppiramide.demo.clases;

import java.util.Date;

public class Post {
    private int id;
    private Usuario usuario;
    private String texto;
    private Date fecha;
    private int likes;
    private int repost;

    public Post(int id, Usuario usuario, String texto, Date fecha, int likes, int repost) {
        this.id = id;
        this.usuario = usuario;
        this.texto = texto;
        this.fecha = fecha;
        this.likes = likes;
        this.repost = repost;
    }

    public int getId() {
        return id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public String getTexto() {
        return texto;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getRepost() {
        return repost;
    }

    public void setRepost(int repost) {
        this.repost = repost;
    }

    @Override
    public String toString() {
        return  "ID: " + id + "\n" +
                "Usuario: " + usuario + "\n" +
                "Texto: " + texto + "\n" +
                "Fecha: " + fecha + "\n" +
                "Likes: " + likes + "\n" +
                "Repost: " + repost;
    }
}
