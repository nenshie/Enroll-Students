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
public class Class implements DomainObject{

    private int classID;
    private String name;
    private int numOfStud;
    private Grade grade;
    private School school;
    private List<Student> listStud;

    public Class() {
    }

    public Class(int classID, String name, int numOfStud, Grade grade, School school) {
        this.classID = classID;
        this.name = name;
        this.numOfStud = numOfStud;
        this.grade = grade;
        this.school = school;
    }

    

    public int getClassID() {
        return classID;
    }

    public void setClassID(int classID) {
        this.classID = classID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumOfStud() {
        return numOfStud;
    }

    public void setNumOfStud(int numOfStud) {
        this.numOfStud = numOfStud;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        this.school = school;
    }

    public List<Student> getListStud() {
        return listStud;
    }

    public void setListStud(List<Student> listStud) {
        this.listStud = listStud;
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
        final Class other = (Class) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.school, other.school);
    }

   


  
 
   

    @Override
    public String toString() {
        return name;
    }
    
    
    
    
    @Override
    public String getTableName() {
        return "class";
    }

    @Override
    public String getPrimaryKey() {
        return "class.classID="+classID;
    }

    @Override
    public String getColumNames() {
        return "name, numOfStud, grade, school";
    }

    @Override
    public String getInsertValues() {
        return "'"+name+"',"+numOfStud+",'"+grade+"',"+school.getSchoolID();

    }

    @Override
    public String getSelectValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateValues() {
        return "name='"+name+"', numOfStud="+numOfStud+", grade='"+grade+"', school="+school.getSchoolID();

    }

    @Override
    public String getJoinTable() {
        return "JOIN school ";
    }

    @Override
    public String getJoinCondition() {
        return "ON(class.school = school.schoolID) JOIN city ON(school.city = city.cityID)";
    }

    @Override
    public List<DomainObject> getList(ResultSet rs) throws Exception {
         List<DomainObject> list = new ArrayList<>();
        while(rs.next()){
            Class c = new Class();
            c.setClassID(rs.getInt("class.classID"));
            c.setName(rs.getString("class.name"));
            c.setNumOfStud(rs.getInt("class.numOfStud"));
            
            String g = rs.getString("class.grade");
            Grade grade = Grade.valueOf(g);
            c.setGrade(grade);
            
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
            
            c.setSchool(school);
            
            list.add(c);
            
        }
        
        return list;
    }
    
}
