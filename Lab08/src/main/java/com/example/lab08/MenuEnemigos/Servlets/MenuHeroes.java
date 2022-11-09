package com.example.lab08.MenuEnemigos.Servlets;

import com.example.lab08.MenuEnemigos.Beans.Genero;
import com.example.lab08.MenuEnemigos.Beans.Heroe;
import com.example.lab08.MenuEnemigos.Beans.Inventario;
import com.example.lab08.MenuEnemigos.Beans.Objeto;
import com.example.lab08.MenuEnemigos.Daos.DaoHeroe;
import com.example.lab08.MenuEnemigos.Daos.DaoInventario;
import com.example.lab08.MenuEnemigos.Daos.DaoObjeto;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

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
        DaoObjeto daoObjeto = new DaoObjeto();
        ArrayList<Heroe> listaParejasDisponibles;
        ArrayList<Inventario> inventario;
        RequestDispatcher vista;
        Heroe heroe;
        Objeto objeto;
        String idHeroe;
        int idObjeto;
        Genero genero;
        Heroe pareja;
        int nivel;
        int cantidad;
        float experiencia;
        float pesoTotal;
        String mensajeError;

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

                mensajeError = "Error: Dato(s) inválido(s)";
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

                    if ((heroe.getNombre().length() <= 10) && (heroe.getEdad() >= 8 && heroe.getEdad() <= 999) && (heroe.getClase().length() <= 50) && (heroe.getNivel() >= 1 && heroe.getNivel() <= 100) && (heroe.getAtaque() > 0)) {

                        int idHeroeGuardado = daoHeroe.guardarHeroe(heroe);
                        if (idHeroeGuardado != 0) { // SI TIENE PAREJA SE ACTUALIZA DICHA PAREJA
                            daoHeroe.actualizarPareja(heroe.getPareja().getIdHeroe(), idHeroeGuardado);
                        }
                        response.sendRedirect(request.getContextPath() + "/MenuHeroes");
                    } else {
                        throw new NumberFormatException();
                    }

                } catch (NumberFormatException e) {

                    listaParejasDisponibles = daoHeroe.obtenerParejasDisponibles(0);

                    request.setAttribute("listaParejas", listaParejasDisponibles);
                    request.setAttribute("mensajeError", mensajeError);

                    vista = request.getRequestDispatcher("AñadirHeroe.jsp");
                    vista.forward(request, response);

                }

                break;

            case "actualizar":

                mensajeError = "Error: Dato(s) inválido(s)";
                heroe = new Heroe();

                try {
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

                    if ((heroe.getNombre().length() <= 10) && (heroe.getEdad() >= 8 && heroe.getEdad() <= 999) && (heroe.getClase().length() <= 50) && (heroe.getNivel() >= 1 && heroe.getNivel() <= 100) && (heroe.getAtaque() > 0)) {

                        Heroe exPareja = daoHeroe.buscarPareja(heroe.getIdHeroe()); // UTILIZA PARAMETRO idPareja PARA HALLAR EXPAREJA
                        daoHeroe.actualizarHeroe(heroe); // ACTUALIZO idPareja = null

                        if (heroe.getPareja().getIdHeroe() == 0) { // ROMPE RELACION CON PAREJA
                            daoHeroe.actualizarPareja(exPareja.getIdHeroe(), 0);
                        } else {
                            daoHeroe.actualizarPareja(heroe.getPareja().getIdHeroe(), heroe.getIdHeroe());
                        }

                        response.sendRedirect(request.getContextPath() + "/MenuHeroes");
                    } else {
                        throw new NumberFormatException();
                    }

                } catch (NumberFormatException e) {

                    idHeroe = request.getParameter("idHeroe");
                    heroe = daoHeroe.buscarPorId(idHeroe);
                    listaParejasDisponibles = daoHeroe.obtenerParejasDisponibles(heroe.getIdHeroe());

                    request.setAttribute("heroeEditar", heroe);
                    request.setAttribute("listaParejas", listaParejasDisponibles);
                    request.setAttribute("mensajeError", mensajeError);

                    vista = request.getRequestDispatcher("EditarHeroe.jsp");
                    vista.forward(request, response);

                }

                break;

            case "guardarObjeto":

                mensajeError = "Error: Dato(s) inválido(s)";

                boolean disponible = false;

                idHeroe = request.getParameter("idHeroe");
                heroe = daoHeroe.buscarPorId(idHeroe);
                inventario = daoInventario.obtenerInventario(idHeroe);
                pesoTotal = 0;

                if (inventario != null){
                    for (Inventario i : inventario){
                        pesoTotal = pesoTotal + i.getObjeto().getPeso()*i.getCantidad();
                    }
                }

                HashSet<Objeto> listaObjetos = daoInventario.obtenerlistaObjetos2();
                HashSet<Objeto> listaObjetosHeroe = daoInventario.obtenerlistaObjetosHeroe(Integer.parseInt(idHeroe));
                listaObjetos.removeAll(listaObjetosHeroe);
                // FALTA OBTENER LISTA DE OBJETOS DISPONIBLES (listaObjetos)

                try {
                    cantidad = Integer.parseInt(request.getParameter("cantidad"));
                    String nombreObjeto = request.getParameter("nombre"); // SE DEBE VALIDAR QUE NO SEA UN OBJETO REPETIDO DEL HEROE

                    // ------ VALIDACION DE QUE HEROE NO REGISTRE MISMO OBJETO DOS VECES ---------

                    for (Objeto o: listaObjetos){
                        if (nombreObjeto.equalsIgnoreCase(o.getNombreObjeto())){

                            pesoTotal = pesoTotal + cantidad*daoObjeto.buscarPorNombre(nombreObjeto).getPeso();

                            if (cantidad>0 && (pesoTotal<(heroe.getAtaque()*heroe.getAtaque()))){
                                disponible = true;
                            }
                            else {
                                mensajeError = "Error: Cantidad inválida";
                                throw new NumberFormatException();
                            }

                        }
                    }

                    if (disponible){
                        idObjeto = daoObjeto.buscarPorNombre(nombreObjeto).getIdObjeto();
                        daoInventario.guardarObjeto(Integer.parseInt(idHeroe), idObjeto, cantidad);
                        response.sendRedirect(request.getContextPath() + "/MenuHeroes?action=inventario&idHeroe=" + idHeroe);
                    }
                    else {
                        mensajeError = "Error: Objeto inválido";
                        throw new NumberFormatException();
                    }

                } catch (NumberFormatException e) {

                    request.setAttribute("idHeroe", idHeroe);
                    request.setAttribute("mensajeError", mensajeError);

                    vista = request.getRequestDispatcher("AñadirObjeto.jsp");
                    vista.forward(request, response);
                }

                break;

            case "actualizarObjeto":

                idHeroe = request.getParameter("idHeroe");
                String idObjetoString = request.getParameter("idObjeto");
                idObjeto = Integer.parseInt(idObjetoString);

                heroe = daoHeroe.buscarPorId(idHeroe);

                inventario = daoInventario.obtenerInventario(idHeroe);
                pesoTotal = 0;
                for (Inventario i: inventario){
                    if (i.getObjeto().getIdObjeto()!=idObjeto){
                        pesoTotal = pesoTotal + i.getObjeto().getPeso()*i.getCantidad();
                    }
                }

                try {
                    cantidad = Integer.parseInt(request.getParameter("cantidad"));

                    pesoTotal = pesoTotal + cantidad*daoObjeto.buscarPorId(idObjeto).getPeso();

                    if (pesoTotal<(heroe.getAtaque()*heroe.getAtaque())){
                        daoInventario.actualizarObjeto(Integer.parseInt(idHeroe), idObjeto, cantidad);
                        response.sendRedirect(request.getContextPath() + "/MenuHeroes?action=inventario&idHeroe=" + idHeroe);
                    }
                    else {
                        throw new NumberFormatException();
                    }

                } catch (NumberFormatException e){

                    cantidad = daoInventario.obtenerCantidad(Integer.parseInt(idHeroe), idObjeto);

                    request.setAttribute("mensajeError", "Cantidad inválida"+pesoTotal);
                    request.setAttribute("idHeroe", idHeroe);
                    request.setAttribute("idObjeto", idObjetoString);
                    request.setAttribute("cantidad", cantidad);

                    vista = request.getRequestDispatcher("EditarObjeto.jsp");
                    vista.forward(request, response);
                }

                break;
        }

    }
}
