package com.example.lab08.MenuEnemigos.Servlets;

import com.example.lab08.MenuEnemigos.Beans.Enemigo;
import com.example.lab08.MenuEnemigos.Daos.DaoEnemigo;
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
        RequestDispatcher vista;

        switch (action){
            case "listar":
                ArrayList<Enemigo> listaEnemigos = daoEnemigo.obtenerListaEnemigos();
                request.setAttribute("ListaEnemigos",listaEnemigos);
                vista = request.getRequestDispatcher("MenuEnemigos.jsp");
                vista.forward(request,response);
                break;

            case "crear":

                vista = request.getRequestDispatcher("");

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
