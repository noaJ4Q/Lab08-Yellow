package com.example.lab08.MenuEnemigos.Servlets;

import java.io.*;
import java.rmi.ServerException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.sql.rowset.serial.SerialException;

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