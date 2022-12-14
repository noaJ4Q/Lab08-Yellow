package com.example.lab08.WIkiFantastica.Daos;

import com.example.lab08.WIkiFantastica.Beans.Clase;
import com.example.lab08.WIkiFantastica.Beans.Elemento;
import com.example.lab08.WIkiFantastica.Beans.DanioPorElemento;

import java.sql.*;
import java.util.ArrayList;

public class DaoDanioPorElemento {

    public ArrayList<DanioPorElemento> obtenerListaDeDaniosPorElemento() {
        ArrayList<DanioPorElemento> listaDaniosPorElemento = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Conexion a DB

        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "select * from danoporelemento dpe" +
                "left join clase c on dpe.idClase = c.idClase " +
                "left join elemento e on dpe.idElemento = e.idElemento";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DanioPorElemento dpel = new DanioPorElemento();
                Clase clase = new Clase();
                clase.setIdClase(rs.getInt("c.idClase"));
                clase.setNombreClase(rs.getString("c.nombre"));
                dpel.setClase(clase);
                Elemento elemento = new Elemento();
                elemento.setIdElemento(rs.getInt("e.idElemento"));
                elemento.setNombreElemento(rs.getString("e.nombre"));
                dpel.setElemento(elemento);
                dpel.setDanoRecibido(rs.getFloat("danioRecibido"));
                listaDaniosPorElemento.add(dpel);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);

        }

        return listaDaniosPorElemento;
    }
}
