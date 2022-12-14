package com.example.lab08.WIkiFantastica.Daos;

import com.example.lab08.WIkiFantastica.Beans.Genero;

import java.sql.*;
import java.util.ArrayList;

public class DaoGenero {
    public ArrayList<Genero> obtenerListaGeneros (){
        ArrayList<Genero> listaGeneros = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Conexion a DB

        String user="root";
        String password="root";
        String url="jdbc:mysql://localhost:3306/yellow";
        String sql ="select * from genero";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Genero genero = new Genero();
                genero.setIdGenero(rs.getString(1));
                genero.setNombreGenero(rs.getString(2));
                listaGeneros.add(genero);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return listaGeneros;
    }
}
