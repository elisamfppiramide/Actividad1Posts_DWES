
package com.cpifppiramide.demo.clases;

import java.util.Date;

public class Post {
    private Integer id;
    private String texto;
    private Date fecha;
    private int likes;
    private int repost;
    private Usuario usuario;

    public Post(Integer id, String texto, Date fecha, int likes, int repost, Usuario usuario) {
        this.id = id;
        this.texto = texto;
        this.fecha = fecha;
        this.likes = likes;
        this.repost = repost;
        this.usuario = usuario;
    }

    public Post(String texto, Usuario usuario){
        this.texto = texto;
        this.usuario = usuario;
    }

    public Usuario getUsuario() {
        return usuario;
    }
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    public Integer getId() {
        return id;
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
                "Texto: " + texto + "\n" +
                "Fecha: " + fecha + "\n" +
                "Likes: " + likes + "\n" +
                "Repost: " + repost;
    }
}
