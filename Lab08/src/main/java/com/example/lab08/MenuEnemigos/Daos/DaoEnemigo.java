package com.example.lab08.MenuEnemigos.Daos;


import java.sql.*;
import java.util.ArrayList;

import com.example.lab08.MenuEnemigos.Beans.Clase;
import com.example.lab08.MenuEnemigos.Beans.Enemigo;
import com.example.lab08.MenuEnemigos.Beans.Genero;

public class DaoEnemigo {

    public ArrayList<Enemigo> obtenerListaEnemigos (){
        ArrayList<Enemigo> listaEnemigos = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

        //Conexion a DB

        String user="root";
        String password="root";
        String url="jdbc:mysql://localhost:3306/yellow";
        String sql ="select * from enemigo";

        try (Connection connection = DriverManager.getConnection(url, user, password);
             Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Enemigo enemigo = new Enemigo();
                enemigo.setIdEnemigo(rs.getInt(1));
                enemigo.setNombreEnemigo(rs.getString(2));
                DaoClase daoClase = new DaoClase();
                Clase claseEnemigo = new Clase();
                for(Clase clase : daoClase.obtenerListaClases()){
                    if(clase.getIdClase()==rs.getInt(3)){
                        claseEnemigo.setIdClase(clase.getIdClase());
                        claseEnemigo.setNombreClase(clase.getNombreClase());
                    }
                }
                enemigo.setClase(claseEnemigo);
                enemigo.setAtaque(rs.getInt(4));
                enemigo.setExperiencia(rs.getInt(5));
                Genero genero = new Genero();
                DaoGenero daoGenero = new DaoGenero();
                if(rs.getInt(6)!=0){
                    for(Genero genero1: daoGenero.obtenerListaGeneros()){
                        if(genero1.getIdGenero()==rs.getInt(6)){
                            genero.setIdGenero(genero1.getIdGenero());
                            genero.setNombreGenero(genero1.getNombreGenero());
                        }
                    }
                enemigo.setGenero(genero);
                }else{
                    enemigo.setGenero(genero);
                }
                listaEnemigos.add(enemigo);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listaEnemigos;

    }
}
