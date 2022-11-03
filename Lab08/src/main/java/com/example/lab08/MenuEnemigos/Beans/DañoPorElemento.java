package com.example.lab08.MenuEnemigos.Beans;

public class DañoPorElemento {
    private Clase clase;
    private Elemento elemento;
    private float dañoRecibido;

    public Clase getClase() {
        return clase;
    }

    public void setClase(Clase clase) {
        this.clase = clase;
    }

    public Elemento getElemento() {
        return elemento;
    }

    public void setElemento(Elemento elemento) {
        this.elemento = elemento;
    }

    public float getDañoRecibido() {
        return dañoRecibido;
    }

    public void setDañoRecibido(float dañoRecibido) {
        this.dañoRecibido = dañoRecibido;
    }
}
