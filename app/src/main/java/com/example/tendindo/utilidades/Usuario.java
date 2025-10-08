package com.example.tendindo.utilidades;

import java.io.Serializable;

public class Usuario implements Serializable {

    public String nombre;
    public String correo;
    public String cedula;
    public String telefono;
    public String departamento;
    public String contrasenha;
    public String estado;

    public Usuario(String nombre, String correo, String cedula, String telefono, String departamento, String contrasenha) {
        this.nombre = nombre;
        this.correo = correo;
        this.cedula = cedula;
        this.telefono = telefono;
        this.departamento = departamento;
        this.contrasenha = contrasenha;
        this.estado = "Sin ninguna tarea asignada";
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getContrasenha() {
        return contrasenha;
    }

    public void setContrasenha(String contrasenha) {
        this.contrasenha = contrasenha;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                ", correo='" + correo + '\'' +
                ", cedula='" + cedula + '\'' +
                ", telefono='" + telefono + '\'' +
                ", departamento='" + departamento + '\'' +
                ", contrasenha='" + contrasenha + '\'' +
                '}';
    }
}
