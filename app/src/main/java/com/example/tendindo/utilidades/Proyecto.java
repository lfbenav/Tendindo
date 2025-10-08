package com.example.tendindo.utilidades;

import java.io.Serializable;

public class Proyecto implements Serializable {

    public String idProyecto;
    public String nombre_proyecto;
    public String descripcion;
    public String fechaInicio;
    public String estado;
    public String ced_responsable;
    public String presupuesto;
    public String recursosNecesarios;

    public Proyecto(String idProyecto, String nombre_proyecto, String descripcion, String fechaInicio, String estado, String ced_responsable, String presupuesto, String recursosNecesarios) {
        this.idProyecto = idProyecto;
        this.nombre_proyecto = nombre_proyecto;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;//.substring(0,10);
        this.estado = estado;
        this.ced_responsable = ced_responsable;
        this.presupuesto = presupuesto;
        this.recursosNecesarios = recursosNecesarios;

    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public String getNombre_proyecto() {
        return nombre_proyecto;
    }

    public void setNombre_proyecto(String nombre_proyecto) {
        this.nombre_proyecto = nombre_proyecto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getCed_responsable() {
        return ced_responsable;
    }

    public void setCed_responsable(String ced_responsable) {
        this.ced_responsable = ced_responsable;
    }

    public String getPresupuesto() {
        return presupuesto;
    }

    public void setPresupuesto(String presupuesto) {
        this.presupuesto = presupuesto;
    }

    public String getRecursosNecesarios() {
        return recursosNecesarios;
    }

    public void setRecursosNecesarios(String recursosNecesarios) {
        this.recursosNecesarios = recursosNecesarios;
    }

    @Override
    public String toString() {
        return "Proyecto{" +
                "idProyecto='" + idProyecto + '\'' +
                ", nombre_proyecto='" + nombre_proyecto + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaInicio='" + fechaInicio + '\'' +
                ", estado='" + estado + '\'' +
                ", ced_responsable='" + ced_responsable + '\'' +
                ", presupuesto='" + presupuesto + '\'' +
                ", recursosNecesarios='" + recursosNecesarios + '\'' +
                '}';
    }
}
