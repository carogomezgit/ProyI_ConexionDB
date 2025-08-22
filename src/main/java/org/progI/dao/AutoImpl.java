package org.progI.dao;

import org.progI.entities.Auto;
import org.progI.interfaces.AdmConexion;
import org.progI.interfaces.DAO;

import java.util.ArrayList;
import java.util.List;

public class AutoImpl implements DAO<Auto,Integer>, AdmConexion {

  @Override
  public List<Auto> getAll() {
    List<Auto> lista = new ArrayList<>();
    return lista;
  }

  @Override
  public void insert(Auto objeto) {

  }

  @Override
  public void update(Auto objeto) {

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
