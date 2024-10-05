/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package so;

import controller.Controller;
import domain.Class;
import domain.Enrollment;
import domain.Student;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author neven
 */
public class AddClass extends AbstractGenericOperation{
    List<Student> students;
    Class cl;
    @Override
    protected void validate(Object object) throws Exception {
      HashMap<Class, List<Student>> map = (HashMap<Class, List<Student>>) object;
       for (HashMap.Entry<Class, List<Student>> entry : map.entrySet()) {
         cl = entry.getKey();
         students = entry.getValue();
          for (Student student : students) {
            Date utilDate = new Date();
            java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
            
            Enrollment enm = new Enrollment(sqlDate, cl, student, Controller.getInstance().getCurrentlyLogged());
            
            if (isStudentAlreadyEnrolled(enm)) {
                throw new Exception("Učenik " + student.getName() + " je već upisan u odeljenje!");
            }
        }
       }
       if(cl == null || !(cl instanceof Class)){
            throw new Exception("Sistem nije mogao da doda odeljenje");
        }
               
       }
       
    

    @Override
    protected void executeOperation(Object object, String key) throws Exception {
         //try {
              HashMap<Class, List<Student>> map = (HashMap<Class, List<Student>>) object;
                for (HashMap.Entry<Class, List<Student>> entry : map.entrySet()) {
                    cl = entry.getKey();
                    students = entry.getValue();

                    int clId = broker.addClass(cl);
                    cl.setClassID(clId);

                    for (Student student : students) {
                        Date utilDate = new Date();
                        java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());

                        Enrollment enm = new Enrollment(sqlDate, cl, student, Controller.getInstance().getCurrentlyLogged());
                        broker.add(enm);
                    }
                }
           /* } catch (Exception e) {
                throw new Exception("Sistem nije mogao da doda odeljenje: " + e.getMessage());
            }*/

    }
    
    protected boolean isStudentAlreadyEnrolled(Enrollment enm) throws Exception {
            List<Enrollment> enrolls = Controller.getInstance().getAllEnrolls();
            for (Enrollment en : enrolls) {
                System.out.println("Checking enrollment: " + en.getStudent().getName());
                System.out.println("Against: " + enm.getStudent().getName());
                if (enm.getStudent().getStudentID() == en.getStudent().getStudentID()) {
                    return true;
                }
            }
            return false;
        }
}
