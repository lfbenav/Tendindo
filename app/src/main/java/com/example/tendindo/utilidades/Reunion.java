package com.example.tendindo.utilidades;

import java.io.Serializable;

public class Reunion implements Serializable {

    public String tema;
    public String medio;
    public String fecha;
    public String hora;
    public String colaboradores;

    public Reunion(String tema, String medio, String fecha, String hora, String colaboradores) {
        this.tema = tema;
        this.medio = medio;
        this.fecha = fecha;
        this.hora = hora;

        String[] splitStr = colaboradores.split("\\s+");
        String splitColaboradores = "";
        for(String str : splitStr){
            splitColaboradores = splitColaboradores + str + "\n";
        }
        this.colaboradores = splitColaboradores;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getMedio() {
        return medio;
    }

    public void setMedio(String medio) {
        this.medio = medio;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(String colaboradores) {
        this.colaboradores = colaboradores;
    }
}
