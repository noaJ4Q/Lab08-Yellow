package com.example.lab08.MenuEnemigos.Daos;

import com.example.lab08.MenuEnemigos.Beans.Heroe;
import com.example.lab08.MenuEnemigos.Beans.Inventario;
import com.example.lab08.MenuEnemigos.Beans.Objeto;

import java.sql.*;
import java.util.ArrayList;

public class DaoInventario {

    public ArrayList<Inventario> obtenerInventario(String idHeroeString){
        int idHeroe = Integer.parseInt(idHeroeString);
        ArrayList<Inventario> listaInventario = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "SELECT c.nombre, c.efecto_uso, c.peso, i.cantidad FROM inventario i\n" +
                "INNER JOIN heroe h ON (i.idHeroe = h.idHeroe)\n" +
                "INNER JOIN catalogodeobjetos c ON (c.idObjeto = i.idObjeto)\n" +
                "WHERE h.idHeroe = ?";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, idHeroe);

            try (ResultSet rs = pstmt.executeQuery()){

                while (rs.next()){
                    Inventario inventario = new Inventario();

                    Heroe heroe = new Heroe();
                    heroe.setIdHeroe(idHeroe);
                    inventario.setHeroe(heroe);

                    Objeto objeto = new Objeto();
                    objeto.setNombreObjeto(rs.getString(1));
                    objeto.setEfectoUso(rs.getString(2));
                    objeto.setPeso(rs.getFloat(3));
                    inventario.setObjeto(objeto);

                    inventario.setCantidad(rs.getInt(4));

                    listaInventario.add(inventario);

                }

            }

        }catch (SQLException e){
            throw new RuntimeException();
        }
        return listaInventario;
    }

}
