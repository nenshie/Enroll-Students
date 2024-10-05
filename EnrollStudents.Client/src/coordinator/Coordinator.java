/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package coordinator;

import communication.Communication;
import controller.AddClassController;
import controller.ClassFormController;
import controller.EnrollStudentController;
import controller.LoginController;
import controller.MainFormController;
import controller.ViewAllStudentsController;
import controller.ViewClassController;
import controller.ViewSchoolController;
import controller.ViewStudentController;
import domain.Administrator;
import domain.School;
import domain.Class;
import domain.Student;
import form.AddClassForm;
import form.ClassForm;
import form.EnrollStudentForm;
import form.FormMod;
import form.LoginForm;
import form.MainForm;
import form.ViewAllStudentsForm;
import form.ViewClassForm;
import form.ViewSchoolForm;
import form.ViewStudentForm;
import java.util.List;
import java.util.Locale;

/**
 *
 * @author neven
 */
public class Coordinator {
    private static Coordinator instance;
    private LoginController loginC;
    private MainFormController mainFormC;
    private Administrator loggedIn;
    private School selectedSchool;
    private ClassFormController classFormC;
    private ViewSchoolController viewSchC;
    private Class selectedClass;
    private ViewClassController viewClassC;
    private EnrollStudentController enrollStudC;
    private AddClassController addClassC;
    private ViewStudentController viewStudC;
    private Student selectedStudent;
    private ViewAllStudentsController viewAllStudC;
    private Locale currentLocale;
    

    private Coordinator() {

    }

    public static Coordinator getInstance() {
        if(instance == null){
            instance = new Coordinator();
        }
        return instance;
    }

    public Locale getCurrentLocale() {
        return currentLocale;
    }

    public void setCurrentLocale(Locale currentLocale) {
        this.currentLocale = currentLocale;
    }

    
    
    public void openLoginForm() {
        loginC = new LoginController(new LoginForm());
        loginC.openForm();
    }

    public void openMainForm() throws Exception {
        mainFormC = new MainFormController(new MainForm());
        mainFormC.openForm();   
    }

    public Administrator getLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(Administrator loggedIn) {
        this.loggedIn = loggedIn;
    }

    public School getSelectedSchool() {
        return selectedSchool;
    }

    public void setSelectedSchool(School selectedSchool) {
        this.selectedSchool = selectedSchool;
    }

    public void openClassForm() throws Exception {
        classFormC = new ClassFormController(new ClassForm());
        classFormC.openForm();
    }

    public void openViewSchForm(FormMod fm) throws Exception {
        viewSchC = new ViewSchoolController(new ViewSchoolForm());
        viewSchC.openForm(fm);
    }

    public Class getSelectedClass() {
        return selectedClass;
    }

    public void setSelectedClass(Class selectedClass) {
        this.selectedClass = selectedClass;
    }

    public void openViewClassForm() throws Exception {
        viewClassC = new ViewClassController(new ViewClassForm());
        viewClassC.openForm();
    }

    public void openEnrollStudForm() throws Exception {
        enrollStudC = new EnrollStudentController(new EnrollStudentForm());
        enrollStudC.openForm();
    }

    public void openAddClassForm(FormMod fm) throws Exception {
        addClassC = new AddClassController(new AddClassForm());
        addClassC.openForm(fm);
    }

    public void openViewStudForm(FormMod fm) throws Exception {
        viewStudC = new ViewStudentController(new ViewStudentForm());
        viewStudC.openForm(fm);
    }

    public Student getSelectedStudent() {
        return selectedStudent;
    }

    public void setSelectedStudent(Student selectedStudent) {
        this.selectedStudent = selectedStudent;
    }

    public void openViewAllStudForm() throws Exception {
        viewAllStudC = new ViewAllStudentsController(new ViewAllStudentsForm());
        viewAllStudC.openForm();
    }


    public void refreshTableClassForm() throws Exception {
        classFormC.refreshTable();
    }

    public void refreshViewClassTable() throws Exception {
        viewClassC.refreshTable();
    }

    public void updateStudentTable(List<Student> selectedStudents) throws Exception {

        viewClassC.updateStudentTable(selectedStudents);
    }

    public void updateViewAllStudTable() throws Exception {
        if(viewAllStudC == null){
            viewAllStudC = new ViewAllStudentsController(new ViewAllStudentsForm());
        }
        viewAllStudC.refreshViewAllStudTable();
    }

    public void updateTableSchool() throws Exception {
        mainFormC.updateTableSchool();
    }

   

    
    
    
    
}
