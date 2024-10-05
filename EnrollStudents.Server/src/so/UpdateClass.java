/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import controller.Controller;
import domain.Enrollment;
import domain.Student;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author neven
 */
public class UpdateClass extends AbstractGenericOperation{
    List<Student> students;
    domain.Class cl;
    
    @Override
    protected void validate(Object object) throws Exception {
        HashMap<domain.Class, List<Student>> map = (HashMap<domain.Class, List<Student>>) object;
       for (HashMap.Entry<domain.Class, List<Student>> entry : map.entrySet()) {
         cl = entry.getKey();
         students = entry.getValue();
         
       }
       if(cl == null || !(cl instanceof domain.Class)){
            throw new Exception("Sistem nije mogao da azurira odeljenje");
        }
       
       for (Student student : students) {
            Enrollment enm = new Enrollment(new java.sql.Date(new Date().getTime()), cl, student, Controller.getInstance().getCurrentlyLogged());
            validateEnrollment(enm);
        }
               
       
    }

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
        //try { 
       HashMap<domain.Class, List<Student>> map = (HashMap<domain.Class, List<Student>>) object;
       for (HashMap.Entry<domain.Class, List<Student>> entry : map.entrySet()) {
         cl = entry.getKey();
         students = entry.getValue();
        
        
         broker.update(cl, key);
         
        for (Student student : students) {
             
             Date utilDate = new Date();
             java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
                Enrollment enm = new Enrollment(sqlDate, cl, student, Controller.getInstance().getCurrentlyLogged());
                broker.add(enm);
            
        } }
        //} catch (Exception e) {
          //       throw new Exception("Sistem nije mogao da doda odeljenje, ucenik je vec upisan u odeljenje");
           // }
    }

protected void validateEnrollment(Object object) throws Exception {
        if(object == null || !(object instanceof Enrollment)){
            throw new Exception("Sistem nije mogao da upise ucenika u odeljenje");
        }
       List<Enrollment> enrolls = Controller.getInstance().getAllEnrolls();
          for(Enrollment en: enrolls){
              if(((Enrollment)object).getStudent().getStudentID() == en.getStudent().getStudentID()){
                   throw new Exception("Ucenik je ved upisan u odeljenje!");
                       }
                   }
    }
    
}
