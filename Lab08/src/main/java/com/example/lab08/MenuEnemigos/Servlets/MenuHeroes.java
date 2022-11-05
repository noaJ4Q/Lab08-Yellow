package com.example.lab08.MenuEnemigos.Servlets;

import com.example.lab08.MenuEnemigos.Beans.Heroe;
import com.example.lab08.MenuEnemigos.Daos.DaoHeroe;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MenuHeroes", value = "/MenuHeroes")
public class MenuHeroes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null? "listar" : request.getParameter("action");
        DaoHeroe daoHeroe = new DaoHeroe();
        RequestDispatcher vista;

        switch (action){
            case "listar":
                ArrayList<Heroe> listaHeroes = daoHeroe.obtenerListaHeroes();

                request.setAttribute("listaHeroes", listaHeroes);
                vista = request.getRequestDispatcher("MenuHeroes.jsp");
                vista.forward(request, response);

                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
