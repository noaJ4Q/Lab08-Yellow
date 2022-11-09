package com.example.lab08.WIkiFantastica.Daos;

import com.example.lab08.WIkiFantastica.Beans.Heroe;
import com.example.lab08.WIkiFantastica.Beans.Inventario;
import com.example.lab08.WIkiFantastica.Beans.Objeto;

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
        String sql = "SELECT c.nombre, c.efecto_uso, c.peso, i.cantidad, c.idObjeto FROM inventario i\n" +
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
                    objeto.setIdObjeto(rs.getInt(5));
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


    public ArrayList<Objeto> obtenerlistaObjetos(){
        ArrayList<Objeto> listaObjetos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "SELECT * FROM catalogodeobjetos";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){

                Objeto objeto = new Objeto();

                objeto.setIdObjeto(rs.getInt(1));
                objeto.setNombreObjeto(rs.getString(2));
                objeto.setEfectoUso(rs.getString(3));
                objeto.setPeso(rs.getFloat(4));

                listaObjetos.add(objeto);
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }
        return listaObjetos;

    }

    public ArrayList<Objeto> obtenerlistaObjetosHeroe(int idHeroe){
        ArrayList<Objeto> listaObjetos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "select o.* from inventario i " +
                "inner join catalogodeobjetos o on (i.idObjeto = o.idObjeto) " +
                "where idHeroe = ?";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, idHeroe);

            try (ResultSet rs = pstmt.executeQuery()){
                while (rs.next()){

                    Objeto objeto = new Objeto();

                    objeto.setIdObjeto(rs.getInt(1));
                    objeto.setNombreObjeto(rs.getString(2));
                    objeto.setEfectoUso(rs.getString(3));
                    objeto.setPeso(rs.getFloat(4));

                    listaObjetos.add(objeto);
                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return listaObjetos;
    }

    public int obtenerCantidad(int idHeroe, int idObjeto){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "select cantidad from inventario " +
                "where idHeroe = ? and idObjeto = ?";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, idHeroe);
            pstmt.setInt(2, idObjeto);

            try (ResultSet rs = pstmt.executeQuery()){
                if (rs.next()){
                    return rs.getInt(1);
                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return -1;
    }

    public void guardarObjeto(int idHeroe, int idObjeto, int cantidad){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "insert into inventario (idHeroe, idObjeto, cantidad)" +
                "values (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
            PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, idHeroe);
            pstmt.setInt(2, idObjeto);
            pstmt.setInt(3, cantidad);

            pstmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException();
        }

    }

    public void actualizarObjeto(int idHeroe, int idObjeto, int cantidad){
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "update inventario set cantidad = ? " +
                "where idHeroe = ? and idObjeto = ?";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, cantidad);
            pstmt.setInt(2, idHeroe);
            pstmt.setInt(3, idObjeto);

            pstmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public void borrarObjeto(int idHeroe, int idObjeto){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "delete from inventario where idHeroe = ? and idObjeto = ?";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, idHeroe);
            pstmt.setInt(2, idObjeto);

            pstmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public void borrarInventario(int idHeroe){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "delete from inventario where idHeroe=?";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, idHeroe);
            pstmt.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException();
        }
    }

}
