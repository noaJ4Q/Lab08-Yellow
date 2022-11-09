package com.example.lab08.MenuEnemigos.Beans;

public class Hechizo {
    private int idHechizo;
    private String nombreHechizo;
    private int nivelAprendizaje;
    private int potencia;
    private int precision;
    private Elemento elementoHechizo;
    private Hechizo hechizoBase;

    public int getIdHechizo() {
        return idHechizo;
    }

    public void setIdHechizo(int idHechizo) {
        this.idHechizo = idHechizo;
    }

    public String getNombreHechizo() {
        return nombreHechizo;
    }

    public void setNombreHechizo(String nombreHechizo) {
        this.nombreHechizo = nombreHechizo;
    }

    public int getNivelAprendizaje() {
        return nivelAprendizaje;
    }

    public void setNivelAprendizaje(int nivelAprendizaje) {
        this.nivelAprendizaje = nivelAprendizaje;
    }

    public int getPotencia() {
        return potencia;
    }

    public void setPotencia(int potencia) {
        this.potencia = potencia;
    }

    public int getPrecision() {
        return precision;
    }

    public void setPrecision(int precision) {
        this.precision = precision;
    }

    public Elemento getElementoHechizo() {
        return elementoHechizo;
    }

    public void setElementoHechizo(Elemento elementoHechizo) {
        this.elementoHechizo = elementoHechizo;
    }

    public Hechizo getHechizoBase() {
        return hechizoBase;
    }

    public void setHechizoBase(Hechizo hechizoBase) {
        this.hechizoBase = hechizoBase;
    }
}
