package com.example.tendindo.utilidades;

import java.io.Serializable;

public class ListTareaElement implements Serializable {

    public String nombre;
    public Tarea tarea;

    public ListTareaElement(String nombre, Tarea tarea) {
        this.nombre = nombre;
        this.tarea = tarea;
    }

    @Override
    public String toString() {
        return "ListTareaElement{" +
                "nombre='" + nombre + '\'' +
                ", tarea=" + tarea +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Tarea getTarea() {
        return tarea;
    }

    public void setTarea(Tarea tarea) {
        this.tarea = tarea;
    }
}
