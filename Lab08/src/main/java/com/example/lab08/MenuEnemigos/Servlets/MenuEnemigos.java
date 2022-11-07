package com.example.lab08.MenuEnemigos.Servlets;

import com.example.lab08.MenuEnemigos.Beans.Clase;
import com.example.lab08.MenuEnemigos.Beans.Enemigo;
import com.example.lab08.MenuEnemigos.Beans.Genero;
import com.example.lab08.MenuEnemigos.Beans.Objeto;
import com.example.lab08.MenuEnemigos.Daos.DaoClase;
import com.example.lab08.MenuEnemigos.Daos.DaoEnemigo;
import com.example.lab08.MenuEnemigos.Daos.DaoGenero;
import com.example.lab08.MenuEnemigos.Daos.DaoInventario;
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

            case "editar":
                String idEnemigoStr = request.getParameter("id");
                int idEnemigo = Integer.parseInt(idEnemigoStr);
                Enemigo enemigo = daoEnemigo.buscarEnemigo(idEnemigo);
                if(enemigo==null){
                    response.sendRedirect(request.getContextPath()+"/MenuEnemigos");
                }else{
                    request.setAttribute("Enemigo",enemigo);
                    vista= request.getRequestDispatcher("EditarEnemigo.jsp");
                    vista.forward(request,response);
                }
             break;

            case "borrar":
                String idEnemigo1Str = request.getParameter("id");
                int idEnemigo1 = Integer.parseInt(idEnemigo1Str);
                //borrar es void , entonces:
                daoEnemigo.borrarEnemigo(idEnemigo1);

                response.sendRedirect(request.getContextPath()+"/MenuEnemigos");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        DaoEnemigo daoEnemigo = new DaoEnemigo();
        DaoInventario daoInventario = new DaoInventario();

        switch (action) {
            case "guardar": //para Guardar el Enemigo creado
                String nombre = request.getParameter("nombre");
                Clase clase = new Clase();
                String idClaseStr = request.getParameter("claseEnemigo");
                int idClaseEnemigo = Integer.parseInt(idClaseStr);
                clase.setIdClase(idClaseEnemigo);
                String ataqueStr = request.getParameter("ataque");
                int ataque = Integer.parseInt(ataqueStr);
                String experienciaStr = request.getParameter("experiencia");
                int experiencia = Integer.parseInt(experienciaStr);
                Objeto objeto = new Objeto();
                String idObjetoStr = request.getParameter("objetoEnemigo");
                int idObjeto = Integer.parseInt(idObjetoStr);
                objeto.setIdObjeto(idObjeto);
                for (Objeto obj: daoInventario.obtenerlistaObjetos()){
                    if(obj.getIdObjeto() == idObjeto ){
                        objeto.setNombreObjeto(obj.getNombreObjeto());
                    }
                }
                String probabilidadStr = request.getParameter("probabilidad");
                float probabilidad = Float.parseFloat(probabilidadStr);
                Genero genero = new Genero();
                String idGenero = request.getParameter("generoEnemigo");
                genero.setIdGenero(idGenero);

                //crear nuevo enemigo
                Enemigo enemigo = new Enemigo();
                enemigo.setNombreEnemigo(nombre);
                enemigo.setClase(clase);
                enemigo.setAtaque(ataque);
                enemigo.setExperiencia(experiencia);
                enemigo.setObjeto(objeto);
                enemigo.setProbabilidad(probabilidad);
                enemigo.setGenero(genero);
                daoEnemigo.agregarEnemigo(enemigo);

                response.sendRedirect(request.getContextPath() + "/MenuEnemigos");

                break;

            case "actualizar": //para Actualizar los datos del Enemigo seleccionado
                String nombre1 = request.getParameter("nombre");
                Clase clase1 = new Clase();
                String idClase1Str = request.getParameter("claseEnemigo");
                int idClaseEnemigo1 = Integer.parseInt(idClase1Str);
                clase1.setIdClase(idClaseEnemigo1);
                String ataque1Str = request.getParameter("ataque");
                int ataque1 = Integer.parseInt(ataque1Str);
                String experiencia1Str = request.getParameter("experiencia");
                int experiencia1 = Integer.parseInt(experiencia1Str);
                //String probabilidad1Str = request.getParameter("probabilidad");
                //float probabilidad1 = Float.parseFloat(probabilidad1Str);
                Genero genero1 = new Genero();
                String idGenero1 = request.getParameter("generoEnemigo");
                genero1.setIdGenero(idGenero1);

                //crear nuevo enemigo
                Enemigo enemigo1 = new Enemigo();
                enemigo1.setNombreEnemigo(nombre1);
                enemigo1.setClase(clase1);
                enemigo1.setAtaque(ataque1);
                enemigo1.setExperiencia(experiencia1);
                enemigo1.setGenero(genero1);
                daoEnemigo.agregarEnemigo(enemigo1);

                response.sendRedirect(request.getContextPath() + "/MenuEnemigos");

                break;
        }
    }
}
