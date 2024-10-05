/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author neven
 */
public class Student implements DomainObject{
    
    private int studentID;
    private String JMBG;
    private String name;
    private String surname;
    private Date birthday;
    private String parent;
    private City city;

    public Student() {
    }

    public Student(int studentID, String JMBG, String name, String surname, Date birthday, String parent, City city) {
        this.studentID = studentID;
        this.JMBG = JMBG;
        this.name = name;
        this.surname = surname;
        this.birthday = birthday;
        this.parent = parent;
        this.city = city;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getJMBG() {
        return JMBG;
    }

    public void setJMBG(String JMBG) {
        this.JMBG = JMBG;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getParent() {
        return parent;
    }

    public void setParent(String parent) {
        this.parent = parent;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "Student{" + "JMBG=" + JMBG + ", name=" + name + ", surname=" + surname + ", birthday=" + birthday + ", parent=" + parent + ", city=" + city + '}';
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
        final Student other = (Student) obj;
        if (!Objects.equals(this.JMBG, other.JMBG)) {
            return false;
        }
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return Objects.equals(this.surname, other.surname);
    }

  
    
    

    @Override
    public String getTableName() {
        return "student";
    }

    @Override
    public String getPrimaryKey() {
        return "student.studentID="+studentID;
    }

    @Override
    public String getColumNames() {
        return "JMBG, name, surname, birthday, parent, city";
    }

    @Override
    public String getInsertValues() {
       return JMBG+",'"+name+"','"+surname+"','"+birthday+"','"+parent+"',"+city.getCityID();

    }

    @Override
    public String getSelectValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateValues() {
        return "name='"+name+"', surname='"+surname+"', birthday='"+birthday+"', parent='"+parent+"', city="+city.getCityID();
    }

    @Override
    public String getJoinTable() {
        return "JOIN city";
    }

    @Override
    public String getJoinCondition() {
        return "ON(student.city = city.cityID)";
    }

    @Override
    public List<DomainObject> getList(ResultSet rs) throws Exception {
         List<DomainObject> list = new ArrayList<>();
        while(rs.next()){
            
            Student student = new Student();
            student.setStudentID(rs.getInt("student.studentID"));
            student.setJMBG(rs.getString("student.JMBG"));
            student.setName(rs.getString("student.name"));
            student.setSurname(rs.getString("student.surname"));
            student.setBirthday(rs.getDate("student.birthday"));
            student.setParent(rs.getString("student.parent"));
            
            
            City city = new City();
            city.setCityID(rs.getInt("city.cityID"));
            city.setName(rs.getString("city.name"));
            city.setZipCode(rs.getInt("city.zipCode"));
            
            
            student.setCity(city);
            
            list.add(student);
            
        }
        
        return list;
    
    }
    
}
