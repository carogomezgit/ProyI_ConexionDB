package org.progI;

import org.progI.configuracion.AdministradorConexion;
import org.progI.dao.AutoDAO;
import org.progI.dao.AutoImpl;
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
    Connection miConexion = AdministradorConexion.obtenerConexion();

    Auto auto = new Auto("XXYYZZ", "blanco", 2025, 897, Marca.Honda, "Fit");

    // guardo en la BD
    AutoDAO autoDAO = new AutoDAO();
    autoDAO.insertarAuto(auto);

    List<Auto> miLista = autoDAO.findAll();
    if (!miLista.isEmpty()) {
      for (Auto auto1 : miLista) {
        System.out.println(auto1.toString());
      }
    }

    //modifica auto
    Auto autoAModificar = new Auto("AABBCC", "Gis", 2024, 897555
            , Marca.Honda, "Fit");

    autoDAO.update(autoAModificar);

    //autoDAO.delete(14);

    System.out.println("Auto encontrado: " + autoDAO.getById(25).toString());

    System.out.println("Lista de autos después de la modificación:");
    // recorro la lista de autos
    miLista = autoDAO.findAll();
    if (!miLista.isEmpty()) {
      for (Auto auto1 : miLista) {
        System.out.println(auto1.toString());
      }
    }

    System.out.println("--- AGREGANDO CON DAO IMPL ---");
    Auto autoTest = new Auto("CCCCCC", "Blanco", 2025, 0, Marca.Toyota, "Corolla");
    AutoImpl autoImpl = new AutoImpl();
    autoImpl.insert(autoTest);
  }
}