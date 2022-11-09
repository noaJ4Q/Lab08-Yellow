package com.example.lab08.WIkiFantastica.Daos;

import com.example.lab08.WIkiFantastica.Beans.*;

import java.sql.*;
import java.util.ArrayList;

public class DaoHechizo {

    public ArrayList<Hechizo> obtenerListaHechizos() {
        ArrayList<Hechizo> listaHechizos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "select h.idHechizo, h.nombre, e.nombre as 'elemento'," +
                "h.potencia, h.precision, b.nombre as 'base', h.nivelDeAprendizaje"+
                "from hechizo h"+
                "inner join elemento e (h.idElemento = e.idElemento)"+
                "left join hechizo b on (h.idHechizoBase = b.idHechizo)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Hechizo hechizo = new Hechizo();
                Elemento elemento = new Elemento();

                hechizo.setIdHechizo(rs.getInt(1));
                hechizo.setNombreHechizo(rs.getString(2));

                elemento.setNombreElemento(rs.getString(3));
                hechizo.setElementoHechizo(elemento);

                hechizo.setPotencia(rs.getInt(4));
                hechizo.setPrecision(rs.getInt(5));
                hechizo.setNivelAprendizaje(rs.getInt(7));

                Hechizo hechizoBase = new Hechizo();
                hechizoBase.setNombreHechizo(rs.getString(6)==null ? "-" : rs.getString(6));

                listaHechizos.add(hechizo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaHechizos;
    }


    public void agregarHechizo(Hechizo hechizo) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "insert into hechizo (nombre,idElemento,potencia,precision, nivelAprendizaje,idHechizoBase)"+
                "values (?,?,?,?,?,?,?)";


        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {


            pstmt.setString(1,hechizo.getNombreHechizo());
            pstmt.setInt(2,hechizo.getElementoHechizo().getIdElemento());
            pstmt.setInt(3, hechizo.getPotencia());
            pstmt.setInt(4, hechizo.getPrecision());
            pstmt.setInt(6,hechizo.getNivelAprendizaje());
            pstmt.setInt(7,hechizo.getHechizoBase().getIdHechizo());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void eliminarHechizo(String idHechizoString, String idHechizoBaseString){

        int idHechizo = Integer.parseInt(idHechizoString);
        int idHechizoBase = Integer.parseInt(idHechizoString);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "DELETE FROM hechizo WHERE idHechizo=? ";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, idHechizo);
            pstmt.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException();
        }


    }

    public Hechizo buscarHechizo(int idHechizo) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/yellow";

        Hechizo hechizo = null;
        String sql = "select h.idHechizo, h.nombre, e.nombre as 'elemento'," +
                "h.potencia, h.precision, b.nombre as 'base', h.nivelDeAprendizaje"+
                "from hechizo h"+
                "inner join elemento e (h.idElemento = e.idElemento)"+
                "left join hechizo b on (h.idHechizoBase = b.idHechizo)";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, idHechizo);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    hechizo = new Hechizo();
                    hechizo.setIdHechizo(rs.getInt(1));
                    hechizo.setNombreHechizo(rs.getString(2));

                    Elemento elemento = new Elemento();
                    elemento.setIdElemento(rs.getInt("e.idClase"));
                    elemento.setNombreElemento(rs.getString("e.nombre"));
                    hechizo.setElementoHechizo(elemento);
                    hechizo.setPotencia(rs.getInt(4));
                    hechizo.setPrecision(rs.getInt(5));
                    hechizo.setNivelAprendizaje(rs.getInt(7));
                    Hechizo hechizoBase = new Hechizo();
                    hechizoBase.setNombreHechizo(rs.getString(6)==null ? "-" : rs.getString(6));


                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return hechizo;

    }

    public void borrarHechizo(int idHechizo){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "DELETE FROM heroe WHERE idHeroe=?";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = conn.prepareStatement(sql)){

            pstmt.setInt(1, idHechizo);
            pstmt.executeUpdate();

        }
        catch (SQLException e){
            throw new RuntimeException();
        }

    }

}
