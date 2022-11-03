package com.example.lab08.MenuEnemigos.Beans;

public class Dropeo {
    private Objeto objeto;
    private Enemigo enemigo;
    private float probabilidad;

    public Objeto getObjeto() {
        return objeto;
    }

    public void setObjeto(Objeto objeto) {
        this.objeto = objeto;
    }

    public Enemigo getEnemigo() {
        return enemigo;
    }

    public void setEnemigo(Enemigo enemigo) {
        this.enemigo = enemigo;
    }

    public float getProbabilidad() {
        return probabilidad;
    }

    public void setProbabilidad(float probabilidad) {
        this.probabilidad = probabilidad;
    }
}
