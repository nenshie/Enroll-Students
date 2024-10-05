/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import communication.Operation;
import domain.Administrator;
import domain.City;
import domain.Class;
import domain.Enrollment;
import domain.School;
import domain.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import so.AddClass;
import so.AddSchool;
import so.AddStudent;
import so.DeleteSchool;
import so.DeleteStudent;
import so.EnrollStudent;
import so.FindStudents;
import so.GetAllCities;
import so.GetAllClasses;
import so.GetAllEnrolls;
import so.GetAllSchools;
import so.GetAllStudents;
import so.GetNumOfClasses;
import so.GetSelectedSchools;
import so.Login;
import so.UpdateClass;
import so.UpdateSchool;
import so.UpdateStudent;
import thread.ClientThread;

/**
 *
 * @author neven
 */
public class Controller {
     private static Controller instance;
     private List<Administrator> loggedIn = new ArrayList<>();
     private School selectedSchool;
     private Administrator currentlyLogged;
    
     private Controller() {
    
    }

    public static Controller getInstance() {
     if(instance == null){
         instance = new Controller();
     }
        return instance;
    }

    public Administrator login(Administrator admin) throws Exception {
        Login operation = new Login();
        operation.execute(admin, null);
        System.out.println("KLASA CONTROLLER: "+operation.getAdmin());
        return operation.getAdmin();
    }

    public List<Administrator> getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(List<Administrator> loggedIn) {
        this.loggedIn = loggedIn;
    }

    public List<School> getAllSchools() throws Exception {
        School school = new School();
        GetAllSchools operation = new GetAllSchools();
        operation.execute(school, school.getJoinTable()+school.getJoinCondition());
        System.out.println("KLASA CONTROLLER: "+operation.getAllSchools());
        return operation.getAllSchools();
        
    }

    public List<City> getAllCities() throws Exception {
        City city = new City();
        GetAllCities operation = new GetAllCities();
        operation.execute(city, null);
        System.out.println("KLASA CONTROLLER: "+operation.getAllCities());
        return operation.getAllCities();
        
    }

    public List<School> getSeelctedSchools(int cityID) throws Exception {
        School school = new School();
        String cityid = String.valueOf(cityID);
        GetSelectedSchools operation = new GetSelectedSchools();
        operation.execute(school, cityid);
        System.out.println("KLASA CONTROLLER: "+operation.getSelectedSchools());
        return operation.getSelectedSchools();
    }

    public void deleteSchool(School s) throws Exception {
        DeleteSchool operation = new DeleteSchool();
        operation.execute(s, null);
    }
    
    public List<Class> getAllClasses(School s) throws Exception {
        setSelectedSchool(s);
        Class cl = new Class();
        GetAllClasses operation = new GetAllClasses();
        String condition = String.valueOf(s.getSchoolID());
        operation.execute(cl, condition);
        System.out.println("KLASA CONTROLLER: "+operation.getAllClasses());
        return operation.getAllClasses();
    }

    public void addSchool(School sch) throws Exception {
        AddSchool operation = new AddSchool();
        operation.execute(sch, null);
    }

    public void updateSchool(School sc) throws Exception {
        UpdateSchool operation = new UpdateSchool();
        operation.execute(sc, null);
    }

    public List<Student> findStudnets(Class cl) throws Exception {
        String id = String.valueOf(cl.getClassID());
        FindStudents operation = new FindStudents();
        operation.execute(null, id);
        System.out.println("KLASA CONTROLLER: "+operation.getSelectedStudnets());
        return operation.getSelectedStudnets();
    }

    public List<Student> getAllStud() throws Exception {
        GetAllStudents operation = new GetAllStudents();
        Student s = new Student();
        String conidition = s.getJoinTable()+" "+s.getJoinCondition();
        operation.execute(s, conidition);
        System.out.println("KLASA CONTROLLER: "+operation.getAllStud());
        return operation.getAllStud();
        
    }

    public List<Enrollment> getAllEnrolls() throws Exception {
        Enrollment en = new Enrollment();
        String condition = en.getJoinTable()+ " "+en.getJoinCondition();
        GetAllEnrolls operation = new GetAllEnrolls();
        operation.execute(en,condition);
        System.out.println("KLASA CONTROLLER: "+operation.getAllEnrolls());
        return operation.getAllEnrolls();
    }

    public void addClass(HashMap map) throws Exception {
        AddClass operation = new AddClass();
        operation.execute(map, null);
    }

    public void updateStudent(Student stud) throws Exception {
        UpdateStudent operation = new UpdateStudent();
        operation.execute(stud, null);
    }

    public void updateClass(HashMap map) throws Exception {
        UpdateClass operation = new UpdateClass();
        operation.execute(map, null);
    }

    
    public void addStudent(Student stud) throws Exception {
        AddStudent operation = new AddStudent();
        operation.execute(stud, null);
    }

    public void deleteStudent(Student std) throws Exception {
        DeleteStudent operation = new DeleteStudent();
        operation.execute(std, null);
    }

    public School getSelectedSchool() {
        return selectedSchool;
    }

    public void setSelectedSchool(School selectedSchool) {
        this.selectedSchool = selectedSchool;
    }

    public Administrator getCurrentlyLogged() {
        return currentlyLogged;
    }

    public void setCurrentlyLogged(Administrator currentlyLogged) {
        this.currentlyLogged = currentlyLogged;
    }

    public int GetNumOfClasses(int schoolID) throws Exception {
        List<Class> listCls = new ArrayList<>();
        int j = 0;
        GetNumOfClasses operation = new GetNumOfClasses();
        operation.execute(schoolID, null);
        
        System.out.println("Broj odeljenja: "+ operation.getList().size());
        return operation.getList().size();
    }

    
    

    
    
}
