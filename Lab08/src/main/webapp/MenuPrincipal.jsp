<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Wiki Fantástica</title>
    </head>
    <body>
        <h1><%= "Hello World!" %>
        </h1>
        <br/>
        <!-- Ir al servelt de MenuEnemigos para ir a la lista de enemigos (por default) -->
        <a href="<%=request.getContextPath()%>/MenuEnemigos">Menú Enemigos</a>
    </body>
</html>