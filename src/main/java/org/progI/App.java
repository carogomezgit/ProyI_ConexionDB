package org.progI;

import org.progI.configuracion.AdministradorConexion;
import org.progI.dao.AutoDAO;
import org.progI.entities.Auto;
import org.progI.entities.Marca;

import java.sql.Connection;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App {
  public static void main(String[] args) {
    System.out.println("Hello World!");
    // Connection miConexion = AdministradorConexion.obtenerConexion();

    Auto auto = new Auto("CCCCCC", "blanco", 2025, 897, Marca.Honda, "Fit");

    // guardo en la BD
    AutoDAO autoDAO = new AutoDAO();
    autoDAO.insertarAuto(auto);

    List<Auto> miLista = autoDAO.findAll();
    if (!miLista.isEmpty()){
      for (Auto auto1 : miLista) {
        System.out.println(auto1.toString());
      }
    }
  }
}
