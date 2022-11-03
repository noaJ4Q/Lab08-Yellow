package com.example.lab08.MenuEnemigos.Daos;

import com.example.lab08.MenuEnemigos.Beans.Clase;
import com.example.lab08.MenuEnemigos.Beans.Enemigo;

import java.sql.*;
import java.util.ArrayList;

public class DaoClase {
    public ArrayList<Clase> obtenerListaClases (){
        ArrayList<Clase> listaClases = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Conexion a DB

        String user="root";
        String password="root";
        String url="jdbc:mysql://localhost:3306/yellow";
        String sql ="select * from clase";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Clase clase = new Clase();
                clase.setIdClase(rs.getInt(1));
                clase.setNombreClase(rs.getString(2));
                listaClases.add(clase);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return listaClases;
    }
}
