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

  public void update(Auto auto) {
    // establecer conexion
    // solo si el auto existe lo modifico
    if (this.existsById(auto.getIdAuto())) {
      //
      String sql = "UPDATE autos SET " +
          "patente = '" + auto.getPatente() + "', " +
          "color = '" + auto.getColor() + "', " +
          "anio = " + auto.getAnio() + ", " +
          "kilometraje = " + auto.getKilometraje() + ", " +
          "marca = '" + auto.getMarca() + "', " +
          "modelo = '" + auto.getModelo() + "' " +
          "WHERE idAuto = " + auto.getIdAuto();

      conn = AdministradorConexion.obtenerConexion();

      // Se crea un statement
      Statement st = null;

      try {
        // ejecuto
        st = conn.createStatement(); // CREO STATEMENT
        st.execute(sql);

        //cierro
        st.close();
        conn.close();

      } catch (SQLException e) {
        System.out.println("Error al crear el statement");
      }
    }
  }

  public boolean existsById(int id) {
    // establecer conexion
    conn = AdministradorConexion.obtenerConexion();
    String sql = "SELECT * FROM autos WHERE idAuto = " + id;

    // Se crea un statement
    Statement st = null;
    ResultSet rs = null;
    boolean existe = false;

    try {
      st = conn.createStatement(); // CREO STATEMENT
      rs = st.executeQuery(sql); //EJECUTO CONSULTA
      // SI LA CONSULTA DEVUELVE AL MENOS UN REGISTRO, EXISTE
      if (rs.next()) {
        existe = true;
      }

      // CIERRO RESULTSET Y STATEMENT
      rs.close();
      st.close();
      conn.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return existe;
  }

  public void insertarAuto(Auto auto) {

    // 1 establecer conexion
    conn = AdministradorConexion.obtenerConexion();
    // establecer conexion a la base de datos

    // paso 2 crear string consulta SQL
    String sql =
        "INSERT INTO autos (idAuto,patente,color,anio,kilometraje,marca,modelo) " +
            "VALUES            (" + auto.getIdAuto() + "," +
            "'" + auto.getPatente() + "'," +
            "'" + auto.getColor() + "'," +
            +auto.getAnio() + "," +
            +auto.getKilometraje() + "," +
            "'" + auto.getMarca() + "'," +
            "'" + auto.getModelo() + "')";

    // paso 3 crear instruccion
    Statement st = null;

    try {
      st = conn.createStatement();
      st.execute(sql); // paso 4 ejecutar instruccion

      // paso 5 cerrar conexion
      st.close();
      conn.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  public List<Auto> findAll() {

    //1 conectar
    conn = AdministradorConexion.obtenerConexion();

    //2  crear consulta SQL
    String sql = "SELECT * FROM autos order by patente";

    // 3 crear  statement y resulset
    Statement st = null;
    ResultSet rs = null;

    List<Auto> listaAutos = new java.util.ArrayList<>();

    try {
      // paso 4 crear instruccion
      st = conn.createStatement();
      // paso 5 ejecutar consulta y guarda el resultado en resultset
      rs = st.executeQuery(sql);

      // paso 6 recorrer el resultset y guardar los autos en una lista
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

      // paso 7 cerrar el resultset y statement
      rs.close();
      st.close();
      conn.close();

    } catch (SQLException e) {
      System.out.println("Error al crear el statement");
      throw new RuntimeException(e);
    }

    return listaAutos;
  }

  public void delete(int idauto){

    conn= AdministradorConexion.obtenerConexion();
    String sql = "DELETE FROM autos WHERE idAuto = " + idauto;

    Statement st = null;

    try {
      st = conn.createStatement(); //creo statement
      st.execute(sql); //ejecuto la consulta

      // cierro statement y conexion
      st.close();
      conn.close();

    } catch (SQLException e) {
      System.out.println("Error al crear el statement");
    }
  }


  public Auto getById(int id) {
    // establecer conexion
    conn = AdministradorConexion.obtenerConexion();
    String sql = "SELECT * FROM autos WHERE idAuto = " + id;

    // Se crea un statement
    Statement st = null;
    ResultSet rs = null;
    Auto auto = new Auto();

    try {
      st = conn.createStatement(); // CREO STATEMENT
      rs = st.executeQuery(sql); // EJECUTO CONSULTA

      // SI LA CONSULTA DEVUELVE AL MENOS UN REGISTRO, EXISTE
      if (rs.next()) {
        // asigno los datos a auto
        auto.setIdAuto(rs.getInt("idAuto"));
        auto.setPatente(rs.getString("patente"));
        auto.setColor(rs.getString("color"));
        auto.setMarca(Marca.valueOf( rs.getString("marca")));
        auto.setAnio(rs.getInt("anio"));
        auto.setKilometraje(rs.getInt("kilometraje"));
        auto.setModelo(rs.getString("modelo"));
      }

      // CIERRO RESULTSET Y STATEMENT
      rs.close();
      st.close();
      conn.close();

    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
    return auto;
  }
}