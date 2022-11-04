package com.example.lab08.MenuEnemigos.Daos;


import java.sql.*;
import java.util.ArrayList;

import com.example.lab08.MenuEnemigos.Beans.Clase;
import com.example.lab08.MenuEnemigos.Beans.Enemigo;
import com.example.lab08.MenuEnemigos.Beans.Genero;

public class DaoEnemigo {

    public ArrayList<Enemigo> obtenerListaEnemigos (){
        ArrayList<Enemigo> listaEnemigos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Conexion a DB

        String user="root";
        String password="root";
        String url="jdbc:mysql://localhost:3306/yellow";
        String sql ="select * from enemigo e" +
                    "left join clase c on e.idClase = c.idClase "+
                    "left join genero g on e.idGenero = g.idGenero";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Enemigo enemigo = new Enemigo();
                enemigo.setIdEnemigo(rs.getInt(1));
                enemigo.setNombreEnemigo(rs.getString(2));
                Clase clase = new Clase();
                clase.setIdClase(rs.getInt("c.idClase"));
                clase.setNombreClase(rs.getString("c.nombre"));
                enemigo.setClase(clase);
                enemigo.setAtaque(rs.getInt(4));
                enemigo.setExperiencia(rs.getInt(5));
                Genero genero = new Genero();
                genero.setIdGenero(rs.getString("g.idGenero"));
                genero.setNombreGenero(rs.getString("g.nombre"));
                enemigo.setGenero(genero);
                listaEnemigos.add(enemigo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaEnemigos;



    }
}
