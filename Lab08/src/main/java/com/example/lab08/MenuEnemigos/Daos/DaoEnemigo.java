package com.example.lab08.MenuEnemigos.Daos;


import java.sql.*;
import java.util.ArrayList;

import com.example.lab08.MenuEnemigos.Beans.Clase;
import com.example.lab08.MenuEnemigos.Beans.Enemigo;
import com.example.lab08.MenuEnemigos.Beans.Genero;
import com.example.lab08.MenuEnemigos.Beans.Objeto;

public class DaoEnemigo {

    public ArrayList<Enemigo> obtenerListaEnemigos() {
        ArrayList<Enemigo> listaEnemigos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Conexion a DB

        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "select e.*,c.*,cdo.nombre,d.*,g.* from enemigo e "+
        "left join clase c on e.idClase = c.idClase "+
        "left join dropeo d on e.idEnemigo = d.idEnemigo "+
        "left join catalogodeobjetos cdo on d.idObjeto = cdo.idObjeto "+
        "left join genero g on e.idGenero = g.idGenero";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Enemigo enemigo = new Enemigo();
                enemigo.setIdEnemigo(rs.getInt(1));
                enemigo.setNombreEnemigo(rs.getString(2));
                Clase clase = new Clase();
                clase.setIdClase(rs.getInt(3));
                clase.setNombreClase(rs.getString(8));
                enemigo.setClase(clase);
                enemigo.setAtaque(rs.getInt(4));
                enemigo.setExperiencia(rs.getInt(5));
                Objeto objeto =new Objeto();
                objeto.setIdObjeto(rs.getInt(10));
                objeto.setNombreObjeto(rs.getString(9));
                enemigo.setObjeto(objeto);
                enemigo.setProbabilidad(rs.getFloat(12));
                Genero genero = new Genero();
                genero.setIdGenero(rs.getString(6));
                genero.setNombreGenero(rs.getString(14));
                enemigo.setGenero(genero);
                listaEnemigos.add(enemigo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaEnemigos;
    }

    public Enemigo buscarEnemigo(int idEnemigo) {


        //inicializacion como nulo
        Enemigo enemigo = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Conexion a DB

        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "select * from enemigo e " +
                "left join clase c on e.idClase = c.idClase " +
                "left join genero g on e.idGenero = g.idGenero " +
                "where e.idEnemigo= ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, idEnemigo);

            try (ResultSet rs = pstmt.executeQuery()) {

                if (rs.next()) {
                    enemigo = new Enemigo();
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

                }

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return enemigo;

    }

    public void agregarEnemigo(Enemigo enemigo) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Conexion a DB

        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "insert into enemigo (nombre,idClase,ataque,experiencia,idGenero)"+
                    "values (?,?,?,?,?)";


        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1,enemigo.getNombreEnemigo());
            pstmt.setInt(2,enemigo.getClase().getIdClase());
            pstmt.setInt(3,enemigo.getAtaque());
            pstmt.setInt(4,enemigo.getExperiencia());
            pstmt.setString(5,enemigo.getGenero().getIdGenero());

            pstmt.executeUpdate();


        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void agregarDropeo(int idEnemigoNuevo2 , int idObjeto2 , float probabilidad2) {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Conexion a DB

        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "insert into dropeo (idObjeto,idEnemigo,probabilidad) "+
                "values (?,?,?)";


        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1,idObjeto2 );
            pstmt.setInt(2,idEnemigoNuevo2);
            pstmt.setFloat(3,probabilidad2);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void actualizarEnemigo(Enemigo enemigo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "UPDATE enemigo SET nombre= ?,idClase = ?, ataque = ?, experiencia = ? , idGenero = ? WHERE idEnemigo = ?";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setString(1, enemigo.getNombreEnemigo());
            pstmt.setInt(2, enemigo.getClase().getIdClase());
            pstmt.setInt(3, enemigo.getAtaque());
            pstmt.setInt(4, enemigo.getExperiencia());
            pstmt.setString(5,enemigo.getGenero().getIdGenero());
            pstmt.setInt(6,enemigo.getIdEnemigo());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void actualizarDropeo(Enemigo enemigo) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/hr";
        String sql = "UPDATE enemigo SET idObjeto= ?, probabilidad = ? WHERE idEnemigo = ?";

        try (Connection connection = DriverManager.getConnection(url, "root", "root");
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, enemigo.getObjeto().getIdObjeto());
            pstmt.setFloat(2, enemigo.getProbabilidad());
            pstmt.setInt(3, enemigo.getIdEnemigo());

            pstmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void borrarEnemigo(int idEnemigo){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        }catch (ClassNotFoundException e){
            throw new RuntimeException(e);
        }

        String user = "root";
        String password = "root";
        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "delete from enemigo where idEnemigo = ?";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement pstmt = connection.prepareStatement(sql)) {

            pstmt.setInt(1, idEnemigo);

            pstmt.executeUpdate();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }

    }
}
