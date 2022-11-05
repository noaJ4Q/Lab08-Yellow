<%@ page import="com.example.lab08.MenuEnemigos.Beans.Enemigo" %>
<%@ page import="com.example.lab08.MenuEnemigos.Beans.Clase" %>
<%@ page import="java.lang.reflect.Array" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.lab08.MenuEnemigos.Beans.Genero" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% ArrayList<Clase> listaClases = (ArrayList<Clase>) request.getAttribute("ListaClases");
  ArrayList<Genero> listaGeneros = (ArrayList<Genero>) request.getAttribute("ListaGeneros");
%>
<html>
  <head>
    <head>
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>Añadir Enemigo</title>
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.2/dist/css/bootstrap.min.css" rel="stylesheet"
            integrity="sha384-Zenh87qX5JnK2Jl0vWa8Ck2rdkQ2Bzep5IDxbcnCeuOxjzrPF/et3URy9Bv1WTRi"
            crossorigin="anonymous">
    </head>
  <body>
    <div class='container'>
      <div class="row justify-content-center">
        <div class="col-md-6 col-sm-12 col-lg-4">
          <h1>Añadir Enemigo</h1>
          <form method="post" action="<%=request.getContextPath()%>/MenuEnemigos?action=guardar">
            <div class="mb-3">
              <label for="nombre" class="form-label">Nombre del Enemigo</label>
              <input type="text" class="form-control" id="nombre" placeholder="nombre" name="nombre">
            </div>
            <div class="mb-3">
              <label for="claseEnemigo" class="form-label">Clase del Enemigo</label>
              <select name="claseEnemigo" id="claseEnemigo" class="form-control">
                <% for(Clase clase: listaClases){ %>
                <option value="<%=clase.getIdClase()%>"><%=clase.getNombreClase()%></option>
                <% } %>
              </select>
            </div>
            <div class="mb-3">
              <label for="ataque" class="form-label">Ataque del Enemigo (Numero Entero)</label>
              <input type="text" class="form-control" id="ataque"  name="ataque">
            </div>
            <div class="mb-3">
              <label for="experiencia" class="form-label">Experiencia al ser Derrotado (Numero Entero)</label>
              <input type="text" class="form-control" id="experiencia"  name="experiencia">
            </div>
            <div class="mb-3">
              <!-- label como un titulo para el input -->
              <label for="probabilidad" class="form-label">Probabilidad de Dropear el Objeto (Numero Decimal)</label>
              <input type="text" class="form-control" id="probabilidad" name="probabilidad">
            </div>
            <div class="mb-3">
              <label for="generoEnemigo" class="form-label">Clase del Enemigo</label>
              <select name="generoEnemigo" id="generoEnemigo" class="form-control">
                <% for(Genero gen: listaGeneros){ %>
                <option value="<%=gen.getIdGenero()%>"><%=gen.getNombreGenero()%></option>
                <% } %>
              </select>
            </div>
            <button type="submit" class="btn btn-primary">Añadir Enemigo</button>
            <!-- boton para volver al Menu de Enemigos al añadir un nuevo Enemigo -->
            <a href="<%=request.getContextPath()%>/MenuEnemigos" class="btn btn-danger">Regresar</a>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>
