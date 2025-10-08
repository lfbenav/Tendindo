package com.example.tendindo.utilidades;

import java.io.Serializable;

public class Mensaje implements Serializable {

    String mensaje;
    String nombreColaborador;
    String idProyecto;

    public Mensaje(String mensaje, String nombreColaborador, String idProyecto) {
        this.mensaje = mensaje;
        this.nombreColaborador = nombreColaborador;
        this.idProyecto = idProyecto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getNombreColaborador() {
        return nombreColaborador;
    }

    public void setNombreColaborador(String nombreColaborador) {
        this.nombreColaborador = nombreColaborador;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    @Override
    public String toString() {
        return "Mensaje{" +
                "mensaje='" + mensaje + '\'' +
                ", nombreColaborador='" + nombreColaborador + '\'' +
                ", idProyecto='" + idProyecto + '\'' +
                '}';
    }
}
