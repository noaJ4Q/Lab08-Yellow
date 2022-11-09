package com.example.lab08.MenuEnemigos.Daos;

import com.example.lab08.MenuEnemigos.Beans.Objeto;

import java.sql.*;

public class DaoObjeto {

    public Objeto buscarPorId(int idObjeto){

        Objeto objeto = new Objeto();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "select * from catalogodeobjetos where idObjeto = ?";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, idObjeto);

            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    objeto.setIdObjeto(rs.getInt(1));
                    objeto.setNombreObjeto(rs.getString(2));
                    objeto.setEfectoUso(rs.getString(3));
                    objeto.setPeso(rs.getFloat(4));
                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return objeto;
    }

    public Objeto buscarPorNombre(String nombre){
        Objeto objeto = new Objeto();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "select * from catalogodeobjetos where nombre = ?";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setString(1, nombre);

            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){
                    objeto.setIdObjeto(rs.getInt(1));
                    objeto.setNombreObjeto(rs.getString(2));
                    objeto.setEfectoUso(rs.getString(3));
                    objeto.setPeso(rs.getFloat(4));
                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return objeto;
    }

}
