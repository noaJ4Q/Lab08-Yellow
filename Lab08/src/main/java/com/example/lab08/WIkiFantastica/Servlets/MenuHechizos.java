package com.example.lab08.WIkiFantastica.Servlets;

import com.example.lab08.WIkiFantastica.Beans.*;
import com.example.lab08.WIkiFantastica.Daos.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "MenuHechizos", value = "/MenuHechizos")
public class MenuHechizos extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action") ==null? "listar": request.getParameter("action");
        DaoHechizo daoHechizo = new DaoHechizo();
        DaoElemento daoElemento = new DaoElemento();
        RequestDispatcher requestDispatcher;
        String idHechizoStr;
        int idHechizo;
        Hechizo hechizo;

        switch (action){
            case "listar":
                request.setAttribute("ListaEnemigos",daoHechizo.obtenerListaHechizos());
                requestDispatcher = request.getRequestDispatcher("MenuEnemigos.jsp");
                requestDispatcher.forward(request,response);
                break;

            case "crear":
                request.setAttribute("ListaElemento",daoElemento.obtenerListaElementos());
                request.setAttribute("listaHechizoBase", daoHechizo.obtenerListaHechizos());
                requestDispatcher = request.getRequestDispatcher("AnadirHechizo.jsp");
                requestDispatcher.forward(request,response);
                break;

            case "editar":
                idHechizoStr = request.getParameter("idHechizo");
                idHechizo = Integer.parseInt(idHechizoStr);
                hechizo = daoHechizo.buscarHechizo(idHechizo);


                if(hechizo==null){
                    response.sendRedirect(request.getContextPath()+"/MenuHechizos");
                }else{
                    request.setAttribute("Hechizo",hechizo);
                    request.setAttribute("listaElemento", daoElemento.obtenerListaElementos());
                    requestDispatcher= request.getRequestDispatcher("EditarHechizo.jsp");
                    requestDispatcher.forward(request,response);
                }
             break;

            case "borrar":
                idHechizoStr = request.getParameter("idHechizo");
                idHechizo = Integer.parseInt(idHechizoStr);
                daoHechizo.borrarHechizo(idHechizo);
                response.sendRedirect(request.getContextPath()+"/MenuHechizos");
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        DaoEnemigo daoEnemigo = new DaoEnemigo();
        DaoInventario daoInventario = new DaoInventario();
        String mensajeError;
        Hechizo hechizo;

        switch (action) {
            case "guardar": //para Guardar el Enemigo creado
                mensajeError = "Error: Dato(s) invalido(s)";
                hechizo = new Hechizo();
                hechizo.setNombreHechizo(request.getParameter("nombre"));

                Elemento elemento = new Elemento();
                String idElementoStr = request.getParameter("elementoHechizo");
                int idElementoHechizo = Integer.parseInt(idElementoStr);
                elemento.setIdElemento(idElementoHechizo);

                hechizo.setPotencia(Integer.parseInt(request.getParameter("potencia")));
                hechizo.setPrecision(Integer.parseInt(request.getParameter("precision")));

                Hechizo hechizoBase = new Hechizo();
                String idHechizoBaseStr = request.getParameter("hechizoBase");
                int idHechizoBase = Integer.parseInt(idHechizoBaseStr);
                hechizoBase.setIdHechizo(idHechizoBase);

                hechizo.setNivelAprendizaje(Integer.parseInt(request.getParameter("nivelAprendizaje")));

                response.sendRedirect(request.getContextPath() + "/MenuEnemigos");

                break;

        }
    }
}
