package com.example.lab08.WIkiFantastica.Servlets;

import java.io.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "servletInicio", value = "")
public class ServletInicio extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

        try{
            RequestDispatcher vista = request.getRequestDispatcher("index.jsp");
            vista.forward(request, response);
        }catch(ServletException e){

        }
    }


}