package com.example.lab08.MenuEnemigos.Servlets;

import com.example.lab08.MenuEnemigos.Beans.Clase;
import com.example.lab08.MenuEnemigos.Beans.Enemigo;
import com.example.lab08.MenuEnemigos.Beans.Genero;
import com.example.lab08.MenuEnemigos.Daos.DaoClase;
import com.example.lab08.MenuEnemigos.Daos.DaoEnemigo;
import com.example.lab08.MenuEnemigos.Daos.DaoGenero;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MenuEnemigos", value = "/MenuEnemigos")
public class MenuEnemigos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") ==null? "listar": request.getParameter("action");
        DaoEnemigo daoEnemigo = new DaoEnemigo();
        DaoClase daoClase = new DaoClase();
        DaoGenero daoGenero = new DaoGenero();
        RequestDispatcher vista;

        switch (action){
            case "listar":
                ArrayList<Enemigo> listaEnemigos = daoEnemigo.obtenerListaEnemigos();
                request.setAttribute("ListaEnemigos",listaEnemigos);
                vista = request.getRequestDispatcher("MenuEnemigos.jsp");
                vista.forward(request,response);
                break;

            case "crear":
                ArrayList<Clase> listaClases = daoClase.obtenerListaClases();
                ArrayList<Genero> listaGeneros = daoGenero.obtenerListaGeneros();
                request.setAttribute("ListaClases",listaClases);
                request.setAttribute("ListaGeneros",listaGeneros);
                vista = request.getRequestDispatcher("AñadirEnemigo.jsp");
                vista.forward(request,response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        DaoEnemigo daoEnemigo = new DaoEnemigo();

        switch (action){
            case "guardar":
                String nombre = request.getParameter("nombre");
                Clase clase = new Clase();
                String idClaseStr = request.getParameter("claseEnemigo");
                int idClaseEnemigo = Integer.parseInt(idClaseStr);
                clase.setIdClase(idClaseEnemigo);
                String ataqueStr = request.getParameter("ataque");
                int ataque = Integer.parseInt("ataqueStr");
                String experienciaStr = request.getParameter("experiencia");
                int experiencia = Integer.parseInt("experienciaStr");
                String probabilidadStr = request.getParameter("probabilidad");
                float probabilidad = Float.parseFloat("probabilidadStr");
                //como guardar el dato de probabilidad a la tabla de Dropeo???
                Genero genero = new Genero();
                String idGenero = request.getParameter("generoEnemigo");
                genero.setIdGenero(idGenero);

                //crear nuevo enemigo
                Enemigo enemigo = new Enemigo();
                enemigo.setNombreEnemigo(nombre);
                enemigo.setClase(clase);
                enemigo.setAtaque(ataque);
                enemigo.setExperiencia(experiencia);
                enemigo.setGenero(genero);
                daoEnemigo.agregarEnemigo(enemigo);

                response.sendRedirect(request.getContextPath()+"/MenuEnemigos");

                break;
        }
    }
}
