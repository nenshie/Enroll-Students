/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package domain;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


/**
 *
 * @author neven
 */
public class Administrator implements DomainObject{

    private int adminID;
    private String name;
    private String surname;
    private String username;
    private String password;
    private Date birthday;

    public Administrator() {
    }

    public Administrator(int adminID, String name, String surname, String username, String password, Date birthday) {
        this.adminID = adminID;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.birthday = birthday;
    }

    public int getAdminID() {
        return adminID;
    }

    public void setAdminID(int adminID) {
        this.adminID = adminID;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    @Override
    public int hashCode() {
        int hash = 3;
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
        final Administrator other = (Administrator) obj;
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        return Objects.equals(this.password, other.password);
    }

    
    @Override
    public String toString() {
        return "Administrator{" + "name=" + name + ", surname=" + surname + '}';
    }

    @Override
    public String getTableName() {
        return "administrator";
    }

    @Override
    public String getPrimaryKey() {
        return "administrator.adminID="+adminID;
    }

    @Override
    public String getColumNames() {
        return "name, surname, usrname, password, birthday";
    }

    @Override
    public String getInsertValues() {
     throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

    @Override
    public String getSelectValues() {
        return "'"+name+"','"+surname+"','"+username+"','"+password+"','"+birthday+"'";
    }

    @Override
    public String getUpdateValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
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
        while (rs.next()) {
           int adminID = rs.getInt("administrator.adminID");
            String name = rs.getString("administrator.name");
            String surname = rs.getString("administrator.surname");
            String username = rs.getString("administrator.username");
            String password = rs.getString("administrator.password");
            Date birthday = rs.getDate("administrator.birthday");
            
            Administrator a = new Administrator(adminID, name, surname, username, password, birthday);
            list.add(a);
            
        }
        return list;
    }

   

 
    
}
