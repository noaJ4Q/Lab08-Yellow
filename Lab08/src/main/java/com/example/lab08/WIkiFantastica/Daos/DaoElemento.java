package com.example.lab08.WIkiFantastica.Daos;

import com.example.lab08.WIkiFantastica.Beans.Elemento;

import java.sql.*;
import java.util.ArrayList;

public class DaoElemento {
    public ArrayList<Elemento> obtenerListaElementos (){
        ArrayList<Elemento> listaElementos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Conexion a DB

        String user="root";
        String password="root";
        String url="jdbc:mysql://localhost:3306/yellow";
        String sql ="select * from elemento";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Elemento elemento= new Elemento();
                elemento.setIdElemento(rs.getInt(1));
                elemento.setNombreElemento(rs.getString(2));
                listaElementos.add(elemento);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return listaElementos;
    }
}

