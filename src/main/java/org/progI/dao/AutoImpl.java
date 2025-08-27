package org.progI.dao;

import org.progI.configuracion.AdministradorConexion;
import org.progI.entities.Auto;
import org.progI.entities.Marca;
import org.progI.interfaces.AdmConexion;
import org.progI.interfaces.DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoImpl implements DAO<Auto, Integer>, AdmConexion {

  private Connection conn = null;

  private static final String SQL_INSERT = "INSERT INTO autos (idAuto,patente,color,anio,kilometraje,marca,modelo) " +
      "VALUES            (?,   ?,   ?,   ?,   ?,    ?,   ?)";

  private static final String SQL_UPDATE =
      "UPDATE autos SET " +
          "patente = ? , color = ? , anio = ? , kilometraje = ? " +
          ", marca = ? , modelo = ?" +
          "WHERE idAuto = ?";

  private static final String SQL_DELETE = "DELETE FROM autos WHERE idAuto = ?";
  private static final String SQL_GETALL = "SELECT * FROM autos ORDER BY patente";
  private static final String SQL_GETBYID = "SELECT * FROM autos WHERE idAuto = ?";

  @Override
  public List<Auto> getAll() {
    List<Auto> lista = new ArrayList<>();
    return lista;
  }

  @Override
  public void insert(Auto objeto) {
    // establecer conexion
    Auto auto = objeto;
    conn = obtenerConexion();

    // paso 2 crear string consulta SQL
    /*
    String sql =
        "INSERT INTO autos (idAuto,patente,color,anio,kilometraje,marca,modelo) " +
            "VALUES            (" + auto.getIdAuto() + "," +
            "'" + auto.getPatente() + "'," +
            "'" + auto.getColor() + "'," +
            +auto.getAnio() + "," +
            +auto.getKilometraje() + "," +
            "'" + auto.getMarca() + "'," +
            "'" + auto.getModelo() + "')";
     */

    // paso 3 crear instruccion
    PreparedStatement pst = null;
    try {

      // con la conexion llamo al prepareStatement pasandole la consulta SQL
      pst = conn.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);

      pst.setString(1, auto.getPatente());
      pst.setString(2, auto.getColor());
      pst.setInt(3, auto.getAnio());
      pst.setInt(4, auto.getKilometraje());
      pst.setString(5, auto.getMarca().toString());
      pst.setString(6, auto.getModelo());

      // paso 4 ejecutar instruccion
      // executeUpdate devuelve 1 si ejecuto correctamente, 0 caso contrario
      int resultado = pst.executeUpdate();
      if (resultado == 1) {
        System.out.println("Auto insertao correctamente");
      } else {
        System.out.println("No se pudo insertar el auto");
      }

      ResultSet rs = pst.getGeneratedKeys();
      if (rs.next()) {
        auto.setIdAuto(rs.getInt(1));
        System.out.println("El id asignado es " + auto.getIdAuto());
      }

      // paso 5 cerrar conexion
      pst.close();
      conn.close();
    } catch (SQLException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void update(Auto objeto) {
    Auto auto = objeto;
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

      conn = obtenerConexion();
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

  @Override
  public void delete(Auto objeto) {

  }

  @Override
  public Auto getById(Integer id) {
    return null;
  }

  @Override
  public boolean existsById(Integer id) {
    return false;
  }
}
