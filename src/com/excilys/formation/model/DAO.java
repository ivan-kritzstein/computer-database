package com.excilys.formation.model;

import java.sql.Connection;
import java.util.List;

public abstract class DAO<T> {
  protected Connection connect = null;

  public DAO(Connection conn){
    this.connect = conn;
  }

  /**
  * Méthode de création
  * @param obj
  * @return boolean 
  */
  public abstract void create(T obj);

  /**
  * Méthode pour effacer
  * @param obj
  * @return boolean 
  */
  public abstract void delete(int id);

  /**
  * Méthode de mise à jour
  * @param obj
  * @return boolean
  */
  public abstract void updateById(int id, T obj);
  
  public abstract void updateByName(String name, T obj);

  /**
  * Méthode de recherche des informations
  * @param id
  * @return T
  */
  public abstract T showDetailsWithId(int id);
  
  public abstract T showDetailsWithName(String name);
  
  public abstract List<T> list();
  
}