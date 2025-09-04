package org.progI.configuracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class AdministradorConexion {

  // tiene un solo metodo
  public static Connection obtenerConexion() {
    // 4 datos de conexion
    String dbDriver = "com.mysql.cj.jdbc.Driver";
    // cadena conexion a mi BD
    // String dbCadenaConexion = "jdbc:mysql://localhost:3306/progAutos";
    String dbCadenaConexion = "jdbc:mysql://localhost:3306/progautos";
    // nom usuarioBD
    String dbUsuario = "root";
    // pass bd
    String dbPass = "root";

    Connection conn = null;

    try {
      Class.forName(dbDriver);

      conn = DriverManager.getConnection(dbCadenaConexion, dbUsuario, dbPass);

    } catch (ClassNotFoundException e) {
      System.out.println("No se encontro el driver de la BD");
      throw new RuntimeException(e);
    } catch (SQLException e) {
      System.out.println("No se pudo conectar a la BD");
      throw new RuntimeException(e);
    }
    return conn;
  }
}