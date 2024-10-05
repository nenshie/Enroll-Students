/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository.db.impl;

import domain.DomainObject;
import java.util.ArrayList;
import java.util.List;
import repository.db.DbConnectionFactory;
import repository.db.DbRepository;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author neven
 */
public class DbRepositoryGeneric implements DbRepository<DomainObject>{

    @Override
    public void add(DomainObject param) throws Exception {
        String query = "INSERT INTO "+ param.getTableName()+"("+param.getColumNames()+") VALUES ("+param.getInsertValues()+")";
        System.out.println("Upit: " + query);
        Statement st =  DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(query);
        st.close();
    }

    @Override
    public void update(DomainObject param, String condition) throws Exception {
        String query = "UPDATE "+ param.getTableName()+ " SET "+param.getUpdateValues()+ " WHERE "+param.getPrimaryKey();
        System.out.println("Upit: " + query);
        Statement st =  DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(query);
        st.close();
    }

    @Override
    public void delete(DomainObject param, String condition) throws Exception {
        try {
            String query = "DELETE FROM "+ param.getTableName()+ " WHERE "+param.getPrimaryKey();
            System.out.println("Upit: " + query);
            Statement st =  DbConnectionFactory.getInstance().getConnection().createStatement();
            st.executeUpdate(query);
            st.close();
        } catch (java.sql.SQLIntegrityConstraintViolationException ex) {
                throw new Exception("Nije moguce obrisati entitet zbog stranog kljuca", ex);
         } catch (SQLException e) {
                throw new Exception("Database error occurred", e);
            }
    }

    @Override
    public List<DomainObject> get(DomainObject param, String condition) {
        List<DomainObject> list = new ArrayList<>();
        String query = "SELECT * FROM "+param.getTableName()+" "+ condition;
        System.out.println("Upit: " + query);
        
        Statement st;
        ResultSet rs;
        try {
            st = DbConnectionFactory.getInstance().getConnection().createStatement();
            rs = st.executeQuery(query);
            
            list= param.getList(rs);
            rs.close();
            st.close();
        
        } catch (Exception ex) {
            Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
        }
      return list;
    }

    @Override
    public List<DomainObject> getAll(DomainObject param) {
        List<DomainObject> list = new ArrayList<>();
        String query = "SELECT * FROM "+param.getTableName();
        System.out.println("Upit: " + query);
        
        Statement st;
        ResultSet rs;
        try {
            st = DbConnectionFactory.getInstance().getConnection().createStatement();
            rs = st.executeQuery(query);
            
            list= param.getList(rs);
            rs.close();
            st.close();
        
        } catch (Exception ex) {
            Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
        }
      
        
        return list;
    }

    @Override
    public List<DomainObject> find(DomainObject param, String join, String condition) throws Exception {
        List<DomainObject> list = new ArrayList<>();
        String query = "SELECT * FROM "+param.getTableName()+ join +" WHERE "+condition;
        System.out.println("Upit: " + query);
        
        Statement st;
        ResultSet rs;
        try {
            st = DbConnectionFactory.getInstance().getConnection().createStatement();
            rs = st.executeQuery(query);
            
            list= param.getList(rs);
            rs.close();
            st.close();
        
        } catch (Exception ex) {
            Logger.getLogger(DbRepositoryGeneric.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return list;
    }
    
     @Override
    public int addClass(DomainObject param) throws Exception {
        String query = "INSERT INTO "+ param.getTableName()+"("+param.getColumNames()+") VALUES ("+param.getInsertValues()+")";
        System.out.println("Upit: " + query);
        Statement st =  DbConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(query, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        int generatedId = -1;
        if (rs.next()) {
            generatedId = rs.getInt(1); 
        }
        rs.close();
        st.close();
        
        return generatedId;
    }

    
}
