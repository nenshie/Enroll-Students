/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package domain;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author neven
 */
public interface DomainObject extends Serializable{

    
     public String getTableName(); 
     public String getPrimaryKey(); 
     public String getColumNames(); 
     public String getInsertValues(); 
     public String getSelectValues();
     public String getUpdateValues();
     public String getJoinTable();
     public String getJoinCondition();
     public List<DomainObject> getList(ResultSet rs) throws Exception;
    
   
    
    
    
    
    
    
    
    
    
    
    
    
    
}
