package com.example.lab08.MenuEnemigos.Daos;

import com.example.lab08.MenuEnemigos.Beans.Genero;
import com.example.lab08.MenuEnemigos.Beans.Heroe;

import java.sql.*;
import java.util.ArrayList;

public class DaoHeroe {

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

}
