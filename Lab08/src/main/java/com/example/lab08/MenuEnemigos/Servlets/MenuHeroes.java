package com.example.lab08.MenuEnemigos.Servlets;

import com.example.lab08.MenuEnemigos.Beans.Genero;
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

            case "crear":

                ArrayList<Heroe> listaParejasDisponibles = daoHeroe.obtenerParejasDisponibles();

                request.setAttribute("listaParejas", listaParejasDisponibles);
                vista = request.getRequestDispatcher("AñadirHeroe.jsp");
                vista.forward(request, response);

                break;

            case "borrar":
                String idHeroe = request.getParameter("id");
                daoHeroe.borrarHeroe(idHeroe);

                response.sendRedirect(request.getContextPath()+"/MenuHeroes");

                break;
        }


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        DaoHeroe daoHeroe = new DaoHeroe();

        switch (action){
            case "guardar":

                Heroe heroe = new Heroe();
                heroe.setNombre(request.getParameter("nombre"));
                heroe.setEdad(request.getParameter("edad"));

                Genero genero = new Genero();
                genero.setIdGenero(request.getParameter("genero"));
                heroe.setGenero(genero);

                heroe.setClase(request.getParameter("clase"));
                int nivel = Integer.parseInt(request.getParameter("nivel"));
                heroe.setNivel(nivel);
                heroe.setAtaque(Integer.parseInt(request.getParameter("ataque")));

                float experiencia = 0;
                if (nivel>0 && nivel <=15){
                    experiencia = (nivel*nivel*nivel)*(24+((float)nivel+1)/3)/50;
                } else if (nivel>=16 && nivel<=35) {
                    experiencia = (nivel*nivel*nivel)*(14+(float)nivel)/50;
                } else if (nivel >= 36 && nivel <= 100) {
                    experiencia = (nivel*nivel*nivel)*(32+((float)nivel/2))/50;
                }

                heroe.setExperiencia(experiencia);

                Heroe pareja = new Heroe();
                pareja.setIdHeroe(Integer.parseInt(request.getParameter("idPareja")));
                heroe.setPareja(pareja);

                int idHeroeGuardado = daoHeroe.guardarHeroe(heroe);

                if (idHeroeGuardado != 0){ // TIENE PAREJA

                }

                break;
        }

    }
}
