<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab08.MenuEnemigos.Beans.Enemigo" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList<Enemigo> listaEnemigos = (ArrayList<Enemigo>) request.getAttribute("ListaEnemigos");%>
<!doctype html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Menu Enemigos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
              crossorigin="anonymous">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
    </head>
    <body>
        <div class='container'>
            <a href="<%=request.getContextPath()%>/MenuEnemigos?action=crear" class="btn btn-success">Añadir Enemigo</a>

            <table class="table">
                <tr>
                    <th>Nombre de Enemigo</th>
                    <th>Clase</th>
                    <th>Ataque</th>
                    <th>Experiencia Al Ser Derrotado</th>
                    <!--<th>Objeto que dropea</th>-->
                    <th>Genero</th>
                    <th></th>
                    <th></th>
                </tr>
                <% for (Enemigo eng : listaEnemigos) { %>
                <tr>
                    <td><%=eng.getNombreEnemigo()%>
                    </td>
                    <td><%=eng.getClase().getNombreClase()%>
                    </td>
                    <td><%=eng.getAtaque()%>
                    </td>
                    <td><%=eng.getExperiencia()%>
                    </td>
                    <!--<td>< %eng.getObjeto() %>
                    </td>-->
                    <td><%=eng.getGenero().getNombreGenero()%>
                    </td>
                    <!--<td>
                        <a type="button" class="btn btn-primary"
                           href=" < %= request.getContextPath()%>/MenuEnemigos?action=editar&id=< %=eng.getIdEnemigo()%>">
                            <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor"
                                 class="bi bi-pencil" viewBox="0 0 16 16">
                                <path d="M12.146.146a.5.5 0 0 1 .708 0l3 3a.5.5 0 0 1 0 .708l-10 10a.5.5 0 0 1-.168.11l-5 2a.5.5 0 0 1-.65-.65l2-5a.5.5 0 0 1 .11-.168l10-10zM11.207 2.5 13.5 4.793 14.793 3.5 12.5 1.207 11.207 2.5zm1.586 3L10.5 3.207 4 9.707V10h.5a.5.5 0 0 1 .5.5v.5h.5a.5.5 0 0 1 .5.5v.5h.293l6.5-6.5zm-9.761 5.175-.106.106-1.528 3.821 3.821-1.528.106-.106A.5.5 0 0 1 5 12.5V12h-.5a.5.5 0 0 1-.5-.5V11h-.5a.5.5 0 0 1-.468-.325z"></path>
                            </svg>
                        </a>
                    </td>
                    <td>
                        <a type="button" class="btn btn-danger"
                           onclick="return confirm('¿Estas seguro(a) que deseas borrar?')"
                           href="< %= request.getContextPath()%>/MenuEnemigos?action=borrar&id=< %=eng.getIdEnemigo()%>">
                            <i class="bi bi-trash"></i>
                        </a>
                    </td> -->
                </tr>
                <% } %>
            </table>
        </div>
    </body>

    </body>
</html>

