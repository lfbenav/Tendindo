package com.example.tendindo.utilidades;

import java.io.Serializable;

public class ListProyectoElement implements Serializable {

    public String nombre;
    public String subtitulo;
    public String estado;
    public Proyecto proyecto;

    public ListProyectoElement(Proyecto proyecto) {

        String estadoTemp = "";
        switch (proyecto.estado){
            case "0": estadoTemp = "En proceso";
                break;

            case "1": estadoTemp = "Pausado";
                break;

            case "2": estadoTemp = "Cancelado";
                break;
        }
        proyecto.estado = estadoTemp;

        this.nombre = proyecto.nombre_proyecto;
        this.subtitulo = proyecto.descripcion;
        this.estado = proyecto.estado;
        this.proyecto = proyecto;
    }

    @Override
    public String toString() {
        return "ListProyectoElement{" +
                "nombre='" + nombre + '\'' +
                ", subtitulo='" + subtitulo + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getSubtitulo() {
        return subtitulo;
    }

    public void setSubtitulo(String subtitulo) {
        this.subtitulo = subtitulo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
