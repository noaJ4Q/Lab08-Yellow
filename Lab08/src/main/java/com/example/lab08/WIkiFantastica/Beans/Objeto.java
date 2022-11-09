package com.example.lab08.WIkiFantastica.Beans;

public class Objeto {
    private int idObjeto;
    private String nombreObjeto;
    private String efectoUso;
    private float peso;

    public int getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(int idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    public String getEfectoUso() {
        return efectoUso;
    }

    public void setEfectoUso(String efectoUso) {
        this.efectoUso = efectoUso;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }
}
