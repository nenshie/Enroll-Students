/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author neven
 */
public class City implements DomainObject{
    
    private int cityID;
    private String name;
    private int zipCode;

    public City() {
    }

    public City(int cityID, String name, int zipCode) {
        this.cityID = cityID;
        this.name = name;
        this.zipCode = zipCode;
    }

    public int getCityID() {
        return cityID;
    }

    public void setCityID(int cityID) {
        this.cityID = cityID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final City other = (City) obj;
        if (this.zipCode != other.zipCode) {
            return false;
        }
        return Objects.equals(this.name, other.name);
    }

    @Override
    public String toString() {
        return name;
    }
    
    

    @Override
    public String getTableName() {
        return "city";
    }

    @Override
    public String getPrimaryKey() {
        return "city.cityID="+cityID;
    }

    @Override
    public String getColumNames() {
        return "name, zipCode";
    }

    @Override
    public String getInsertValues() {
         return "'"+name+"',"+zipCode;
    }

    @Override
    public String getSelectValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateValues() {
        return "name='"+name+"', zipCode="+zipCode;  
    }

    @Override
    public String getJoinTable() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getJoinCondition() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<DomainObject> getList(ResultSet rs) throws Exception {
        List<DomainObject> list = new ArrayList<>();
        while(rs.next()){
            int cityID= rs.getInt("city.cityID");
            String name = rs.getString("city.name");
            int zipCode= rs.getInt("city.zipCode");
            
            City c = new City(cityID, name, zipCode);
            list.add(c);
        }
        
        return list;
    }
    
}
