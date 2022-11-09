package com.example.lab08.MenuEnemigos.Servlets;

import com.example.lab08.MenuEnemigos.Beans.Genero;
import com.example.lab08.MenuEnemigos.Beans.Heroe;
import com.example.lab08.MenuEnemigos.Beans.Inventario;
import com.example.lab08.MenuEnemigos.Beans.Objeto;
import com.example.lab08.MenuEnemigos.Daos.DaoHeroe;
import com.example.lab08.MenuEnemigos.Daos.DaoInventario;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "MenuHeroes", value = "/MenuHeroes")
public class MenuHeroes extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action") == null ? "listar" : request.getParameter("action");
        DaoHeroe daoHeroe = new DaoHeroe();
        DaoInventario daoInventario = new DaoInventario();
        Heroe heroe;
        RequestDispatcher vista;
        String idHeroe;
        String idObjeto;
        ArrayList<Heroe> listaParejasDisponibles;

        switch (action) {
            case "listar":
                ArrayList<Heroe> listaHeroes = daoHeroe.obtenerListaHeroes();

                request.setAttribute("listaHeroes", listaHeroes);
                vista = request.getRequestDispatcher("MenuHeroes.jsp");
                vista.forward(request, response);

                break;

            case "crear":

                listaParejasDisponibles = daoHeroe.obtenerParejasDisponibles(0);

                request.setAttribute("listaParejas", listaParejasDisponibles);
                vista = request.getRequestDispatcher("AñadirHeroe.jsp");
                vista.forward(request, response);

                break;

            case "inventario":

                idHeroe = request.getParameter("idHeroe");
                heroe = daoHeroe.buscarPorId(idHeroe);

                ArrayList<Inventario> listaInventario = daoInventario.obtenerInventario(idHeroe);
                request.setAttribute("listaInventario", listaInventario);
                request.setAttribute("idHeroe", idHeroe);
                request.setAttribute("nombre", heroe.getNombre());
                vista = request.getRequestDispatcher("InventarioHeroe.jsp");
                vista.forward(request, response);

                break;

            case "editar":
                idHeroe = request.getParameter("idHeroe");
                heroe = daoHeroe.buscarPorId(idHeroe);
                listaParejasDisponibles = daoHeroe.obtenerParejasDisponibles(heroe.getIdHeroe());

                request.setAttribute("heroeEditar", heroe);
                request.setAttribute("listaParejas", listaParejasDisponibles);

                vista = request.getRequestDispatcher("EditarHeroe.jsp");
                vista.forward(request, response);

                break;

            case "borrar":
                idHeroe = request.getParameter("idHeroe");
                Heroe pareja = daoHeroe.buscarPareja(Integer.parseInt(idHeroe));
                if (pareja != null) {
                    daoHeroe.actualizarPareja(pareja.getIdHeroe(), 0); // ELIMINAR REGISTRO SUYO EN PAREJA SI TIENE PAREJA
                }
                daoInventario.borrarInventario(Integer.parseInt(idHeroe)); // ELIMINAR INVENTARIO
                daoHeroe.borrarHeroe(idHeroe); // ELMINAR HEROE

                response.sendRedirect(request.getContextPath() + "/MenuHeroes");

                break;

            case "crearObjeto":

                idHeroe = request.getParameter("idHeroe");
                request.setAttribute("idHeroe", idHeroe);

                vista = request.getRequestDispatcher("AñadirObjeto.jsp");
                vista.forward(request, response);

                break;

            case "editarObjeto":

                idHeroe = request.getParameter("idHeroe");
                idObjeto = request.getParameter("idObjeto");
                int cantidad = daoInventario.obtenerCantidad(Integer.parseInt(idHeroe), Integer.parseInt(idObjeto));

                request.setAttribute("idHeroe", idHeroe);
                request.setAttribute("idObjeto", idObjeto);
                request.setAttribute("cantidad", cantidad);

                vista = request.getRequestDispatcher("EditarObjeto.jsp");
                vista.forward(request, response);

                break;

            case "borrarObjeto":

                idHeroe = request.getParameter("idHeroe");
                idObjeto = request.getParameter("idObjeto");

                daoInventario.borrarObjeto(Integer.parseInt(idHeroe), Integer.parseInt(idObjeto));

                // ---ESPACIO PARA IMPLICANCIA EN CATALOGO DE OBJETOS-----

                //--------------------------------------------------------

                response.sendRedirect(request.getContextPath() + "/MenuHeroes?action=inventario&idHeroe=" + idHeroe);

                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        DaoHeroe daoHeroe = new DaoHeroe();
        DaoInventario daoInventario = new DaoInventario();
        ArrayList<Heroe> listaParejasDisponibles = new ArrayList<>();
        RequestDispatcher vista;
        Heroe heroe;
        String idHeroe;
        Genero genero;
        Heroe pareja;
        Inventario inventario;
        int nivel;
        int cantidad;
        float experiencia;
        String mensajeError = "Error: Dato(s) inválido(s)";

        switch (action) {
            case "buscar":

                String busqueda = request.getParameter("busqueda");
                ArrayList<Heroe> listaHeroes = daoHeroe.buscarPorNombre(busqueda);

                request.setAttribute("busqueda", busqueda);
                request.setAttribute("listaHeroes", listaHeroes);
                vista = request.getRequestDispatcher("MenuHeroes.jsp");
                vista.forward(request, response);

                break;

            case "guardar":

                heroe = new Heroe();
                heroe.setNombre(request.getParameter("nombre"));

                try {

                    heroe.setEdad(Integer.parseInt(request.getParameter("edad")));

                    genero = new Genero();
                    genero.setIdGenero(request.getParameter("genero"));
                    heroe.setGenero(genero);

                    heroe.setClase(request.getParameter("clase"));
                    nivel = Integer.parseInt(request.getParameter("nivel"));
                    heroe.setNivel(nivel);
                    heroe.setAtaque(Integer.parseInt(request.getParameter("ataque")));

                    experiencia = 0;
                    if (nivel > 0 && nivel <= 15) {
                        experiencia = (nivel * nivel * nivel) * (24 + ((float) nivel + 1) / 3) / 50;
                    } else if (nivel >= 16 && nivel <= 35) {
                        experiencia = (nivel * nivel * nivel) * (14 + (float) nivel) / 50;
                    } else if (nivel >= 36 && nivel <= 100) {
                        experiencia = (nivel * nivel * nivel) * (32 + ((float) nivel / 2)) / 50;
                    }

                    heroe.setExperiencia(experiencia);

                    pareja = new Heroe();
                    pareja.setIdHeroe(Integer.parseInt(request.getParameter("idPareja")));
                    heroe.setPareja(pareja);

                    if ((heroe.getNombre().length()<=10) && (heroe.getEdad()>=8 && heroe.getEdad()<=999) && (heroe.getClase().length()<=50) && (heroe.getNivel()>=1 && heroe.getNivel()<=100) && (heroe.getAtaque()>0)){

                        int idHeroeGuardado = daoHeroe.guardarHeroe(heroe);
                        if (idHeroeGuardado != 0) { // SI TIENE PAREJA SE ACTUALIZA DICHA PAREJA
                            daoHeroe.actualizarPareja(heroe.getPareja().getIdHeroe(), idHeroeGuardado);
                        }
                        response.sendRedirect(request.getContextPath() + "/MenuHeroes");
                    }
                    else {
                        throw new NumberFormatException();
                    }

                }catch (NumberFormatException e){

                    listaParejasDisponibles = daoHeroe.obtenerParejasDisponibles(0);

                    request.setAttribute("listaParejas", listaParejasDisponibles);
                    request.setAttribute("mensajeError", mensajeError);

                    vista = request.getRequestDispatcher("AñadirHeroe.jsp");
                    vista.forward(request, response);

                }

                break;

                /*
                Gateway Load Balancing Protocol (GLBP)
                -

                */

            case "actualizar":

                heroe = new Heroe();
                heroe.setIdHeroe(Integer.parseInt(request.getParameter("idHeroe")));
                heroe.setNombre(request.getParameter("nombre"));
                heroe.setEdad(Integer.parseInt(request.getParameter("edad")));

                genero = new Genero();
                genero.setIdGenero(request.getParameter("genero"));
                heroe.setGenero(genero);

                heroe.setClase(request.getParameter("clase"));
                nivel = Integer.parseInt(request.getParameter("nivel"));
                heroe.setNivel(nivel);
                heroe.setAtaque(Integer.parseInt(request.getParameter("ataque")));

                experiencia = 0;
                if (nivel > 0 && nivel <= 15) {
                    experiencia = (nivel * nivel * nivel) * (24 + ((float) nivel + 1) / 3) / 50;
                } else if (nivel >= 16 && nivel <= 35) {
                    experiencia = (nivel * nivel * nivel) * (14 + (float) nivel) / 50;
                } else if (nivel >= 36 && nivel <= 100) {
                    experiencia = (nivel * nivel * nivel) * (32 + ((float) nivel / 2)) / 50;
                }

                heroe.setExperiencia(experiencia);

                pareja = new Heroe();
                pareja.setIdHeroe(Integer.parseInt(request.getParameter("idPareja")));
                heroe.setPareja(pareja);

                Heroe exPareja = daoHeroe.buscarPareja(heroe.getIdHeroe()); // UTILIZA PARAMETRO idPareja PARA HALLAR EXPAREJA
                daoHeroe.actualizarHeroe(heroe); // ACTUALIZO idPareja = null

                if (heroe.getPareja().getIdHeroe() == 0) { // ROMPE RELACION CON PAREJA
                    daoHeroe.actualizarPareja(exPareja.getIdHeroe(), 0);
                } else {
                    daoHeroe.actualizarPareja(heroe.getPareja().getIdHeroe(), heroe.getIdHeroe());
                }

                response.sendRedirect(request.getContextPath() + "/MenuHeroes");

                break;

            case "guardarObjeto":

                boolean disponible = true;

                idHeroe = request.getParameter("idHeroe");
                cantidad = Integer.parseInt(request.getParameter("cantidad"));

                String nombreObjeto = request.getParameter("nombre"); // SE DEBE VALIDAR QUE NO SEA UN OBJETO REPETIDO DEL HEROE

                // ------ VALIDACION DE QUE HEROE NO REGISTRE MISMO OBJETO DOS VECES ---------
                ArrayList<Inventario> listaObjetosHeroe = daoInventario.obtenerInventario(idHeroe);

                for (Inventario inv : listaObjetosHeroe) {
                    if (nombreObjeto.equalsIgnoreCase(inv.getObjeto().getNombreObjeto())) {
                        disponible = false;
                    }
                }

                if (disponible) { // " OBJETO DISPONIBLE "
                    ArrayList<Objeto> listaObjetos = daoInventario.obtenerlistaObjetos();
                    for (Objeto objeto : listaObjetos) {
                        if (nombreObjeto.equalsIgnoreCase(objeto.getNombreObjeto())) { // OBJETO SE ENCUENTRA EN CATALOGO DE OBJETOS
                            int idObjeto = objeto.getIdObjeto();
                            daoInventario.guardarObjeto(Integer.parseInt(idHeroe), idObjeto, cantidad);

                            response.sendRedirect(request.getContextPath() + "/MenuHeroes?action=inventario&idHeroe=" + idHeroe);
                        } else { // OBJETO NO SE ENCUENTRA EN CATALOGO DE OBJETOS
                            // VISTA CON ERROR
                        }
                    }
                } else { // OBJETO REPETIDO
                    // VISTA CON ERROR
                }

                // -----------------------------------------------------------------------------

                break;

            case "actualizarObjeto":

                cantidad = Integer.parseInt(request.getParameter("cantidad"));
                idHeroe = request.getParameter("idHeroe");
                String idObjeto = request.getParameter("idObjeto");

                daoInventario.actualizarObjeto(Integer.parseInt(idHeroe), Integer.parseInt(idObjeto), cantidad);

                response.sendRedirect(request.getContextPath() + "/MenuHeroes?action=inventario&idHeroe=" + idHeroe);

                break;
        }

    }
}
