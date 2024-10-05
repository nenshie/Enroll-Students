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
public class School implements DomainObject{
    
    private int schoolID;
    private String name;
    private String address;
    private String phone;
    private City city;

    public School() {
    }

    public School(int schoolID, String name, String address, String phone, City city) {
        this.schoolID = schoolID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.city = city;
    }

    public int getSchoolID() {
        return schoolID;
    }

    public void setSchoolID(int schoolID) {
        this.schoolID = schoolID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
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
        final School other = (School) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.address, other.address)) {
            return false;
        }
        if (!Objects.equals(this.phone, other.phone)) {
            return false;
        }
        return Objects.equals(this.city, other.city);
    }

    @Override
    public String toString() {
        return name;
    }
    
    
    
    

    @Override
    public String getTableName() {
        return "school";
    }

    @Override
    public String getPrimaryKey() {
        return "school.schoolID="+schoolID;
    }

    @Override
    public String getColumNames() {
        return "name, address, phone, city";
    }

    @Override
    public String getInsertValues() {
        return "'"+name+"','"+address+"','"+phone+"',"+city.getCityID();
    }

    @Override
    public String getSelectValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateValues() {
        return "name='"+name+"', address='"+address+"', phone='"+phone+"', city="+city.getCityID();
    }

    @Override
    public String getJoinTable() {
        return "JOIN city ";
    }

    @Override
    public String getJoinCondition() {
        return "ON (school.city = city.cityID) ";
    }

    @Override
    public List<DomainObject> getList(ResultSet rs) throws Exception {
        List<DomainObject> list = new ArrayList<>();
        while(rs.next()){
            
            School school = new School();
            school.setSchoolID(rs.getInt("school.schoolID"));
            school.setName(rs.getString("school.name"));
            school.setAddress(rs.getString("school.address"));
            school.setPhone(rs.getString("school.phone"));
            
            
            City city = new City();
            city.setCityID(rs.getInt("city.cityID"));
            city.setName(rs.getString("city.name"));
            city.setZipCode(rs.getInt("city.zipCode"));
            
            
            school.setCity(city);
            
            list.add(school);
            
        }
        
        return list;
    }
    
}
