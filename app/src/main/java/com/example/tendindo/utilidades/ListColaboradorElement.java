package com.example.tendindo.utilidades;

import java.io.Serializable;

public class ListColaboradorElement implements Serializable {

    public String nombre;
    public String estado;
    public Usuario usuario;

    public ListColaboradorElement(String nombre, String estado, Usuario usuario) {
        this.nombre = nombre;
        this.estado = estado;
        this.usuario = usuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "ListColaboradorElement{" +
                "nombre='" + nombre + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}
