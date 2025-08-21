package org.progI.dao;

import org.progI.configuracion.AdministradorConexion;
import org.progI.entities.Auto;
import org.progI.entities.Marca;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class AutoDAO {

    private static Connection conn;

    public void insertarAuto(Auto auto) {
        // establecer la conexion a la BD
        conn = AdministradorConexion.obtenerConexion();

        // crear string consulta SQL
        String sql =
                "INSERT INTO autos (idAuto,patente,color,anio,kilometraje,marca,modelo) " +
                        "VALUES (" + auto.getIdAuto() + "," +
                        "'" + auto.getPatente() + "'," +
                        "'" + auto.getColor() + "'," +
                        + auto.getAnio()+ "," +
                        + auto.getKilometraje() + "," +
                        "'" + auto.getMarca() + "'," +
                        "'" + auto.getModelo() + "')" ;

        // paso 3 crear instruccion
        Statement st = null;

        try {
            st = conn.createStatement();
            // paso 4 ejecutar instruccion
            st.execute(sql);
            // paso 5 cerrar conexion
            st.close();
            conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Auto> findAll() {

        // 1. conectar
        conn = AdministradorConexion.obtenerConexion();

        // 2. crear consulta SQL
        String sql = "SELECT * FROM autos";

        // 3. crear statement y resultset
        Statement st = null;
        ResultSet rs = null;

        List<Auto> listaAutos = new java.util.ArrayList<>();
        try {
            // 4. crear instruccion
            st = conn.createStatement();
            // 5. ejecutar consulta y guarda el resultado en resulset
            rs = st.executeQuery(sql);

            // 6. recorrer el resultset y guardar los datos en una lista
            while (rs.next()) {
                Auto auto = new Auto();
                auto.setIdAuto(rs.getInt("idAuto"));
                auto.setAnio(rs.getInt("anio"));
                auto.setPatente(rs.getString("patente"));
                auto.setColor(rs.getString("color"));
                auto.setKilometraje(rs.getInt("kilometraje"));
                auto.setMarca(Marca.valueOf(rs.getString("marca")));
                auto.setModelo(rs.getString("modelo"));

                listaAutos.add(auto);
            }

            // cerrar el resultset y statement
            rs.close();
            st.close();
            conn.close();

        } catch (SQLException e) {
            System.out.println("Error al crear el statement");
            throw new RuntimeException(e);
        }

        return listaAutos;
    }
}
