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
public class Enrollment implements DomainObject{

   
    private Date date;
    private Class c;
    private Student student;
    private Administrator admin;

    public Enrollment() {
    }

    public Enrollment(Date date, Class c, Student student, Administrator admin) {
        this.date = date;
        this.c = c;
        this.student = student;
        this.admin = admin;
    }

    

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Class getC() {
        return c;
    }

    public void setC(Class c) {
        this.c = c;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Administrator getAdmin() {
        return admin;
    }

    public void setAdmin(Administrator admin) {
        this.admin = admin;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final Enrollment other = (Enrollment) obj;
        if (!Objects.equals(this.c, other.c)) {
            return false;
        }
        return Objects.equals(this.student, other.student);
    }

 

    @Override
    public String toString() {
        return "Enrollment{" + "date=" + date + ", c=" + c + ", student=" + student + ", admin=" + admin + '}';
    }

 

 
    
    
    @Override
    public String getTableName() {
        return "enrollment";
    }

    @Override
    public String getPrimaryKey() {
        return null;
    }

    @Override
    public String getColumNames() {
        return "date, class, student, admin";
    }

    @Override
    public String getInsertValues() {
      return "'"+date+"',"+c.getClassID()+","+student.getStudentID()+","+admin.getAdminID();
    }

    @Override
    public String getSelectValues() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public String getUpdateValues() {
       return "date='"+date+"', class="+c.getClassID()+", student="+student.getStudentID()+", admin="+admin.getAdminID();
    }

    @Override
    public String getJoinTable() {
        return " JOIN class";
    }

    @Override
    public String getJoinCondition() {
        return " on(enrollment.class = class.classID) join student on(enrollment.student = student.studentID) join administrator on(enrollment.admin = administrator.adminID)";
    }

    @Override
    public List<DomainObject> getList(ResultSet rs) throws Exception {
        List<DomainObject> list = new ArrayList<>();
        while(rs.next()){
            
            Enrollment e = new Enrollment();
            e.setDate(rs.getDate("enrollment.date"));
            
            Class c = new Class();
            c.setClassID(rs.getInt("class.classID"));
            c.setName(rs.getString("class.name"));
            c.setNumOfStud(rs.getInt("class.numOfStud"));
            
            String g = rs.getString("class.grade");
            Grade grade = Grade.valueOf(g);
            c.setGrade(grade);
            e.setC(c);
            
            Student student = new Student();
            student.setStudentID(rs.getInt("student.studentID"));
            student.setJMBG(rs.getString("student.JMBG"));
            student.setName(rs.getString("student.name"));
            student.setSurname(rs.getString("student.surname"));
            student.setBirthday(rs.getDate("student.birthday"));
            student.setParent(rs.getString("student.parent"));
            
            e.setStudent(student);
          
            int adminID = rs.getInt("administrator.adminID");
            String name = rs.getString("administrator.name");
            String surname = rs.getString("administrator.surname");
            String username = rs.getString("administrator.username");
            String password = rs.getString("administrator.password");
            Date birthday = rs.getDate("administrator.birthday");
            
            Administrator a = new Administrator(adminID, name, surname, username, password, birthday);
            
            e.setAdmin(a);
            
            list.add(e);
            
        }
        
        return list;
    
    
    }
    
}
