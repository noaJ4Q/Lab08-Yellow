package com.example.lab08.MenuEnemigos.Daos;

import com.example.lab08.MenuEnemigos.Beans.Genero;
import com.example.lab08.MenuEnemigos.Beans.Heroe;

import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;

public class DaoHeroe {

    public ArrayList<Heroe> obtenerParejasDisponibles(){
        ArrayList<Heroe> listaParejasDisponibles = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "select h.* from heroe h " +
                "where h.idPareja is null;";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                Heroe parejaDisponible = new Heroe();

                parejaDisponible.setIdHeroe(rs.getInt(1));
                parejaDisponible.setNombre(rs.getString(2));
                parejaDisponible.setEdad(rs.getString(3));

                Genero genero = new Genero();
                genero.setIdGenero(rs.getString(4));
                parejaDisponible.setGenero(genero);

                parejaDisponible.setClase(rs.getString(5));
                parejaDisponible.setNivel(rs.getInt(6));
                parejaDisponible.setAtaque(rs.getInt(7));
                parejaDisponible.setExperiencia(rs.getFloat(8));

                listaParejasDisponibles.add(parejaDisponible);
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }

        return listaParejasDisponibles;
    }

    public ArrayList<Heroe> obtenerListaHeroes (){
        ArrayList<Heroe> listaHeroes = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url="jdbc:mysql://localhost:3306/yellow";
        String sql = "select h.idHeroe, h.nombre,  h.edad, g.nombre as 'genero', " +
                "h.clase, h.nivel, h.ataque, p.nombre as 'pareja', h.experiencia " +
                "from heroe h " +
                "inner join genero g on (h.idGenero = g.idGenero) " +
                "left join heroe p on (h.idPareja = p.idHeroe)";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)){

            while (rs.next()){
                Heroe heroe = new Heroe();

                heroe.setIdHeroe(rs.getInt(1));
                heroe.setNombre(rs.getString(2));
                heroe.setEdad(rs.getString(3));

                Genero genero = new Genero();
                genero.setNombreGenero(rs.getString(4));
                heroe.setGenero(genero);

                heroe.setClase(rs.getString(5));
                heroe.setNivel(rs.getInt(6));
                heroe.setAtaque(rs.getInt(7));

                Heroe pareja = new Heroe();
                pareja.setNombre(rs.getString(8) == null ? "-" : rs.getString(8));
                heroe.setPareja(pareja);

                heroe.setExperiencia(rs.getFloat(9));

                listaHeroes.add(heroe);
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }
        return listaHeroes;
    }

    public void actualizarPareja(int idHeroe, int idParejaNueva){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "UPDATE heroe SET idPareja = ? WHERE idHeroe = ?";

        try(Connection conn = DriverManager.getConnection(url, "root", "root");
            PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, idParejaNueva);
            pstmt.setInt(2, idHeroe);

            pstmt.executeUpdate();

        }catch (SQLException e){
            throw new RuntimeException();
        }

    }

    public int guardarHeroe(Heroe heroe){

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "INSERT INTO heroe (nombre, edad, idGenero, clase, nivel, ataque, experiencia, idPareja) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(url, "root", "root");
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            pstmt.setString(1, heroe.getNombre());
            pstmt.setString(2, heroe.getEdad());
            pstmt.setString(3, heroe.getGenero().getIdGenero());
            pstmt.setString(4, heroe.getClase());
            pstmt.setInt(5, heroe.getNivel());
            pstmt.setInt(6, heroe.getAtaque());
            pstmt.setFloat(7, heroe.getExperiencia());

            if (heroe.getPareja().getIdHeroe()==0){
                pstmt.setNull(8, Types.INTEGER);
                pstmt.executeUpdate();

                return 0;
            }
            else {
                pstmt.setInt(8, heroe.getPareja().getIdHeroe());
                pstmt.executeUpdate();

                try (ResultSet rsKey = pstmt.getGeneratedKeys()){
                    if (rsKey.next()){
                        int idHeroe = rsKey.getInt(1);
                        return idHeroe;
                    }
                    return 0;
                }
            }

        }catch (SQLException e){
            throw new RuntimeException();
        }
    }

    public void borrarHeroe(String idHeroeString){

        int idHeroe = Integer.parseInt(idHeroeString);

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        String url = "jdbc:mysql://localhost:3306/yellow";
        String sql = "DELETE FROM heroe WHERE idHeroe=?";

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
