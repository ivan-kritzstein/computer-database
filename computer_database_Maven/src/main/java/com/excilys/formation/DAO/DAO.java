//package com.excilys.formation.DAO;
//
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.util.List;
//
//import com.excilys.formation.model.Data;
//
//public abstract class DAO<T> {
//	protected Data connect = Data.getInstance();
//	public DAO(Connection conn) {
//		this.connect = conn;
//	}
//
//	/**
//	 * Méthode de création
//	 * 
//	 * @param obj
//	 * @return boolean
//	 */
//	public abstract void create(T obj);
//
//	/**
//	 * Méthode pour effacer
//	 * 
//	 * @param obj
//	 * @return boolean
//	 */
//	public abstract void delete(Long id);
//
//	/**
//	 * Méthode de mise à jour
//	 * 
//	 * @param obj
//	 * @return boolean
//	 */
//	public abstract void updateById(Long id, T obj);
//
//	public abstract void updateByName(String name, T obj);
//
//	/**
//	 * Méthode de recherche des informations
//	 * 
//	 * @param id
//	 * @return T
//	 */
//	public abstract T showDetailsWithId(Long id);
//
//	public abstract T showDetailsWithName(String name);
//
//	public abstract List<T> list();
//
//}