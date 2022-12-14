package com.example.lab08.WIkiFantastica.Servlets;

import com.example.lab08.WIkiFantastica.Beans.Clase;
import com.example.lab08.WIkiFantastica.Beans.Enemigo;
import com.example.lab08.WIkiFantastica.Beans.Genero;
import com.example.lab08.WIkiFantastica.Beans.Objeto;
import com.example.lab08.WIkiFantastica.Daos.DaoClase;
import com.example.lab08.WIkiFantastica.Daos.DaoEnemigo;
import com.example.lab08.WIkiFantastica.Daos.DaoGenero;
import com.example.lab08.WIkiFantastica.Daos.DaoInventario;
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
                request.setAttribute("ListaEnemigos",daoEnemigo.obtenerListaEnemigos());
                vista = request.getRequestDispatcher("MenuEnemigos.jsp");
                vista.forward(request,response);
                break;

            case "crear":
                DaoInventario daoInventario = new DaoInventario();
                request.setAttribute("ListaClases",daoClase.obtenerListaClases());
                request.setAttribute("ListaGeneros",daoGenero.obtenerListaGeneros());
                request.setAttribute("ListaObjetos",daoInventario.obtenerlistaObjetos());
                vista = request.getRequestDispatcher("AñadirEnemigo.jsp");
                vista.forward(request,response);
                break;

            case "editar":
                String idEnemigoStr = request.getParameter("id");
                int idEnemigo = Integer.parseInt(idEnemigoStr);
                Enemigo enemigo = daoEnemigo.buscarEnemigo(idEnemigo);
                DaoInventario daoInventario1 = new DaoInventario();

                if(enemigo==null){
                    response.sendRedirect(request.getContextPath()+"/MenuEnemigos");
                }else{
                    request.setAttribute("Enemigo",enemigo);
                    request.setAttribute("ListaClases",daoClase.obtenerListaClases());
                    request.setAttribute("ListaGeneros",daoGenero.obtenerListaGeneros());
                    request.setAttribute("ListaObjetos",daoInventario1.obtenerlistaObjetos());
                    vista= request.getRequestDispatcher("EditarEnemigo2.jsp");
                    vista.forward(request,response);
                    //response.sendRedirect(request.getContextPath()+"/MenuEnemigos");
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
        DaoClase daoClase = new DaoClase();
        DaoGenero daoGenero = new DaoGenero();

        switch (action) {
            case "guardar": //para Guardar el Enemigo creado
                String nombre = request.getParameter("nombre");
                Clase clase = new Clase();
                String idClaseStr = request.getParameter("claseEnemigo");
                int idClaseEnemigo = Integer.parseInt(idClaseStr);
                clase.setIdClase(idClaseEnemigo);
                for( Clase c : daoClase.obtenerListaClases()){
                    if(c.getIdClase() == idClaseEnemigo){
                        clase.setNombreClase(c.getNombreClase());
                    }
                }
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
                for (Genero gen: daoGenero.obtenerListaGeneros()){
                    if(gen.getIdGenero() == idGenero ){
                        genero.setNombreGenero(gen.getNombreGenero());
                    }
                }

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
                //daoEnemigo.agregarDropeo(10,idObjeto,probabilidad);

                response.sendRedirect(request.getContextPath() + "/MenuEnemigos");

                //daoEnemigo.agregarDropeo(enemigo);

                break;

            case "actualizar": //para Actualizar los datos del Enemigo seleccionado
                String idEnemigoStr = request.getParameter("idEnemigo");
                int idEnemigo = Integer.parseInt(idEnemigoStr);
                String nombre1 = request.getParameter("nombre");
                Clase clase1 = new Clase();
                String idClase1Str = request.getParameter("claseEnemigo");
                int idClaseEnemigo1 = Integer.parseInt(idClase1Str);
                clase1.setIdClase(idClaseEnemigo1);
                String ataque1Str = request.getParameter("ataque");
                int ataque1 = Integer.parseInt(ataque1Str);
                String experiencia1Str = request.getParameter("experiencia");
                int experiencia1 = Integer.parseInt(experiencia1Str);
                Objeto objeto1 = new Objeto();
                String idObjeto1Str = request.getParameter("objetoEnemigo");
                int idObjeto1 = Integer.parseInt(idObjeto1Str);
                objeto1.setIdObjeto(idObjeto1);
                for (Objeto obj1: daoInventario.obtenerlistaObjetos()){
                    if(obj1.getIdObjeto() == idObjeto1 ){
                        objeto1.setNombreObjeto(obj1.getNombreObjeto());
                    }
                }
                String probabilidad1Str = request.getParameter("probabilidad");
                float probabilidad1 = Float.parseFloat(probabilidad1Str);
                Genero genero1 = new Genero();
                String idGenero1 = request.getParameter("generoEnemigo");
                genero1.setIdGenero(idGenero1);

                //crear nuevo enemigo
                Enemigo enemigo1 = new Enemigo();
                enemigo1.setIdEnemigo(idEnemigo);
                enemigo1.setNombreEnemigo(nombre1);
                enemigo1.setClase(clase1);
                enemigo1.setAtaque(ataque1);
                enemigo1.setExperiencia(experiencia1);
                enemigo1.setObjeto(objeto1);
                enemigo1.setProbabilidad(probabilidad1);
                enemigo1.setGenero(genero1);
                daoEnemigo.actualizarEnemigo(enemigo1);
                //daoEnemigo.actualizarDropeo(enemigo1);

                response.sendRedirect(request.getContextPath() + "/MenuEnemigos");

                break;
        }
    }
}
