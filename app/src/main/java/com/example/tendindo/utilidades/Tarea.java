package com.example.tendindo.utilidades;

import java.io.Serializable;

public class Tarea implements Serializable {

    String idTarea;
    String nombre;
    String descripcion;
    String recursos;
    String fechaInicio;
    String tiempoEstimado;
    String estado;
    String cantidadStorypoints;
    String cedulaResponsable;
    String idProyecto;

    public Tarea(String idTarea, String nombre, String descripcion, String recursos, String fechaInicio, String tiempoEstimado, String estado, String cantidadStorypoints, String cedulaResponsable, String idProyecto) {
        this.idTarea = idTarea;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.recursos = recursos;
        this.fechaInicio = fechaInicio;//.substring(0,10);
        this.tiempoEstimado = tiempoEstimado;
        this.estado = estado;
        this.cantidadStorypoints = cantidadStorypoints;
        this.cedulaResponsable = cedulaResponsable;
        this.idProyecto = idProyecto;
    }

    @Override
    public String toString() {
        return "Tarea{" +
                "idTarea='" + idTarea + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", recursos='" + recursos + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", tiempoEstimado='" + tiempoEstimado + '\'' +
                ", estado='" + estado + '\'' +
                ", cantidadStorypoints='" + cantidadStorypoints + '\'' +
                ", cedulaResponsable='" + cedulaResponsable + '\'' +
                ", idProyecto='" + idProyecto + '\'' +
                '}';
    }

    public String getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRecursos() {
        return recursos;
    }

    public void setRecursos(String recursos) {
        this.recursos = recursos;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getTiempoEstimado() {
        return tiempoEstimado;
    }

    public void setTiempoEstimado(String tiempoEstimado) {
        this.tiempoEstimado = tiempoEstimado;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCantidadStorypoints() {
        return cantidadStorypoints;
    }

    public void setCantidadStorypoints(String cantidadStorypoints) {
        this.cantidadStorypoints = cantidadStorypoints;
    }

    public String getCedulaResponsable() {
        return cedulaResponsable;
    }

    public void setCedulaResponsable(String cedulaResponsable) {
        this.cedulaResponsable = cedulaResponsable;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }
}
